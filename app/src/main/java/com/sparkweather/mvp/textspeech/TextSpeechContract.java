package com.sparkweather.mvp.textspeech;

import android.content.Context;

import com.sparkweather.base.BasePresenter;
import com.sparkweather.base.BaseView;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：这指定视图和主持人之间的合同。
 */
public interface TextSpeechContract {
    interface View extends BaseView<Presenter> {
        Context getContext();
    }

    interface Presenter extends BasePresenter {
        void startPlay();

        void endPlay();
    }
}
