package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;


/**
 * Created by shido on 2017/10/24.
 */

/**
 * 签到界面
 */
public class QianDao extends Activity {
    private static final String TAG = "QianDao";
    private WebView qiandao;
    private String session;
    private String url;
    private ProgressDialog progressDialog;

    //http://192.168.1.111:1003/userinfo/user_center?token=7f0c572b428692bf485ccff10d0b9dfa
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrqd);
        progressDialog = new ProgressDialog(QianDao.this);
        SharedUtils sharedUtils = new SharedUtils();
        session = sharedUtils.getShared("sessionId", this);
        url = WangZhi.QIANDAO;
        initView();
    }

    private void initView() {
        qiandao = (WebView) findViewById(R.id.wv_mrqd);
        synCookies(QianDao.this, url);
        WebSettings settings = qiandao.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);

        qiandao.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
            }
        });

        qiandao.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && qiandao.canGoBack()) {
            qiandao.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void synCookies(Context context, String url) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(context);
        }

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, session);//cookies是在HttpClient中获得的cookie
        String test  = cookieManager.getCookie(url);
        Log.e(TAG, "synCookies: "+test );
        CookieSyncManager.getInstance().sync();
    }
}