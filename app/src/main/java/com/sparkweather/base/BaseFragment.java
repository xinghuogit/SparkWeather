package com.sparkweather.base;

import androidx.fragment.app.Fragment;
import android.view.View;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：Fragment基类
 */
public class BaseFragment extends Fragment {

    protected <T extends View> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
