package com.sparkweather.mvp.textspeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sparkweather.R;
import com.sparkweather.base.BaseFragment;

import java.util.Locale;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：
 */
public class TextSpeechFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "TextSpeechFragment";

    private Button btStart;
    private Button btEnd;

    private TextToSpeech tts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_text_speech, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btStart:
                tts.speak("人大二次会议新闻中心7日9时举行记者会，邀请财政部部长", TextToSpeech.QUEUE_FLUSH, null);
                tts.stop();
                break;
            case R.id.btEnd:
                break;
        }
    }

    private void initView(View view) {
        btStart = findView(view, R.id.btStart);
        btEnd = findView(view, R.id.btEnd);
        btStart.setOnClickListener(this);
        btEnd.setOnClickListener(this);
    }


    private void initData() {
        tts = new TextToSpeech(getContext(), new OnInitListener1());
    }

    class OnInitListener1 implements TextToSpeech.OnInitListener {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) { //转换成功
                int result = tts.setLanguage(Locale.CHINESE);//默认设定语言为中文
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) { // 缺失数据 或者不支持的语言
                    Log.i(TAG, "onInit: 不支持中文");
                    Toast.makeText(getActivity(), "不支持中文", Toast.LENGTH_SHORT).show();
                    tts.setLanguage(Locale.US);//不支持中文就将语言设置为英文
                } else {
                    Log.i(TAG, "onInit: 支持中文");
                }
            }
        }
    }

}
