package com.sparkweather.mvp.login;

import com.sparkweather.api.BaseObserver;
import com.sparkweather.mvp.base.BasePresenter;

/**
 * 日期：2019/3/9 16:44
 * 创建：李加蒙
 * 描述：
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView loginView) {
        super(loginView);
    }

    public void login(String name, String pwd) {
        addDisposable(apiServer.LoginByRx(name, pwd), new BaseObserver(baseView) {
            @Override
            public void onSuccess(Object o) {
                baseView.onLoginSucc();
            }

            @Override
            public void onError(String msg) {
                baseView.showErrorMessage(msg);
            }
        });
    }

}
