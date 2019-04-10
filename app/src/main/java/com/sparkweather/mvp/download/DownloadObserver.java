package com.sparkweather.mvp.download;

import com.library.common.utils.FileUtils;
import com.library.common.utils.StringUtils;
import com.library.common.api.BaseObserver;
import com.library.common.mvp.base.view.BaseView;

import java.io.File;

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
        if (StringUtils.isEmpty(responseBody)) {
            onSuccess(FileUtils.getFile(responseBody));
        } else {
            onErrorMsg("file is null or file not exists");
        }
    }

    public abstract void onSuccess(File file);

    public abstract void onErrorMsg(String msg);
}
