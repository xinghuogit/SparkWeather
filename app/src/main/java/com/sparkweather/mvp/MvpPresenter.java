package com.sparkweather.mvp;

import com.library.common.mvp.base.view.BasePresenter;
import com.library.common.mvp.base.model.Callback;
import com.library.common.mvp.base.model.DataModel;
import com.sparkweather.mvp.model.MvpModel;


/**
 * 日期：2019/3/8 20:26
 * 创建：李加蒙
 * 描述：
 */
public class MvpPresenter extends BasePresenter<MvpView> {
    public MvpPresenter(MvpView baseView) {
        super(baseView);
    }

    /**
     * @param params 获取网络数据 参数
     */
    public void getData(String params) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();//显示正在加载进度条
        DataModel.request(MvpModel.class)// 设置请求标识token
                .params(params) // 添加请求参数，没有则不添加
                .execute(new Callback() {// 注册监听回调
                    @Override
                    public void onSuccess(Object data) {
                        if (isViewAttached()) {
                            getView().showData((String) data);
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
                            getView().showErrorMessage("mParams[0]  Fragment 请检查网络");
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
