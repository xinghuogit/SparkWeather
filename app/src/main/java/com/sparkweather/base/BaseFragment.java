package com.sparkweather.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    public abstract void initView(View view);

    public abstract void initData();

    public <T extends View> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
