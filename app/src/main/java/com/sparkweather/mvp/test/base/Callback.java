package com.sparkweather.mvp.test.base;

/**
 * 日期：2019/3/9 10:50
 * 创建：李加蒙
 * 描述：回调基类
 */
public interface Callback<T> {
    /**
     * @param data 数据请求成功，返回的 数据
     */
    void onSuccess(T data);

    /**
     * @param msg 使用网络API接口请求方式时，
     *            虽然已经请求成功但是由于{@code msg}的原因无法正常返回数据。
     */
    void onFailure(String msg);

    /**
     * 请求数据失败，指在请求网络API接口请求方式时，
     * 出现无法联网、缺少权限，内存泄露等原因导致无法连接到请求数据源。
     */
    void onError();

    /**
     * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，
     * 通常做网络请求时可以在此处隐藏“正在加载”的等待控件。
     */
    void onComplete();

}