package com.sparkweather.mvp.textspeech;

import android.content.Context;
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
public class TextSpeechFragment extends BaseFragment implements View.OnClickListener, TextSpeechContract.View {

    private Button btStart;
    private Button btEnd;

    private TextSpeechContract.Presenter mPresenter;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btStart:
                mPresenter.startPlay();
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


    @Override
    public void setPresenter(TextSpeechContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }
}
