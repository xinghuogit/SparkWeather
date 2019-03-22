package com.sparkweather.mvp.download;


import android.annotation.SuppressLint;

import com.sparkweather.api.ApiServer;
import com.sparkweather.api.BaseObserver;
import com.sparkweather.mvp.base.BasePresenter;
import com.sparkweather.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 日期：2019/3/11 11:35
 * 创建：李加蒙
 * 描述：
 */
public class DownloadPresenter extends BasePresenter<DownloadView> {

    public DownloadPresenter(DownloadView baseView) {
        super(baseView);
    }

    @SuppressLint("CheckResult")
    public void downFile(final String url, final String path) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body(),
                                new ProgressResponseBody.ProgressListener() {
                                    @Override
                                    public void onProgress(long totalSize, long downSize) {
                                        baseView.onProgress(totalSize, downSize);
                                    }
                                })).build();
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://download.sdk.mob.com/").build();
        apiServer = retrofit.create(ApiServer.class);
        apiServer
                .downloadFile(url)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody body) throws Exception {
                        File file = FileUtils.saveFile(path, body);
                        return file.getPath();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DownloadObserver(baseView) {
                    @Override
                    public void onSuccess(File file) {
                        baseView.onSuccess(file);
                    }

                    @Override
                    public void onErrorMsg(String msg) {
                        onError(msg);
                    }
                });
    }

    public void upLoadFile(String... filePaths) {
        if (filePaths == null || filePaths.length <= 0) return;
        MultipartBody.Part[] parts = new MultipartBody.Part[filePaths.length];
        int count = 0;
        for (String s : filePaths) {
            File file = new File(s);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("headimg[]", file.getName(), requestFile);
            parts[count] = filePart;
            count++;
        }
        RequestBody token = RequestBody.create(MediaType.parse("multipart/form-data"), "B23FD6A1013CC748671931B15B02D94E3472FA11C68DA0E9C27B7AF118EB7065");
        addDisposable(apiServer.upLoadFile(parts, token), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onSuccess((String) o);
            }

            @Override
            public void onError(String msg) {
                baseView.onError(msg);
            }
        });
    }
}
