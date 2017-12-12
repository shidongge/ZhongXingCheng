package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.dianpu.DianPuActivity;
import us.mifeng.zhongxingcheng.dianpu.SimpleViewPagerIndicator;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.Util;
import us.mifeng.zhongxingcheng.view.MyScrollView;

/**
 * Created by shido on 2017/11/1.
 */
/**
 * 店铺界面
 */
public class WuPing extends Activity implements View.OnClickListener {
    private SimpleViewPagerIndicator mIndicator;
    private ImageView back;
    private LinearLayout fenxiang, yunfei;
    private String appKey = "wx2629c8fc2e7dd404";
    private IWXAPI api;
    //聊天
    private int mTargetScene0 = SendMessageToWX.Req.WXSceneSession;
    //朋友圈
    private int mTargetScene1 = SendMessageToWX.Req.WXSceneTimeline;
    private ImageView img;
    private RelativeLayout shang;
    private LinearLayout zhiying,dpmz,shou,baobei;
    private TextView jianjie,jieshao;
    private String url = "http://192.168.1.111:1003/shop/shop_goodsinfo/41";
    private static final String TAG = "WuPing";
    private Button goumai,jrgwc;
    private Button dianpu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuping);
        String shipId = getIntent().getStringExtra("shopId");
        initLianWang();
        initView();
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("","");
        OkUtils.UploadSJ(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void initView() {
        MyScrollView myscr = (MyScrollView) findViewById(R.id.wuping_myscr);
        back = (ImageView) findViewById(R.id.wuping_back);
        yunfei = (LinearLayout) findViewById(R.id.wuping_yunfei);
        img = (ImageView) findViewById(R.id.wuping_img);
        fenxiang = (LinearLayout) findViewById(R.id.wuping_fenxiang);
        shang = (RelativeLayout) findViewById(R.id.wuping_shang);
        zhiying = (LinearLayout) findViewById(R.id.wuping_zhiying);
        jianjie = (TextView) findViewById(R.id.wuping_jianjie);
        jieshao = (TextView) findViewById(R.id.wuping_jieshao);
        dpmz = (LinearLayout) findViewById(R.id.wuping_dpmz);
        baobei = (LinearLayout) findViewById(R.id.wuping_baobei);
        shou = (LinearLayout) findViewById(R.id.wuping_shou);
        goumai = (Button) findViewById(R.id.wuping_goumai);
        dianpu = (Button) findViewById(R.id.wuping_dianpu);
        jrgwc = (Button) findViewById(R.id.wuping_jrgwc);

        goumai.setOnClickListener(this);
        Glide.with(WuPing.this).load("http://47.94.144.186:8080/uploads/goodsThumb/20170814/150269331870.jpg").into(img);
        back.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        yunfei.setOnClickListener(this);
        dianpu.setOnClickListener(this);
        jrgwc.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wuping_back:
                finish();
                break;
            case R.id.wuping_fenxiang:
                //fenxiang(mTargetScene0);
                break;
            case R.id.wuping_yunfei:

                break;
            case R.id.wuping_goumai:
                startActivity(new Intent(WuPing.this,TJDD.class));
                break;
            case R.id.wuping_dianpu:
                startActivity(new Intent(WuPing.this,DianPuActivity.class));
                break;
            case R.id.wuping_jrgwc:
                ToSi.show(WuPing.this,"加入购物车成功");
                break;
        }
    }

    public void fenxiang(int i) {
        api = WXAPIFactory.createWXAPI(this, appKey);
        api.registerApp(appKey);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "http://www.qq.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        //这个是标题
        msg.title = "WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        //这里显示内容
        msg.description = "WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        //分享的图片
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = Util.bmpToByteArray(bmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        //要分享的好友或朋友圈
        req.scene = i;
        api.sendReq(req);

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}