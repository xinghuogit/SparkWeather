package com.sparkweather.mvp.test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sparkweather.R;

/**
 * 日期：2019/3/8 20:31
 * 创建：李加蒙
 * 描述：
 */
public class MvpActivity extends AppCompatActivity implements MvpView {
    private ProgressDialog progressDialog;
    private TextView text;

    private MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        text = findViewById(R.id.text);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("数据");

        mvpPresenter = new MvpPresenter(this);
    }
    
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(String data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
