package com.sparkweather.mvp.download;

import com.sparkweather.mvp.base.BaseView;

import java.io.File;

/**
 * 日期：2019/3/11 11:33
 * 创建：李加蒙
 * 描述：
 */
public interface DownloadView extends BaseView {
    void onSuccess(File file);

    void onSuccess(String str);

    void onError(String str);
}
