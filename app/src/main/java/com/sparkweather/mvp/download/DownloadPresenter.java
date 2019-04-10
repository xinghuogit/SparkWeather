package com.sparkweather.mvp.download;


import android.annotation.SuppressLint;

import com.library.common.utils.StringUtils;
import com.library.common.api.IApiServer;
import com.library.common.api.BaseObserver;
import com.library.common.mvp.base.view.BasePresenter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        apiServer = retrofit.create(IApiServer.class);
        apiServer
                .downloadFile(url)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody body) throws Exception {
                        File file = saveFile(path, body);
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

    public static File saveFile(String filePath, ResponseBody body) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;
        try {
            if (StringUtils.isEmpty(filePath)) {
                return null;
            }
            file = new File(filePath);
            if (file == null || !file.exists()) {
                file.createNewFile();
            }
            long fileSize = body.contentLength();
            long fileSizeDownload = 0;
            byte[] fileReader = new byte[4096];
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) break;
                outputStream.write(fileReader, 0, read);
                fileSizeDownload += read;
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
