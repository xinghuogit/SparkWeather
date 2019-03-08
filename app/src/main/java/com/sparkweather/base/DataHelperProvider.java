package com.sparkweather.base;

import android.content.Context;

/**
 * 日期：2019/3/8 17:26
 * 创建：李加蒙
 * 描述：
 */
public class DataHelperProvider {
    private static IDataHelper dataHelper;

    public static IDataHelper getHttpHelper(Context context) {
        if (dataHelper == null) {
            synchronized (DataHelperProvider.class) {
                if (dataHelper == null) {
                    dataHelper = new HttpHelper(context);
                }
            }
        }
        return dataHelper;
    }

}
