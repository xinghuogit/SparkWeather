package com.sparkweather.mvp.test;

import android.view.View;
import android.widget.TextView;

import com.sparkweather.R;
import com.sparkweather.mvp.test.base.BaseFragment;

/**
 * 日期：2019/3/9 11:25
 * 创建：李加蒙
 * 描述：
 */
public class MvpFragment extends BaseFragment implements MvpView, View.OnClickListener {
    private TextView text;
    private TextView getDataForSuccess;
    private TextView getDataForFailure;
    private TextView getDataForError;

    private MvpPresenter mvpPresenter;

    public static final String Success = "Success";
    public static final String Failure = "Failure";
    public static final String Error = "Error";

    @Override
    public int getContentViewId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initView(View view) {
        text = view.findViewById(R.id.text);
        getDataForSuccess = view.findViewById(R.id.getDataForSuccess);
        getDataForFailure = view.findViewById(R.id.getDataForFailure);
        getDataForError = view.findViewById(R.id.getDataForError);
    }


    @Override
    protected void initData() {
        mvpPresenter = new MvpPresenter();
        mvpPresenter.attachView(this);

        getDataForSuccess.setOnClickListener(this);
        getDataForFailure.setOnClickListener(this);
        getDataForError.setOnClickListener(this);
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
        }
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mvpPresenter.detachView();
    }
}
