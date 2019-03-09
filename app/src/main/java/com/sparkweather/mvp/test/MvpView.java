package com.sparkweather.mvp.test;

import com.sparkweather.mvp.test.base.BaseView;

/**
 * 日期：2019/3/8 20:23
 * 创建：李加蒙
 * 描述：
 */
public interface MvpView extends BaseView {
    /**
     * @param data 当数据请求成功后，调用此接口显示 数据源
     */
    void showData(String data);
}
