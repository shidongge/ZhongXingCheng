package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import us.mifeng.zhongxingcheng.R;


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzx);
        SharedUtils sharedUtils = new SharedUtils();
        yicangshoujiaho = sharedUtils.getShared("yicangshoujihao", GRZX.this);
        TongMing();
        initView();
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
                Intent intent1 = new Intent(GRZX.this, SFRZ.class);
                intent1.putExtra("sfrz","身份认证");
                startActivity(intent1);
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
}
