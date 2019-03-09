package com.sparkweather.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 日期：2019/3/9 15:00
 * 创建：李加蒙
 * 描述：
 */
public class ApiRetrofit {
    private static final String TAG = "ApiRetrofit";

    public final String BASE_SERVER_URL = "http://192.168.0.86:13099/";

    private static ApiRetrofit apiRetrofit;
    private OkHttpClient client;
    private Retrofit retrofit;
    private ApiServer apiServer;

    public static ApiRetrofit getInstance() {
//        if (apiRetrofit == null) {
//            synchronized (Object.class) {
//                if (apiRetrofit == null) {
        apiRetrofit = new ApiRetrofit();
//                }
//            }
//        }
        return apiRetrofit;
    }

    /**
     * 请求访问quest response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| request.url()：" + request.url());
            Log.e(TAG, "| " + request.toString() + request.headers().toString());
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    public ApiRetrofit() {
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)//添加log拦截器
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时
                .readTimeout(10, TimeUnit.SECONDS)//阅读超时
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava2
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    public ApiServer getApiServer() {
        return apiServer;
    }
}
