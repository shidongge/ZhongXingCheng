package us.mifeng.zhongxingcheng.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.ShouCang_DianPu;
import us.mifeng.zhongxingcheng.fragment.ShouCang_ShangPin;

/**
 * Created by shido on 2017/11/24.
 */

public class ShouCang extends FragmentActivity implements View.OnClickListener {

    private FragmentManager fm;
    private ShouCang_DianPu shouCang_dianPu;
    private ShouCang_ShangPin shouCang_shangPin;
    private LinearLayout shangpin, pinpai;
    private TextView shangpin_text, shangpin_number, shangpin_kuohao, pinpai_text, pinpai_number, pinpai_kuohao;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc_shoucang);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        shouCang_shangPin = new ShouCang_ShangPin();
        ft.add(R.id.shoucang_da, shouCang_shangPin);
        ft.commit();
        TongMing();
        initView();
    }

    private void initView() {
        shangpin = (LinearLayout) findViewById(R.id.shoucang_shangpin);
        shangpin_text = (TextView) findViewById(R.id.shoucang_shangpin_text);
        shangpin_number = (TextView) findViewById(R.id.shoucang_shangpin_number);
        shangpin_kuohao = (TextView) findViewById(R.id.shoucang_shangpin_kuohao);
        pinpai = (LinearLayout) findViewById(R.id.shoucang_pinpai);
        pinpai_text = (TextView) findViewById(R.id.shoucang_pinpai_text);
        pinpai_number = (TextView) findViewById(R.id.shoucang_pinpai_number);
        pinpai_kuohao = (TextView) findViewById(R.id.shoucang_pinpai_kuohao);
        back = (ImageView) findViewById(R.id.yhq_back);
        pinpai.setOnClickListener(this);
        shangpin.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        setText();
        hintFragment();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.shoucang_shangpin:
                shangpin.setBackgroundColor(Color.parseColor("#777777"));
                shangpin_text.setTextColor(Color.parseColor("#ffffff"));
                shangpin_number.setTextColor(Color.parseColor("#ffffff"));
                shangpin_kuohao.setTextColor(Color.parseColor("#ffffff"));
                if (shouCang_shangPin == null) {
                    ft.add(R.id.shoucang_da, shouCang_shangPin);
                } else {
                    ft.show(shouCang_shangPin);
                }
                break;
            case R.id.shoucang_pinpai:
                pinpai.setBackgroundColor(Color.parseColor("#777777"));
                pinpai_text.setTextColor(Color.parseColor("#ffffff"));
                pinpai_number.setTextColor(Color.parseColor("#ffffff"));
                pinpai_kuohao.setTextColor(Color.parseColor("#ffffff"));
                if (shouCang_dianPu == null) {
                    shouCang_dianPu = new ShouCang_DianPu();
                    ft.add(R.id.shoucang_da, shouCang_dianPu);
                } else {
                    ft.show(shouCang_dianPu);
                }
                break;
            case R.id.yhq_back:
                finish();
                break;
        }
        ft.commit();
    }

    private void setText() {
        shangpin_text.setTextColor(Color.parseColor("#000000"));
        shangpin_number.setTextColor(Color.parseColor("#000000"));
        shangpin_kuohao.setTextColor(Color.parseColor("#000000"));
        pinpai_text.setTextColor(Color.parseColor("#000000"));
        pinpai_number.setTextColor(Color.parseColor("#000000"));
        pinpai_kuohao.setTextColor(Color.parseColor("#000000"));
        shangpin.setBackgroundColor(Color.parseColor("#ffffff"));
        pinpai.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void hintFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (shouCang_dianPu != null) {
            ft.hide(shouCang_dianPu);
        }
        if (shouCang_shangPin != null) {
            ft.hide(shouCang_shangPin);
        }
        ft.commit();
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