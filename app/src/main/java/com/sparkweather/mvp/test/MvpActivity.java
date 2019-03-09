package com.sparkweather.mvp.test;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.sparkweather.R;
import com.sparkweather.mvp.test.base.BaseActivity;

/**
 * 日期：2019/3/8 20:31
 * 创建：李加蒙
 * 描述：
 */
public class MvpActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameC, new MvpFragment())
                .commit();
    }
}
