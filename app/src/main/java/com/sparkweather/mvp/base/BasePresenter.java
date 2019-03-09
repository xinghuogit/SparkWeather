package com.sparkweather.mvp.base;

import com.sparkweather.api.ApiRetrofit;
import com.sparkweather.api.ApiServer;
import com.sparkweather.api.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 日期：2019/3/9 10:53
 * 创建：李加蒙
 * 描述：Presenter的基类 因为已经定义好了BaseView，
 * 所以我们希望Presenter中持有的View都是BaseView的子类，这里同样需要泛型来约束：
 */
public class BasePresenter<V extends BaseView> {

    private CompositeDisposable compositeDisposable;

    /**
     * 绑定的view
     */
    public V baseView;

    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiServer();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }


    /**
     * @param baseView 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        removeDisposable();
        baseView = null;
    }

    /**
     * 是否与View建立连接,每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return baseView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable(observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer));
        }
    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable = null;
        }
    }
}
