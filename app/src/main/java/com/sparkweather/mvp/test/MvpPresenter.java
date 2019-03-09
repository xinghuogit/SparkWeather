package com.sparkweather.mvp.test;

import com.sparkweather.mvp.test.base.BasePresenter;

/**
 * 日期：2019/3/8 20:26
 * 创建：李加蒙
 * 描述：
 */
public class MvpPresenter extends BasePresenter<MvpView> {
    /**
     * @param params 获取网络数据 参数
     */
    public void getData(String params) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();//显示正在加载进度条
        MvpModel.getNetData(params, new MvpCallback() { // 调用Model请求数据
            @Override
            public void onSuccess(String data) {
                if (isViewAttached()) {
                    getView().showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    getView().showFailureMessage(msg);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    getView().showErrorMessage("Fragment 请检查网络");
                }
            }

            @Override
            public void onComplete() {
                if (isViewAttached()) {
                    getView().hideLoading();
                }
            }
        });
    }

}
