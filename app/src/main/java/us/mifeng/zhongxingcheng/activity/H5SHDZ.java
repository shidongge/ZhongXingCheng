package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by shido on 2017/11/8.
 */

public class H5SHDZ extends Activity {

    private String sessionId;
    private ProgressDialog progressDialog;
    private WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5shdz);
        SharedUtils sharedUtils = new SharedUtils();
        progressDialog = new ProgressDialog(H5SHDZ.this);
        sessionId = sharedUtils.getShared("sessionId", H5SHDZ.this);
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.h5shdz_wv);
        synCookies(H5SHDZ.this, WangZhi.ZHUJI+"/shop/shop_address_list");
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient(){
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
        wv.loadUrl(WangZhi.ZHUJI+"/shop/shop_address_list");

    }
    public void synCookies(Context context, String url) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(context);
        }

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, sessionId);//cookies是在HttpClient中获得的cookie
        String test  = cookieManager.getCookie(url);
        CookieSyncManager.getInstance().sync();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
            wv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
