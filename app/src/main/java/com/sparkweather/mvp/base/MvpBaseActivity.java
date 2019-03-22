package com.sparkweather.mvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sparkweather.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * 日期：2019/3/9 10:57
 * 创建：李加蒙
 * 描述：Activity基类
 */
public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    private ProgressDialog progressDialog;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    /**
     * 获取Presenter实例，子类实现
     */
    public abstract P createPresenter();


    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("数据请求");
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void showErrorMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void onProgress(long totalSize, long downSize) {

    }

    @Override
    public Context getContext() {
        return MvpBaseActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
