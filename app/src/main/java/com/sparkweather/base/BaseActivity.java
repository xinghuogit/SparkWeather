package com.sparkweather.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
