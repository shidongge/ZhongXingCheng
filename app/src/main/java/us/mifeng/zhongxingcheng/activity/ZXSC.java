package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
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
 * Created by shido on 2017/11/1.
 */

/**
 * 中心商城界面
 */
public class ZXSC extends Activity {
    private ProgressDialog progressDialog;
    private WebView zxsc;
    private String session;
    private String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc);
        SharedUtils sharedUtils = new SharedUtils();
        session = sharedUtils.getShared("sessionId", this);
        progressDialog = new ProgressDialog(ZXSC.this);
        url = WangZhi.ZXC;
        initView();
    }

    private void initView() {
        zxsc = (WebView) findViewById(R.id.wv_zxsc);
        synCookies(ZXSC.this,url);
        WebSettings settings = zxsc.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        zxsc.setWebViewClient(new WebViewClient(){
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


        zxsc.loadUrl(url);
    }
    public void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, session);//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && zxsc.canGoBack()) {
            zxsc.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}