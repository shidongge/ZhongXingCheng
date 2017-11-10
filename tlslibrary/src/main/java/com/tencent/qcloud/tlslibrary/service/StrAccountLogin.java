package com.tencent.qcloud.tlslibrary.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tencent.qcloud.tlslibrary.activity.ImgCodeActivity;
import com.tencent.qcloud.tlslibrary.helper.Util;

import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by weijunyi@tencent.com on 2016/4/28.
 */
public class StrAccountLogin {
    private static final String TAG = "StrAccountLogin";
    private Context context;
    private LoginListener listener;

    public StrAccountLogin(Context context) {
        this.context = context;
    }

    public StrAccountLogin(Context context, LoginListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void doStrAccountLogin1(String id, String password) {
        TLSService.getInstance().TLSPwdLogin(id, password, new TLSPwdLoginListener() {
            @Override
            public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {
                listener.login();
            }

            @Override
            public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {

            }

            @Override
            public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {

            }

            @Override
            public void OnPwdLoginFail(TLSErrInfo tlsErrInfo) {

            }

            @Override
            public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {

            }
        });
    }

    public void doStrAccountLogin(String id, String password) {
        TLSService.getInstance().TLSPwdLogin(id, password, new TLSPwdLoginListener() {

            @Override
            public void OnPwdLoginSuccess(TLSUserInfo userInfo) {
                Log.e(TAG, "OnPwdLoginSuccess: ");
                TLSService.getInstance().setLastErrno(0);
                ((Activity) context).setResult(Activity.RESULT_OK);
                ((Activity) context).finish();

            }

            @Override
            public void OnPwdLoginReaskImgcodeSuccess(byte[] picData) {
                ImgCodeActivity.fillImageview(picData);
            }

            @Override
            public void OnPwdLoginNeedImgcode(byte[] picData, TLSErrInfo errInfo) {
                Intent intent = new Intent(context, ImgCodeActivity.class);
                intent.putExtra(Constants.EXTRA_IMG_CHECKCODE, picData);
                intent.putExtra(Constants.EXTRA_LOGIN_WAY, Constants.USRPWD_LOGIN);
                context.startActivity(intent);
            }

            @Override
            public void OnPwdLoginFail(TLSErrInfo errInfo) {
                TLSService.getInstance().setLastErrno(-1);
                Util.notOK(context, errInfo);
            }

            @Override
            public void OnPwdLoginTimeout(TLSErrInfo errInfo) {
                TLSService.getInstance().setLastErrno(-1);
                Util.notOK(context, errInfo);
            }
        });
    }

    public interface LoginListener {
        void login();
    }

}
