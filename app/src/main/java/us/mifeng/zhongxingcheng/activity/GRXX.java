package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.readystatesoftware.systembartint.SystemBarTintManager;
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
    private TextView nc,js,phone,zsxm,diqu,zhiye,shouru,aihao;
    private TextView bt;
    private String grzx;
    private String token;
    private ImageView img,back,sznc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grxx);
        grzx = getIntent().getStringExtra("grzx");
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", GRXX.this);
        TongMing();
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
                    String mobile = msg1.getString("mobile");
                    String city = msg1.getString("city");
                    String job = msg1.getString("job");
                    String income = msg1.getString("income");
                    String hobby = msg1.getString("hobby");
                    String nickName = msg1.getString("nickName");
                    String portrait = msg1.getString("portrait");

                    //TODO 隐藏手机号中间四位
                    String mobile2 = mobile;
                    String maskNumber = mobile.substring(0,3)+"****"+mobile2.substring(7,mobile2.length());

                    phone.setText(maskNumber);
                    if (zhiye.equals("")){
                        zhiye.setText("未设置");
                    }else {
                        zhiye.setText(job);
                    }
                    if (zsxm.equals("")){
                        zsxm.setText("未设置");
                    }else {
                        zsxm.setText(realName);
                    }
                    if (diqu.equals("")){
                        diqu.setText("未设置");
                    }else {

                        diqu.setText(city);
                    }
                    if (shouru.equals("")){
                        shouru.setText("未设置");
                    }else {

                        shouru.setText(income);
                    }
                    if (aihao.equals("")){
                        aihao.setText("未设置");
                    }else {

                        aihao.setText(hobby);
                    }
                    if (nc.equals("")){
                        nc.setText("未设置");
                    }else {

                        nc.setText(nickName);
                    }
                    Glide.with(GRXX.this).load(WangZhi.TUPIAN+portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);
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
//        nc.setOnClickListener(this);
        sznc = (ImageView) findViewById(R.id.grxx_sznc);
        js.setOnClickListener(this);
        bt.setOnClickListener(this);
        back.setOnClickListener(this);
        sznc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grxx_sznc:
                startActivity(new Intent(GRXX.this,NinChen.class));
                break;
            case R.id.grxx_js:
                startActivity(new Intent(GRXX.this,JieShao.class));
                break;
            case R.id.grzx_bt:
                Intent intent = new Intent(GRXX.this, GRZL.class);
                intent.putExtra("grxx","个人资料");
                startActivity(intent);
                break;
            case R.id.grxx_back:
                finish();
                break;
        }
    }
    //设置状态栏
    public void TongMing(){
        //如果手机有虚拟按键 那么不能添加透明状态栏
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //   tintManager.setStatusBarTintResource(R.color.zhuangtailan);
        tintManager.setTintColor(Color.parseColor("#000000"));

    }
}
