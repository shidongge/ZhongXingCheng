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
import us.mifeng.zhongxingcheng.utils.AiHaoEvent;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/8.
 */

public class AIHao extends Activity implements View.OnClickListener {

    private EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aihao);
        TongMing();
        initView();
    }

    private void initView() {
        TextView baocun = (TextView) findViewById(R.id.aihao_bc);
        edit = (EditText) findViewById(R.id.aihao_aihao);
        ImageView back = (ImageView) findViewById(R.id.aihao_back);
        back.setOnClickListener(this);
        baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aihao_back:
                finish();
                break;
            case R.id.aihao_bc:
                String trim = edit.getText().toString().trim();
                if (trim.equals("")) {
                    ToSi.show(AIHao.this,"请输入爱好");
                }else {
                    EventBus.getDefault().post(new AiHaoEvent(trim));
                    finish();
                }
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
