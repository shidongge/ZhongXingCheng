package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/8.
 */

public class ZJZP_WanShan extends Activity {
    private SharedUtils utils;
    private String token;
    private static final String TAG = "ZJZP_WanShan";
    private ImageView zhengmian, fanmian, chizhao_zhenmian, chizhao_fanmian;
    private ImageView fm;
    private TextView title, xingming, xingbie, nianyueri, diqu, sfz;
    private String xb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjzp_wanshan);
        utils = new SharedUtils();
        token = utils.getShared("token", ZJZP_WanShan.this);
        initView();
        initLianWang();
    }

    private void initLianWang() {
        String s = JiaMi.jdkBase64Encoder("realName,identityCard,birthDate,gender,province,city,identityFace,identityBack");
        HashMap<String, String> map = new HashMap<>();
        map.put("field", s);
        map.put("token", token);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = hand.obtainMessage();
                message.obj = string;
                message.what = 200;
                hand.sendMessage(message);
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
                    String identityCard = msg1.getString("identityCard");
                    String gender = msg1.getString("gender");
                    String birthDate = msg1.getString("birthDate");
                    String city = msg1.getString("city");
                    diqu.setText(city);
                    nianyueri.setText(birthDate);
                    if (gender.equals("1")) {
                        xb = "男";
                    } else {
                        xb = "女";
                    }
                    xingbie.setText(xb);
                    xingming.setText(realName);
                    sfz.setText(identityCard);
                    String identityFace = msg1.getString("identityFace");
                    String identityBack = msg1.getString("identityBack");
                    if ("".equals(identityFace)) {
                        zhengmian.setImageResource(R.mipmap.sfzm);
                    } else {
                        Glide.with(ZJZP_WanShan.this).load(WangZhi.TUPIAN + identityFace).into(zhengmian);
                    }
                    if ("".equals(identityBack)) {
                        fanmian.setImageResource(R.mipmap.sfzfm);
                    } else {
                        Glide.with(ZJZP_WanShan.this).load(WangZhi.TUPIAN + identityBack).into(fanmian);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.jbxx_wanshan_back);
        sfz = (TextView) findViewById(R.id.jbxx_wanshan_sfz);
        xingbie = (TextView) findViewById(R.id.jbxx_wanshan_xingbie);
        nianyueri = (TextView) findViewById(R.id.jbxx_wanshan_nianyueri);
        diqu = (TextView) findViewById(R.id.jbxx_wanshan_ssdq);
        xingming = (TextView) findViewById(R.id.jbxx_wanshan_name);
        zhengmian = (ImageView) findViewById(R.id.zjzp_wanshan_zhengmian);
        fanmian = (ImageView) findViewById(R.id.zjzp_wanshan_fanmian);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
