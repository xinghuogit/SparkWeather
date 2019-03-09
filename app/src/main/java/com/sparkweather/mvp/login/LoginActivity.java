package com.sparkweather.mvp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sparkweather.R;
import com.sparkweather.mvp.base.MvpBaseActivity;
import com.sparkweather.mvp.model.base.BaseModel;

import androidx.annotation.Nullable;

/**
 * 日期：2019/3/9 16:51
 * 创建：李加蒙
 * 描述：
 */
public class LoginActivity extends MvpBaseActivity<LoginPresenter> implements LoginView {
    private EditText name;
    private EditText pwd;
    private Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void initView() {
        name = findView(R.id.name);
        pwd = findView(R.id.pwd);
        submit = findView(R.id.submit);
    }

    @Override
    public void initData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(name.getText().toString(), pwd.getText().toString());
            }
        });
    }

    @Override
    public void onLoginSucc() {

    }

    @Override
    public void onErrorCode(BaseModel model) {

    }
}
