package com.sparkweather.mvp;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sparkweather.R;
import com.library.common.mvp.base.view.BasePresenter;
import com.library.common.mvp.base.view.MvpBaseFragment;
import com.library.common.mvp.base.model.BaseModel;

import java.util.Locale;

/**
 * 日期：2019/3/9 11:25
 * 创建：李加蒙
 * 描述：
 */
public class MvpFragment extends MvpBaseFragment implements MvpView, View.OnClickListener {
    private static final String TAG = "MvpFragment";

    private TextView text;
    private TextView getDataForSuccess;
    private TextView getDataForFailure;
    private TextView getDataForError;
    private TextView start;

    private MvpPresenter mvpPresenter;

    public static final String Success = "Success";
    public static final String Failure = "Failure";
    public static final String Error = "Error";

    private TextToSpeech tts;

    @Override
    public int getContentViewId() {
        return R.layout.activity_mvp;
    }


    @Override
    public BasePresenter createPresenter() {
        return new MvpPresenter(MvpFragment.this);
    }
    
    @Override
    public void initView(View view) {
        text = view.findViewById(R.id.text);
        getDataForSuccess = view.findViewById(R.id.getDataForSuccess);
        getDataForFailure = view.findViewById(R.id.getDataForFailure);
        getDataForError = view.findViewById(R.id.getDataForError);
        start = view.findViewById(R.id.start);
    }


    @Override
    public void initData() {
        getDataForSuccess.setOnClickListener(this);
        getDataForFailure.setOnClickListener(this);
        getDataForError.setOnClickListener(this);
        start.setOnClickListener(this);

        tts = new TextToSpeech(getContext(), new OnInitListener1());
    }

    @Override
    public void onErrorCode(BaseModel model) {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getDataForSuccess:
                mvpPresenter.getData(Success);
                break;
            case R.id.getDataForFailure:
                mvpPresenter.getData(Failure);
                break;
            case R.id.getDataForError:
                mvpPresenter.getData(Error);
                break;
            case R.id.start:
                tts.speak("人大二次会议新闻中心7日9时举行记者会，邀请财政部部长", TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }
}
