package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/1.
 */

/**
 * 开通会员界面
 */
public class KTHY extends Activity {

    private WebView kthy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kthy);
        initView();
    }

    private void initView() {
        kthy = (WebView) findViewById(R.id.wv_kthy);
    }
}
