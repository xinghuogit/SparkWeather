package com.sparkweather.base;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initView();

    public abstract void initData();

    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
