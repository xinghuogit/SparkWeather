package com.sparkweather.mvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sparkweather.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 日期：2019/3/9 11:25
 * 创建：李加蒙
 * 描述：
 */
public abstract class MvpBaseFragment extends BaseFragment implements BaseView {
    protected Context mContext;
    protected View mRootView;

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        return mRootView;
    }

    public abstract int getContentViewId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        initPresenter();
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
    }

    /**
     * 获取Presenter实例，子类实现
     */
    public abstract BasePresenter getPresenter();


    /**
     * 初始化Presenter的实例，子类实现
     */
    public abstract void initPresenter();


    @Override
    public void showLoading() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void showErrorMessage(String msg) {
        showToast(msg);
    }

    @Override
    public Context getContext() {
        checkActivityAttached();
        return getActivity();
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }
}
