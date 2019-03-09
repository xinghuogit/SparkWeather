package com.sparkweather.mvp;

import android.os.Bundle;

import com.sparkweather.R;
import com.sparkweather.mvp.base.BasePresenter;
import com.sparkweather.mvp.base.MvpBaseActivity;
import com.sparkweather.mvp.model.base.BaseModel;
import com.sparkweather.mvp.MvpPresenter;

import androidx.annotation.Nullable;


/**
 * 日期：2019/3/8 20:31
 * 创建：李加蒙
 * 描述：
 */
public class MvpActivity extends MvpBaseActivity implements MvpView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameC, new MvpFragment())
                .commit();
    }

    @Override
    public BasePresenter createPresenter() {
        return new com.sparkweather.mvp.MvpPresenter(MvpActivity.this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void showData(String data) {

    }
}
