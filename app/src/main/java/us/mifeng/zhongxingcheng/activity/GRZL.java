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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.SinglePicker;
import cn.qqtheme.framework.widget.WheelView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.AddressPickTask;
import us.mifeng.zhongxingcheng.utils.AiHaoEvent;
import us.mifeng.zhongxingcheng.utils.NinChengEvent;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.QianMingEvent;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.utils.ZhiYeEvent;
import us.mifeng.zhongxingcheng.view.CustomDog;

import static android.content.ContentValues.TAG;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 个人资料界面
 */
public class GRZL extends Activity implements View.OnClickListener {
    private TextView xb,xz,nincheng,diqu,qianming,xingzuo,zhiye,shouru,aihao;
    private boolean first = true;
    private String grxx;
    private String token;
    private LinearLayout ll_nc,ll_zhiye,ll_aihao;
    private LinearLayout ll_qianming;
    private int xb_int ;
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
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        OkUtils.UploadSJ(WangZhi.WODE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.e(TAG, "onResponse: "+response.body().string() );
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj=string;
                mess.what=200;
                hand.sendMessage(mess);

            }
        });
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("msg");
                    String realName = msg1.getString("realName");
                    String city = msg1.getString("city");
                    String gender = msg1.getString("gender");

                    String job = msg1.getString("job");
                    String income = msg1.getString("income");
                    String hobby = msg1.getString("hobby");
                    String nickName = msg1.getString("nickName");
                    if ("".equals(zhiye)){
                        zhiye.setText("未设置");
                    }else {
                        zhiye.setText(job);
                    }
                    if ("".equals(diqu)){
                        diqu.setText("未设置");
                    }else {
                        diqu.setText(city);
                    }
                    if ("".equals(shouru)){
                        shouru.setText("未设置");
                    }else {

                        shouru.setText(income);
                    }
                    if ("".equals(aihao)){
                        aihao.setText("未设置");
                    }else {

                        aihao.setText(hobby);
                    }
                    if ("".equals(nincheng)){
                        nincheng.setText("未设置");
                    }else {
                        nincheng.setText(nickName);
                    }
                    if ("1".equals(gender)){
                        xb.setText("男");
                    }else {
                        xb.setText("女");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };





    private void initView() {
        xb = (TextView) findViewById(R.id.grzl_xb);
        xz = (TextView) findViewById(R.id.grzl_xz);

        nincheng = (TextView) findViewById(R.id.grzl_nc);
        diqu = (TextView) findViewById(R.id.grzl_diqu);
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
        xb.setOnClickListener(this);
        ll_nc.setOnClickListener(this);
        mbtn.setOnClickListener(this);
        ll_aihao.setOnClickListener(this);
        ll_qianming.setOnClickListener(this);
        ll_zhiye.setOnClickListener(this);
        diqu.setOnClickListener(this);
        shouru.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grzl_xb:
                CustomDog customDog = new CustomDog(GRZL.this);
                customDog.setOnDialogClickListeners(new CustomDog.OnDialogClickListeners() {
                    @Override
                    public void onInputLegitimacy(String inputString) {
                       xb.setText(inputString);
                    }

                    @Override
                    public void onInputIllegal() {

                    }
                });
                customDog.show();
                break;
            case R.id.grzl_xz:
                onConstellationPicker(xz);
                break;

            case R.id.grzl_ll_nc:
                Intent intent = new Intent(GRZL.this, NinChen.class);
                startActivity(intent);
                break;
            case R.id.grzl_back:
                finish();
                break;
            case R.id.grzl_ll_qm:
                startActivity(new Intent(GRZL.this,JieShao.class));
                break;
            case R.id.grzl_ll_zhiye:
                startActivity(new Intent(GRZL.this,ZhiYe.class));
                break;
            case R.id.grzl_ll_aihao:
                startActivity(new Intent(GRZL.this,AIHao.class));
                break;
            case R.id.grzl_diqu:
                //TODO 添加地区地址
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(GRZL.this, "数据初始化失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            final String address = province.getAreaName() + city.getAreaName();
                            diqu.setText(address);
                        } else {
                            final String address = province.getAreaName() + city.getAreaName() + county.getAreaName();
                            diqu.setText(address);
                        }
                    }
                });
                task.execute("北京市", "北京市", "东城区");
                break;
            case R.id.grzl_shouru:
                onShouru(shouru);
                break;
            case R.id.grzl_mbtn:
                HashMap<String, String> map = new HashMap<>();
                String string = xb.getText().toString();
                if ("男".equals(string)){
                    xb_int=1;
                }else if ("女".equals(string)){
                    xb_int=2;
                }
                //性别
                map.put("gender",xb_int+"");
                //爱好
                String string1 = aihao.getText().toString();
                map.put("hobby",string1);
                //收入
                String string2 = shouru.getText().toString();
                map.put("income",string2);
                //地区
                String string3 = diqu.getText().toString();
                map.put("city",string3);
                //昵称
                String string4 = nincheng.getText().toString();
                map.put("nickName",string4);
                Log.e(TAG, "onClick: "+map );
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
    public void MessageEvent(NinChengEvent event){
        String msg = event.getMsg();
        nincheng.setText(msg);
    }
    //接收昵称发过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(ZhiYeEvent event){
        String msg = event.getMsg();
        zhiye.setText(msg);
    }
    //接收爱好发过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(AiHaoEvent event){
        String msg = event.getMsg();
        aihao.setText(msg);
    }
}
