package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.AddressPickTask;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 修改收货地址界面
 */

public class XGSHDZ extends Activity implements View.OnClickListener {
    private static final String TAG = "XZSHDZ";
    private LinearLayout diqu;
    private TextView diqu_text, baocun;
    private ImageView back,moren;
    private EditText name, mobile, xxdz, youbian;
    private String token;
    private String zxcid;
    private String substring;
    private String sheng, shi, qu;
    private boolean isTag = false;
    private String shifoumoren = "0";
    private String dizhiid,city1,districe1,mobile1,isDefault1,province1,zip_code1,userName1,address1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xgshdz);
        SharedUtils sharedUtils = new SharedUtils();
        dizhiid = getIntent().getStringExtra("dizhiid");
        city1 = getIntent().getStringExtra("city1");
        districe1 = getIntent().getStringExtra("districe1");
        mobile1 = getIntent().getStringExtra("mobile1");
        isDefault1 = getIntent().getStringExtra("isDefault1");
        province1 = getIntent().getStringExtra("province1");
        zip_code1 = getIntent().getStringExtra("zip_code1");
        userName1 = getIntent().getStringExtra("username1");
        address1 = getIntent().getStringExtra("address1");
        String id = sharedUtils.getShared("id", XGSHDZ.this);
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", XGSHDZ.this);
        zxcid = sharedUtils.getShared("zxcid", XGSHDZ.this);
        initView();
    }

    private void initView() {
        diqu = (LinearLayout) findViewById(R.id.xgshdz_diqu);
        diqu_text = (TextView) findViewById(R.id.xgshdz_text);
        name = (EditText) findViewById(R.id.xgshdz_name);
        xxdz = (EditText) findViewById(R.id.xgshdz_xxdz);
        mobile = (EditText) findViewById(R.id.xgshdz_number);
        youbian = (EditText) findViewById(R.id.xgshdz_youbian);
        back = (ImageView) findViewById(R.id.xgshdz_back);
        baocun = (TextView) findViewById(R.id.xgshdz_bc);
        moren = (ImageView) findViewById(R.id.xgshdz_moren);


        diqu_text.setText(province1+city1+districe1);
        name.setText(userName1);
        youbian.setText(zip_code1);
        mobile.setText(mobile1);
        xxdz.setText(address1);
        if ("0".equals(isDefault1)){
            moren.setImageResource(R.mipmap.shdzwg);
        }else {
            moren.setImageResource(R.mipmap.shdzyg);
        }

        diqu.setOnClickListener(this);
        back.setOnClickListener(this);
        baocun.setOnClickListener(this);
        moren.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xgshdz_diqu:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(XGSHDZ.this, "数据初始化失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            final String address = province.getAreaName() + city.getAreaName();
                            diqu_text.setText(address);
                        } else {
                            final String address = province.getAreaName() + city.getAreaName() + county.getAreaName();
                            sheng = province.getAreaName();
                            shi = city.getAreaName();
                            qu = county.getAreaName();
                            diqu_text.setText(address);
                            province1=sheng;
                            city1=shi;
                            districe1=qu;
                        }
                    }
                });
                task.execute("北京市", "北京市", "东城区");
                break;
            case R.id.xgshdz_back:
                finish();
                break;
            case R.id.xgshdz_moren:

                if (!isTag){
                    moren.setImageResource(R.mipmap.shdzyg);
                    isTag=true;
                    shifoumoren = "1";
                }else {
                    shifoumoren = "0";
                    isTag=false;
                    moren.setImageResource(R.mipmap.shdzwg);
                }
                break;
            case R.id.xgshdz_bc:
                String trim = name.getText().toString().trim();
                String trim1 = mobile.getText().toString().trim();

                String trim2 = xxdz.getText().toString().trim();
                String trim3 = youbian.getText().toString().trim();
                String trim4 = diqu_text.getText().toString().trim();
                boolean mobileNO = JiaMi.isMobileNO(trim1);
                if (trim.equals("") || trim2.equals("") || trim3.equals("") || trim4.equals("")) {
                    ToSi.show(XGSHDZ.this, "信息未填写完整");
                }  else if (!mobileNO){
                        ToSi.show(XGSHDZ.this,"手机号有误");
                } else{
                    HashMap<String, String> map = new HashMap<>();
                    map.put("user_id",zxcid);
                    map.put("user_token",token);
                    map.put("user_mobile",substring);
                    map.put("userName",trim);
                    map.put("mobile",trim1);
                    map.put("address",trim2);
                    map.put("province",province1);
                    map.put("city",city1);
                    map.put("district",districe1);
                    map.put("isDefault",shifoumoren);
                    map.put("zip_code",trim3);
                    map.put("id",dizhiid);
                    JSONObject jsonObject = new JSONObject(map);
                    String string = jsonObject.toString();
                    String s = JiaMi.jdkBase64Encoder(string);
                    HashMap<String, String> map1 = new HashMap<>();
                    map1.put("secret",s);
                    OkUtils.UploadSJ(WangZhi.XGSHDZ, map1, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Message mess = hand.obtainMessage();
                            mess.obj=string;
                            mess.what=200;
                            hand.sendMessage(mess);

                        }
                    });
                }
                break;
            default:
                break;
        }
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String info = jsonObject.getString("info");
                    String status = jsonObject.getString("status");
                    //成功返回0
                    if ("0".equals(status)){
                        ToSi.show(XGSHDZ.this,info);
                        finish();
                    }else {
                        ToSi.show(XGSHDZ.this,info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
