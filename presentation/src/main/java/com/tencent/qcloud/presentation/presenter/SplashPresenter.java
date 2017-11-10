package com.tencent.qcloud.presentation.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.tencent.qcloud.presentation.viewfeatures.SplashView;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;


/**
 * 闪屏界面逻辑
 */
public class SplashPresenter {
    SplashView view;
    private SharedUtils utils;
    private static final String TAG = SplashPresenter.class.getSimpleName();

    public SplashPresenter(SplashView view) {
        this.view = view;
        utils = new SharedUtils();
    }


    /**
     * 加载页面逻辑
     */
    public void start(final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view.isUserLogin() /*&& !"".equals(utils.getShared("id", context))*/) {
                    view.navToHome();
                    Log.e(TAG, "run: 主界面");
                } else {
                    view.navToLogin();
                    Log.e(TAG, "run: 登录界面");
                }
            }
        }, 1000);
    }


}
