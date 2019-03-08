package com.sparkweather.mvp.test;

/**
 * 日期：2019/3/8 20:23
 * 创建：李加蒙
 * 描述：
 */
public interface MvpView {
    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();

    /**
     * @param data 当数据请求成功后，调用此接口显示 数据源
     */
    void showData(String data);

    /**
     * @param msg 当数据请求失败后，调用此接口提示 失败原因
     */
    void showFailureMessage(String msg);

    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage();
}
