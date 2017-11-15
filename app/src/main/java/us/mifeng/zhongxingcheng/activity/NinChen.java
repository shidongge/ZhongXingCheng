package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.NinChengEvent;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 昵称界面
 */
public class NinChen extends Activity {

    private TextView bc;
    private EditText nc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninchen);
        TongMing();
        initView();
    }

    private void initView() {
        bc = (TextView) findViewById(R.id.nc_bc);
        nc = (EditText) findViewById(R.id.nc_nc);
        ImageView back = (ImageView) findViewById(R.id.nincheng_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = nc.getText().toString().trim();
                if (trim.equals("")){
                    ToSi.show(NinChen.this,"请输入昵称");
                }else {
                    EventBus.getDefault().post(new NinChengEvent(trim));
                    finish();
                }
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
}
