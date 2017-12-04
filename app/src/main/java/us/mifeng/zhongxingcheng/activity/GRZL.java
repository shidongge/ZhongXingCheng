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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import cn.qqtheme.framework.picker.SinglePicker;
import cn.qqtheme.framework.widget.WheelView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.AiHaoEvent;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.NinChengEvent;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.QianMingEvent;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.utils.ZhiYeEvent;

import static android.content.ContentValues.TAG;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 个人资料界面
 */
public class GRZL extends Activity implements View.OnClickListener {
    private TextView  xz, nincheng, qianming, xingzuo, zhiye, shouru, aihao;
    private boolean first = true;
    private String grxx;
    private String token;
    private LinearLayout ll_nc, ll_zhiye, ll_aihao;
    private LinearLayout ll_qianming;
    private int xb_int;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzl);
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", GRZL.this);
        EventBus.getDefault().register(this);
        initView();
        initLianWang();
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        String s = JiaMi.jdkBase64Encoder("gender,nickName,province,city,job,income,hobby");
        map.put("token", token);
        map.put("field", s);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                 Log.e(TAG, "onResponse: "+response.body().string() );
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
                    String job = msg1.getString("job");
                    String income = msg1.getString("income");
                    String hobby = msg1.getString("hobby");
                    String nickName = msg1.getString("nickName");
                    if ("".equals(job)) {
                        zhiye.setText("未设置");
                    } else {
                        zhiye.setText(job);
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
                        nincheng.setText("未设置");
                    } else {
                        nincheng.setText(nickName);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what==100){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    String msg1 = data.getString("msg");
                    if ("0".equals(msg1)){
                        ToSi.show(GRZL.this,"更新成功");
                        finish();
                    }else if ("1".equals(msg1)){
                        ToSi.show(GRZL.this,"参数不齐");
                    }else if ("2".equals(msg1)){
                        ToSi.show(GRZL.this,"用户不存在");
                    }else if ("3".equals(msg1)){
                        ToSi.show(GRZL.this,"更新失败");
                    }else {
                        ToSi.show(GRZL.this,"更新失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private void initView() {
        xz = (TextView) findViewById(R.id.grzl_xz);

        nincheng = (TextView) findViewById(R.id.grzl_nc);
        qianming = (TextView) findViewById(R.id.grzl_qianming);
        zhiye = (TextView) findViewById(R.id.grzl_zhiye);
        shouru = (TextView) findViewById(R.id.grzl_shouru);
        aihao = (TextView) findViewById(R.id.grzl_aihao);
        TextView mbtn = (TextView) findViewById(R.id.grzl_mbtn);
        ll_nc = (LinearLayout) findViewById(R.id.grzl_ll_nc);
        ll_zhiye = (LinearLayout) findViewById(R.id.grzl_ll_zhiye);
        back = (ImageView) findViewById(R.id.grzl_back);
        ll_qianming = (LinearLayout) findViewById(R.id.grzl_ll_qm);
        ll_aihao = (LinearLayout) findViewById(R.id.grzl_ll_aihao);

        xz.setOnClickListener(this);

        ll_nc.setOnClickListener(this);
        mbtn.setOnClickListener(this);
        ll_aihao.setOnClickListener(this);
        ll_qianming.setOnClickListener(this);
        ll_zhiye.setOnClickListener(this);
        shouru.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grzl_xz:
                onConstellationPicker(xz);
                break;
            case R.id.grzl_ll_nc:
                Intent intent = new Intent(GRZL.this, NinChen.class);
                intent.putExtra("tag","2");
                startActivity(intent);
                break;
            case R.id.grzl_back:
                finish();
                break;
            case R.id.grzl_ll_qm:
                Intent intent1 = new Intent(GRZL.this, JieShao.class);
                intent1.putExtra("tag","2");
                startActivity(intent1);
                break;
            case R.id.grzl_ll_zhiye:
                startActivity(new Intent(GRZL.this, ZhiYe.class));
                break;
            case R.id.grzl_ll_aihao:
                startActivity(new Intent(GRZL.this, AIHao.class));
                break;

            case R.id.grzl_shouru:
                onShouru(shouru);
                break;
            case R.id.grzl_mbtn:
                HashMap<String, String> map = new HashMap<>();
                //爱好
                String string1 = aihao.getText().toString();
                map.put("hobby", string1);
                //收入
                String string2 = shouru.getText().toString();
                map.put("income", string2);
                //昵称
                String string4 = nincheng.getText().toString();
                map.put("nickName", string4);
                String string5 = zhiye.getText().toString();
                map.put("job",string5);
                JSONObject jsonObject = new JSONObject(map);
                String string = jsonObject.toString();
                final String s = JiaMi.jdkBase64Encoder(string);
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("token",token);
                map1.put("field",s);

                OkUtils.UploadSJ(WangZhi.GXJRXX, map1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string3 = response.body().string();
                        Message mess = hand.obtainMessage();
                        mess.obj=string3;
                        mess.what=100;
                        hand.sendMessage(mess);
                    }
                });
                break;
            default:
                break;

        }
    }

    //十二星座选择器
    public void onConstellationPicker(View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(this,
                isChinese ? new String[]{
                        "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
                        "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
                } : new String[]{
                        "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer",
                        "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn"
                });
        //picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
//        picker.setSelectedTextColor(0xFFEE0000);
//        picker.setUnSelectedTextColor(0xFF999999);
        WheelView.LineConfig config = new WheelView.LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                xz.setText(item);
//                xz.setTextColor(Color.parseColor("#ff0000"));
            }
        });
        picker.show();
    }


    //收入选择器
    public void onShouru(View view) {
        boolean isChinese = Locale.getDefault().getDisplayLanguage().contains("中文");
        SinglePicker<String> picker = new SinglePicker<>(this,
                isChinese ? new String[]{
                        "5000以下", "5000-10000", "10000-15000", "20000-50000", "50000以上"
                } : new String[]{
                        "5000以下", "5000-10000", "10000-15000", "20000-50000", "50000以上"
                });
        //picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(isChinese ? "请选择" : "Please pick");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(13);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
//        picker.setSelectedTextColor(0xFFEE0000);
//        picker.setUnSelectedTextColor(0xFF999999);
        WheelView.LineConfig config = new WheelView.LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                shouru.setText(item);
                // shouru.setTextColor(Color.parseColor("#ff0000"));
            }
        });
        picker.show();
    }


    //接收签名发过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(QianMingEvent event) {
        String msg = event.getMsg();
        qianming.setText(msg);

    }

    //接收昵称发过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(NinChengEvent event) {
        String msg = event.getMsg();
        nincheng.setText(msg);
    }

    //接收昵称发过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(ZhiYeEvent event) {
        String msg = event.getMsg();
        zhiye.setText(msg);
    }

    //接收爱好发过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(AiHaoEvent event) {
        String msg = event.getMsg();
        aihao.setText(msg);
    }



}
