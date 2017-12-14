package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/1.
 */

public class XinWen_NeiRong extends Activity {

    private WebView wv;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwzxnr);
        url = getIntent().getStringExtra("url");
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.xwzxnr_wv);
        wv.loadUrl(url);
    }
}
