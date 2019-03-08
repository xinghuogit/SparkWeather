package com.sparkweather.mvp.test;

/**
 * 日期：2019/3/8 20:26
 * 创建：李加蒙
 * 描述：
 */
public class MvpPresenter {
    private MvpView mvpView;

    public MvpPresenter(MvpView mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * @param params 获取网络数据 参数
     */
    public void getData(String params) {
        mvpView.showLoading();//显示正在加载进度条
        MvpModel.getNetData(params, new MvpCallback() { // 调用Model请求数据
            @Override
            public void onSuccess(String data) {
                mvpView.showData(data);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.showFailureMessage(msg);
            }

            @Override
            public void onError() {
                mvpView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                mvpView.hideLoading();
            }
        });
    }

}
