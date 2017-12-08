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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;


/**
 * Created by shido on 2017/10/30.
 */

/**
 * 个人中心界面
 */
public class GRZX extends Activity implements View.OnClickListener {

    private LinearLayout grzy, sfrz, shdz, fapiao;
    private ImageView back;
    private TextView zxh;
    private String yicangshoujiaho;
    private String token;
    private static final String TAG = "GRZX";
    private String realStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzx);
        SharedUtils sharedUtils = new SharedUtils();
        yicangshoujiaho = sharedUtils.getShared("mobile", GRZX.this);
        token = sharedUtils.getShared("token", GRZX.this);
        initLianWang();
        initView();
    }

    private void initLianWang() {
        final String realStatus = JiaMi.jdkBase64Encoder("realStatus");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("field",realStatus);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 100;
                hand.sendMessage(mess);
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.grzx_back);
        grzy = (LinearLayout) findViewById(R.id.grzx_grzy);
        sfrz = (LinearLayout) findViewById(R.id.grzx_sfrz);
        shdz = (LinearLayout) findViewById(R.id.grzx_shdz);
        zxh = (TextView) findViewById(R.id.grzx_zxh_text);
        fapiao = (LinearLayout) findViewById(R.id.grzx_fapiao);
        fapiao.setOnClickListener(this);
        zxh.setText(yicangshoujiaho);
        shdz.setOnClickListener(this);
        grzy.setOnClickListener(this);
        sfrz.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grzx_grzy:
                Intent intent = new Intent(GRZX.this, GRXX.class);
                intent.putExtra("grzx", "个人主页");
                startActivity(intent);
                break;
            case R.id.grzx_sfrz:
                Log.e(TAG, "onClick: " + realStatus);
                if ("1".equals(realStatus)) {
                    startActivity(new Intent(GRZX.this, ZJZP_WanShan.class));
                } else if ("0".equals(realStatus)) {
                    Intent intent1 = new Intent(GRZX.this, SFRZ.class);
                    intent1.putExtra("sfrz", "身份认证");
                    startActivity(intent1);
                }

                break;
            case R.id.grzx_shdz:
                startActivity(new Intent(GRZX.this, SHDZGL.class));
                break;

            case R.id.grzx_fapiao:
                startActivity(new Intent(GRZX.this, FPGL.class));
                break;
            case R.id.grzx_back:
                finish();
                break;
            default:
                break;
        }
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("userInfo");
                    realStatus = msg1.getString("realStatus");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
