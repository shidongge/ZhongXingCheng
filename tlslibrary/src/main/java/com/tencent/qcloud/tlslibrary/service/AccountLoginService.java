package com.tencent.qcloud.tlslibrary.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tencent.qcloud.tlslibrary.activity.ImgCodeActivity;
import com.tencent.qcloud.tlslibrary.helper.Util;
import com.tencent.qcloud.tlslibrary.utils.OkUtils;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by dgy on 15/8/12.
 */
public class AccountLoginService {

    private final static String TAG = "AccountLoginService";

    private Context context;
    private EditText txt_username;
    private EditText txt_password;

    private String username;
    private String password;
    private final String LOGIN_PSW = "123456789";
    private TLSService tlsService;
    public  static PwdLoginListener pwdLoginListener;
    private StrAccRegListener strAccRegListener;

    public AccountLoginService(Context context,
                               EditText txt_username,
                               EditText txt_password,
                               Button btn_login) {
        this.context = context;
        this.txt_username = txt_username;
        this.txt_password = txt_password;

        tlsService = TLSService.getInstance();
        pwdLoginListener = new PwdLoginListener();
        strAccRegListener = new StrAccRegListener();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = AccountLoginService.this.txt_username.getText().toString();
                password = AccountLoginService.this.txt_password.getText().toString();

                // 验证用户名和密码的有效性
                if (username.length() == 0 || password.length() == 0) {
                    Util.showToast(AccountLoginService.this.context, "用户名密码不能为空");
                    return;
                }
                else {
                    HashMap<String,String> map = new HashMap<>();
                    map.put("mobile",username);
                    map.put("pwd",password);
                    //陈哥接口 192.168.1.130:8081
                    //小东接口 192.168.1.111:1003
                    OkUtils.UploadSJ("http://www.taogt.cn/app/check_login", map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
//                            Log.e(TAG, "onResponse: "+response.body().string() );
                            String string = response.body().string();
                            Message mess = hand.obtainMessage();
                            mess.obj=string;
                            mess.what=200;
                            hand.sendMessage(mess);
                        }
                    });
                }

            }
        });
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "jsonObject: " + jsonObject);
                    JSONObject data = jsonObject.getJSONObject("data");
                    String msg1 = data.getString("msg");
                    if ("0".equals(msg1)){
                        String sessionId = data.getString("sessionId");
                        String token = data.getString("token");
                        String mobile = data.getString("mobile");
                        String id = data.getString("id");
                        SharedUtils sharedUtils = new SharedUtils();
                        Log.e(TAG, "handleMessage: "+sessionId );
                        sharedUtils.saveShared("sessionId","sessionId="+sessionId,context);
                        sharedUtils.saveShared("token",token,context);
                        sharedUtils.saveShared("mobile",mobile,context);
                        sharedUtils.saveShared("zxcid",id,context);
                        username = username + "a";
                        sharedUtils.saveShared("id",username,context);
                        tlsService.TLSPwdLogin(username, LOGIN_PSW, pwdLoginListener);
                        registerTencentIM(username, LOGIN_PSW, strAccRegListener);
                    }else if ("1".equals(msg1)){
                        Util.showToast(context, "用户不存在");
                    }else if ("2".equals(msg1)){
                        Util.showToast(context,"账号或密码不正确");
                    }else if ("3".equals(msg1)){
                        Util.showToast(context,"用户已被禁止登录");
                    }else {
                        Util.showToast(context,"参数不齐");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * TencentIM请求注册回调
     */
    private void registerTencentIM(String phone, String psw, StrAccRegListener strAccRegListener) {
        int result = tlsService.TLSStrAccReg(phone, psw, strAccRegListener);
        if (result == TLSErrInfo.INPUT_INVALID) {
            Util.showToast(this.context, "帐号不合法");
        }
    }


    class PwdLoginListener implements TLSPwdLoginListener {
        @Override
        public void OnPwdLoginSuccess(TLSUserInfo userInfo) {
            Util.showToast(context, "登录成功");
            TLSService.getInstance().setLastErrno(0);
            AccountLoginService.this.jumpToSuccActivity();
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
    }

    void jumpToSuccActivity() {
        Log.d(TAG, "jumpToSuccActivity");
        String thirdappPackageNameSucc = Constants.thirdappPackageNameSucc;
        String thirdappClassNameSucc = Constants.thirdappClassNameSucc;

        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_LOGIN_WAY, Constants.USRPWD_LOGIN);
        intent.putExtra(Constants.EXTRA_USRPWD_LOGIN, Constants.USRPWD_LOGIN_SUCCESS);
        if (thirdappPackageNameSucc != null && thirdappClassNameSucc != null) {
            intent.setClassName(thirdappPackageNameSucc, thirdappClassNameSucc);
            context.startActivity(intent);
        } else {
            Log.d(TAG, "finish current activity");
            ((Activity) context).setResult(Activity.RESULT_OK, intent);
            ((Activity) context).finish();
        }
    }

    /**
     * TencentIM 请求注册回调
     */
    class StrAccRegListener implements TLSStrAccRegListener {
        @Override
        public void OnStrAccRegSuccess(TLSUserInfo userInfo) {
            Util.showToast(context, "成功注册 " + userInfo.identifier);
            TLSService.getInstance().setLastErrno(0);
            StrAccountLogin login = new StrAccountLogin(context);
            Log.e(TAG, "OnStrAccRegSuccess: " + "register");
            //TODO
            login.doStrAccountLogin(username, LOGIN_PSW);

        }

        @Override
        public void OnStrAccRegFail(TLSErrInfo errInfo) {
            Log.e(TAG, "OnStrAccRegSuccess: " + "register1");
            Util.notOK(context, errInfo);
        }

        @Override
        public void OnStrAccRegTimeout(TLSErrInfo errInfo) {
            Log.e(TAG, "OnStrAccRegSuccess: " + "register2");
            Util.notOK(context, errInfo);
        }
    }

}
