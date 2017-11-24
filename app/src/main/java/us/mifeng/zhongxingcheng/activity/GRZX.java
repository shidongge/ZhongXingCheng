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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;


/**
 * Created by shido on 2017/10/30.
 */

/**
 * 个人中心界面
 */
public class GRZX extends Activity implements View.OnClickListener {

    private LinearLayout grzy,sfrz,shdz,fapiao;
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
        yicangshoujiaho = sharedUtils.getShared("yicangshoujihao", GRZX.this);
        token = sharedUtils.getShared("token",GRZX.this);
        TongMing();
        initLianWang();
        initView();
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        OkUtils.UploadSJ(WangZhi.WODE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             //   Log.e(TAG, "onResponse: "+response.body().string() );
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj=string;
                mess.what=100;
                hand.sendMessage(mess);
            }
        });
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
        switch (v.getId()){
            case R.id.grzx_grzy:
                Intent intent = new Intent(GRZX.this, GRXX.class);
                intent.putExtra("grzx","个人主页");
                startActivity(intent);
                break;
            case R.id.grzx_sfrz:
                Log.e(TAG, "onClick: "+realStatus );
                if (realStatus.equals("1")){
                    startActivity(new Intent(GRZX.this,ZJZP_WanShan.class));
                }else if (realStatus.equals("0")){
                    Intent intent1 = new Intent(GRZX.this, SFRZ.class);
                    intent1.putExtra("sfrz","身份认证");
                    startActivity(intent1);
                }

                break;
            case R.id.grzx_shdz:
                startActivity(new Intent(GRZX.this,H5SHDZ.class));
                break;

            case R.id.grzx_fapiao:
                startActivity(new Intent(GRZX.this,FPGL.class));
                break;
            case R.id.grzx_back:
                finish();
                break;
        }
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("msg");
                    realStatus = msg1.getString("realStatus");
                    Log.e(TAG, "handleMessage: "+realStatus );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
