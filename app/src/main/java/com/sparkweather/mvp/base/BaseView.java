package com.sparkweather.mvp.base;

import android.content.Context;

import com.sparkweather.mvp.model.base.BaseModel;

/**
 * 日期：2019/3/9 10:51
 * 创建：李加蒙
 * 描述：View基类
 */
public interface BaseView {
    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();

    /**
     * @param msg 当数据请求失败后，调用此接口提示 失败原因
     */
    void showToast(String msg);

    /**
     * @param msg 当数据请求失败后，调用此接口提示 失败原因
     */
    void showFailureMessage(String msg);

    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage(String msg);

    /**
     * @param model 错误码
     */
    void onErrorCode(BaseModel model);

    /**
     * @return 上下文 获取上下文
     */
    Context getContext();
}
