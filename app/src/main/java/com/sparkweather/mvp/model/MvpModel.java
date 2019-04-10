package com.sparkweather.mvp.model;

import android.os.Handler;

import com.sparkweather.mvp.MvpFragment;
import com.library.common.mvp.base.model.BaseModel;
import com.library.common.mvp.base.model.Callback;


/**
 * 日期：2019/3/8 20:17
 * 创建：李加蒙
 * 描述：
 */
public class MvpModel extends BaseModel<String> {

    @Override
    public void execute(final Callback<String> callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (mParams[0]) {
                    case MvpFragment.Success:
                        callback.onSuccess("mParams[0] Fragment根据参数“" + mParams[0] + "”的请求网络数据成功");
                        break;
                    case MvpFragment.Failure:
                        callback.onFailure("mParams[0] Fragment根据参数“" + mParams[0] + "”的请求网络数据成功");
                        break;
                    case MvpFragment.Error:
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }
        }, 2000);
    }
}
