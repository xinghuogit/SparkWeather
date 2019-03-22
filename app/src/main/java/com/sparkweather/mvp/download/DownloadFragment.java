package com.sparkweather.mvp.download;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sparkweather.R;
import com.sparkweather.mvp.base.BasePresenter;
import com.sparkweather.mvp.base.MvpBaseFragment;
import com.sparkweather.mvp.login.LoginPresenter;
import com.sparkweather.mvp.model.base.BaseModel;

import java.io.File;

/**
 * 日期：2019/3/11 9:54
 * 创建：李加蒙
 * 描述：
 */
public class DownloadFragment extends MvpBaseFragment<DownloadPresenter> implements DownloadView, View.OnClickListener {
    private static final String TAG = "DownloadFragment";

    private Button button;
    private Button buttonUpLoad;

    private String dir;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_download;
    }

    @Override
    public DownloadPresenter createPresenter() {
        return new DownloadPresenter(DownloadFragment.this);
    }

    @Override
    public void initView(View view) {
        button = findView(view, R.id.button);
        buttonUpLoad = findView(view, R.id.buttonUpLoad);
    }

    @Override
    public void initData() {
        button.setOnClickListener(this);
        buttonUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.upLoadFile("/storage/emulated/0/position.txt");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                downFile();
                break;
        }
    }

    private void downFile() {
        String url = "http://download.sdk.mob.com/apkbus.apk";
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {// 检查是否有存储卡
            dir = Environment.getExternalStorageDirectory() + "/spark/";
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
        }
        presenter.downFile(url, dir + "app-debug.apk");
    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public void onSuccess(File file) {
        Log.i(TAG, "onSuccess: file.getPath():" + file.getPath());
    }

    @Override
    public void onSuccess(String str) {
        Log.i(TAG, "onSuccess: str:" + str);
    }

    @Override
    public void onError(String str) {
        Log.i(TAG, "onError: str:" + str);
    }

    @Override
    public void onProgress(final long totalSize, final long downSize) {
        Log.i(TAG, "onProgress: totalSize:" + totalSize + " downSize:" + downSize);
        if (button != null) {
            button.post(new Runnable() {
                @Override
                public void run() {
                    DownloadFragment.super.onProgress(totalSize, downSize);
                }
            });
        }
    }

}
