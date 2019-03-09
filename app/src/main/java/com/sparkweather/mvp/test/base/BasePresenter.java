package com.sparkweather.mvp.test.base;

import com.sparkweather.mvp.test.MvpView;

/**
 * 日期：2019/3/9 10:53
 * 创建：李加蒙
 * 描述：Presenter的基类 因为已经定义好了BaseView，
 * 所以我们希望Presenter中持有的View都是BaseView的子类，这里同样需要泛型来约束：
 */
public class BasePresenter<V extends BaseView> {

    /**
     * 绑定的view
     */
    private V mvpView;


    /**
     * @param mvpView 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        mvpView = null;
    }

    /**
     * 是否与View建立连接,每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mvpView;
    }
}
