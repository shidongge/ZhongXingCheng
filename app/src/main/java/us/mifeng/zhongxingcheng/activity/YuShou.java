package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.SharedUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by shido on 2017/11/1.
 */

/**
 * 双十一预界面
 */
public class YuShou extends Activity {

    private WebView yushou;
    private String session;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yushou);
        SharedUtils sharedUtils = new SharedUtils();
        session = sharedUtils.getShared("sessionId", this);
        initView();
    }

    private void initView() {
        yushou = (WebView) findViewById(R.id.wv_yushou);

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
