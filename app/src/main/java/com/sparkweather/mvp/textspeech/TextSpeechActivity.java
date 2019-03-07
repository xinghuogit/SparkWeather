package com.sparkweather.mvp.textspeech;

import android.os.Bundle;

import com.sparkweather.R;
import com.sparkweather.base.BaseActivity;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：从文字到语音 Activity
 */
public class TextSpeechActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.frameC, new TextSpeechFragment())
                .commit();
    }
}
