package com.sparkweather.mvp.test.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 日期：2019/3/9 10:57
 * 创建：李加蒙
 * 描述：Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("数据请求");
    }

    @Override
    public void showLoading() {
        if (progressDialog != null && !progressDialog.isShowing()) {
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
    public Context getContext() {
        return BaseActivity.this;
    }
}
