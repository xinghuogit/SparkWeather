package com.sparkweather.app;

import android.app.Application;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：全局Application
 */
public class BaseApplication extends Application {
    private BaseApplication instance;

    public BaseApplication getBaseApplication() {
        if (instance == null) instance = this;
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
