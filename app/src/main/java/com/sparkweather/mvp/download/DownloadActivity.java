package com.sparkweather.mvp.download;

import android.os.Bundle;

import com.library.common.utils.LogUtils;
import com.sparkweather.R;
import com.library.common.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * 日期：2019/3/11 9:52
 * 创建：李加蒙
 * 描述：
 */
public class DownloadActivity extends BaseActivity {
    private static final String TAG = "DownloadActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        LogUtils.i(TAG, "onCreate");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameC, new DownloadFragment())
                .commit();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
