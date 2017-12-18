package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/12/18.
 */

public class HuoDong extends Activity {

    private String wangzhi;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huodong);
        progressDialog = new ProgressDialog(HuoDong.this);
        wangzhi = getIntent().getStringExtra("wangzhi");
        initView();
    }

    private void initView() {
        WebView web = (WebView) findViewById(R.id.hd_web);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {
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
        web.loadUrl(wangzhi);
    }
}
