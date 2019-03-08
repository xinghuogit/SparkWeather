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

    public static final String Success = "Success";
    public static final String Failure = "Failure";
    public static final String Error = "Error";

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

    public void getDataForSuccess() {
        mvpPresenter.getData(Success);
    }

    public void getDataForFailure() {
        mvpPresenter.getData(Failure);
    }

    public void getDataForError() {
        mvpPresenter.getData(Error);
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
    public void showData(String data) {
        text.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {
        text.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        text.setText("网络请求数据出现异常");
    }
}
