package com.sparkweather.mvp.download;

import com.sparkweather.api.BaseObserver;
import com.sparkweather.mvp.base.BaseView;
import com.sparkweather.utils.FileUtils;
import com.sparkweather.utils.StringUtils;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * 日期：2019/3/11 11:35
 * 创建：李加蒙
 * 描述：
 */
public abstract class DownloadObserver extends BaseObserver<String> {

    public DownloadObserver(BaseView view) {
        super(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess(String o) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onComplete() {
       super.onComplete();
    }

    @Override
    public void onNext(String responseBody) {
        if (StringUtils.isE(responseBody)) {
            onSuccess(FileUtils.getFile(responseBody));
        } else {
            onErrorMsg("file is null or file not exists");
        }
    }

    public abstract void onSuccess(File file);

    public abstract void onErrorMsg(String msg);
}
