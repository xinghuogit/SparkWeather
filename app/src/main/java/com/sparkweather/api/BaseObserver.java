package com.sparkweather.api;

import com.google.gson.JsonParseException;
import com.sparkweather.mvp.base.BaseView;
import com.sparkweather.mvp.model.base.BaseModel;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * 日期：2019/3/9 15:19
 * 创建：李加蒙
 * 描述：主要处理了常见的错误
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {
    protected BaseView view;

    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        try {
            BaseModel model = (BaseModel) t;
            if (model.getErrCode() == 0) {
                onSuccess(t);
            } else {
                if (view != null) {
                    view.onErrorCode(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.toString());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (view != null) {
            view.hideLoading();
        }
        if (e instanceof HttpException) {
            onException(BAD_NETWORK); //HTTP错误
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            onException(CONNECT_ERROR);//连接错误
        } else if (e instanceof InterruptedIOException) {
            onException(CONNECT_TIMEOUT); //连接超时
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            onException(PARSE_ERROR);// 解析错误
        } else {
            if (e != null) {
                onError(e.toString());
            } else {
                onError("未知错误");
            }
        }

    }

    public void onException(int unknownError) {
        switch (unknownError) {
            case PARSE_ERROR:// 解析错误
                onError("解析数据失败");
                break;
            case BAD_NETWORK: //HTTP错误
                onError("网络问题");
                break;
            case CONNECT_ERROR://连接错误
                onError("连接错误");
                break;
            case CONNECT_TIMEOUT://连接超时
                onError("连接超时");
                break;
        }
    }

    @Override
    public void onComplete() {
        if (view != null) {
            view.hideLoading();
        }
    }


    public abstract void onSuccess(T o);

    public abstract void onError(String msg);
}
