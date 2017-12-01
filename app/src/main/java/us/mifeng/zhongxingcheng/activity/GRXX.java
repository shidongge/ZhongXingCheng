package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by shido on 2017/10/30.
 */

/***
 * 个人信息界面
 */
public class GRXX extends Activity implements View.OnClickListener {
    private static final String TAG = "GRXX";
    private TextView nc, js, phone, zsxm, diqu, zhiye, shouru, aihao, zxjf;
    private TextView bt;
    private String grzx;
    private String token;
    private ImageView img, back, sznc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grxx);
        grzx = getIntent().getStringExtra("grzx");
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", GRXX.this);
        initView();
        initLianWang();
    }

    private void initLianWang() {
        String s = JiaMi.jdkBase64Encoder("mobile,userExp,realName,nickName,province,city,job,income,hobby,portrait");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("field", s);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: "+response.body().string() );
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 200;
                hand.sendMessage(mess);

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
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("userInfo");
                    String realName = msg1.getString("realName");
                    String mobile = msg1.getString("mobile");
                    String city = msg1.getString("city");
                    String job = msg1.getString("job");
                    String income = msg1.getString("income");
                    String hobby = msg1.getString("hobby");
                    String nickName = msg1.getString("nickName");
                    String userExp = msg1.getString("userExp");
                    String portrait = msg1.getString("portrait");
                    //TODO 隐藏手机号中间四位
                    String mobile2 = mobile;
                    String maskNumber = mobile.substring(0, 3) + "****" + mobile2.substring(7, mobile2.length());
                    phone.setText(maskNumber);
                    if ("".equals(job)) {
                        zhiye.setText("未设置");
                    } else {
                        zhiye.setText(job);
                    }
                    if ("".equals(realName)) {
                        zsxm.setText("未设置");
                    } else {
                        zsxm.setText(realName);
                    }
                    if ("".equals(portrait)){
                        diqu.setText("未设置");
                    }else {
                        if ("".equals(city)) {
                            diqu.setText("未设置");
                        } else {
                            diqu.setText(portrait+city);
                        }
                    }
                    if ("".equals(income)) {
                        shouru.setText("未设置");
                    } else {

                        shouru.setText(income);
                    }
                    if ("".equals(hobby)) {
                        aihao.setText("未设置");
                    } else {

                        aihao.setText(hobby);
                    }
                    if ("".equals(nickName)) {
                        nc.setText("未设置");
                    } else {

                        nc.setText(nickName);
                    }
                    if ("".equals(userExp)){
                        zxjf.setText("未设置");
                    }else {
                        zxjf.setText(userExp);
                    }
                    if ("".equals(portrait)){
                        img.setImageResource(R.mipmap.tx);
                    }else {

                    Glide.with(GRXX.this).load(WangZhi.TUPIAN+portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void initView() {
        nc = (TextView) findViewById(R.id.grxx_nc);
        js = (TextView) findViewById(R.id.grxx_js);
        phone = (TextView) findViewById(R.id.grxx_phone);
        zsxm = (TextView) findViewById(R.id.grxx_zsxm);
        diqu = (TextView) findViewById(R.id.grxx_diqu);
        zhiye = (TextView) findViewById(R.id.grxx_zhiye);
        shouru = (TextView) findViewById(R.id.grxx_shouru);
        aihao = (TextView) findViewById(R.id.grxx_aihao);
        bt = (TextView) findViewById(R.id.grzx_bt);
        img = (ImageView) findViewById(R.id.grxx_img);
        back = (ImageView) findViewById(R.id.grxx_back);
        zxjf = (TextView) findViewById(R.id.grxx_zxjf);
//        nc.setOnClickListener(this);
        sznc = (ImageView) findViewById(R.id.grxx_sznc);
        js.setOnClickListener(this);
        bt.setOnClickListener(this);
        back.setOnClickListener(this);
        sznc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grxx_sznc:
                startActivity(new Intent(GRXX.this, NinChen.class));
                break;
            case R.id.grxx_js:
                startActivity(new Intent(GRXX.this, JieShao.class));
                break;
            case R.id.grzx_bt:
                Intent intent = new Intent(GRXX.this, GRZL.class);
                intent.putExtra("grxx", "个人资料");
                startActivity(intent);
                break;
            case R.id.grxx_back:
                finish();
                break;
            default:
                break;
        }
    }
}
