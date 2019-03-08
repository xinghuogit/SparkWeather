package com.sparkweather.mvp.test;


import android.os.Handler;

/**
 * 日期：2019/3/8 20:17
 * 创建：李加蒙
 * 描述：
 */
public class MvpModel {

    public static void getNetData(final String param, final MvpCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param) {
                    case "Success":
                        callback.onSuccess("根据参数“" + param + "”的请求网络数据成功");
                        break;
                    case "Failure":
                        callback.onFailure("根据参数“" + param + "”的请求网络数据成功");
                        break;
                    case "Error":
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }
        }, 2000);
    }
}
