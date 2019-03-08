package com.sparkweather.mvp.textspeech;

import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.sparkweather.utils.ObjectUtils;

import java.util.Locale;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：
 */
public class TextSpeechPresenter implements TextSpeechContract.Presenter {
    private static final String TAG = "TextSpeechPresenter";

    private final TextSpeechContract.View mTextSpeechContractView;

    private TextToSpeech tts;

    public TextSpeechPresenter(TextSpeechContract.View mTextSpeechContractView) {
        this.mTextSpeechContractView = ObjectUtils.checkNotNull(mTextSpeechContractView, "mTextSpeechContractView null");
        mTextSpeechContractView.setPresenter(this);
    }

    @Override
    public void startPlay() {
        tts.speak("人大二次会议新闻中心7日9时举行记者会，邀请财政部部长", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void endPlay() {

    }

    @Override
    public void start() {
        initData();
    }

    private void initData() {
        tts = new TextToSpeech(mTextSpeechContractView.getContext(), new OnInitListener1());
    }

    class OnInitListener1 implements TextToSpeech.OnInitListener {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) { //转换成功
                int result = tts.setLanguage(Locale.CHINESE);//默认设定语言为中文
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) { // 缺失数据 或者不支持的语言
                    Log.i(TAG, "onInit: 不支持中文");
                    tts.setLanguage(Locale.US);//不支持中文就将语言设置为英文
                } else {
                    Log.i(TAG, "onInit: 支持中文");
                }
            }
        }
    }
}
