package com.sparkweather.mvp.textspeech;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sparkweather.R;
import com.sparkweather.base.BaseFragment;

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
