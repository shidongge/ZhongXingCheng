package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.SignUtils8;

/**
 * Created by shido on 2017/11/23.
 */

public class ZhiFu extends Activity {
    private static final String TAG = "ZhiFu";
    private String encode;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifu);
        getIntent().getStringExtra("");
        progressDialog = new ProgressDialog(ZhiFu.this);
        initView();
    }

    private void initView() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

// inputCharset=1&
// bgUrl=http://www.baidu.com&
// version=mobile1.0&
// language=1&signType=4&
// merchantAcctId=1020996764101&
// payerIdType=3&payerId=18994659500&
// orderId=20171117172953&orderAmount=1000&
// orderTime=20171117172953&payType=21-1
        WebView wv = (WebView) findViewById(R.id.zhifu_wv);
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
        String xxx = "inputCharset=1&"+
                "bgUrl=http://www.baidu.com&version=mobile1.0&" +
                "language=1&signType=4&" +
                "merchantAcctId=1020996764101&payerIdType=3&" +
                "payerId=18994659500&orderId=20171117172953&" +
                "orderAmount=100&orderTime=20171117172953&payType=21-1";
//        Log.e(TAG, "initView: " + xxx);
        String s = SignUtils8.signMsg(xxx);

        try {
            encode = URLEncoder.encode(s, "GBK");
        } catch (Exception e) {
            // TODO: handle exception
        }

        wv.loadUrl("https://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm?" + xxx +"&"+ "signMsg=" + encode);
        String url = wv.getUrl();
        Log.e(TAG, "initView: "+url );
    }
}
