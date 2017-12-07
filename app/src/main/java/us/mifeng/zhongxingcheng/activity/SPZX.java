package us.mifeng.zhongxingcheng.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.SPZX_DD;
import us.mifeng.zhongxingcheng.fragment.SPZX_SPZX;
import us.mifeng.zhongxingcheng.fragment.SPZX_WoDo;

/**
 * Created by shido on 2017/12/6.
 */

/**
 * 首页点击商品中心跳转过来的界面
 */
public class SPZX extends FragmentActivity implements View.OnClickListener {

    private FragmentManager fm;
    private LinearLayout shangpin,dingdan,geren;
    private SPZX_SPZX spzx_spzx;
    private SPZX_DD spzx_dd;
    private SPZX_WoDo spzx_woDo;
    private TextView shangpin_text,dd_text,wode_text;
    private ImageView shangpin_img,dd_img,wode_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spzx);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        spzx_spzx = new SPZX_SPZX();
        ft.add(R.id.spzx_shang, spzx_spzx);
        ft.commit();
        initView();
    }

    private void initView() {
        shangpin = (LinearLayout) findViewById(R.id.spzx_shouye);
        dingdan = (LinearLayout) findViewById(R.id.spzx_dingdan);
        geren = (LinearLayout) findViewById(R.id.spzx_geren);
        shangpin_text = (TextView) findViewById(R.id.spzx_shouye_text);
        dd_text = (TextView) findViewById(R.id.spzx_dingdan_text);
        wode_text = (TextView) findViewById(R.id.spzx_geren_text);
        shangpin_img = (ImageView) findViewById(R.id.spzx_shouye_img);
        dd_img = (ImageView) findViewById(R.id.spzx_dingdan_img);
        wode_img = (ImageView) findViewById(R.id.spzx_geren_img);
        shangpin.setOnClickListener(this);
        dingdan.setOnClickListener(this);
        geren.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hintFragment();
        setTextColor();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.spzx_shouye:
                shangpin_img.setImageResource(R.mipmap.sph);
                shangpin_text.setTextColor(Color.parseColor("#ff9147"));
                if (spzx_spzx==null){
                    ft.add(R.id.spzx_shang ,spzx_spzx);
                }else {
                    ft.show(spzx_spzx);
                }
                break;
            case R.id.spzx_dingdan:
                dd_img.setImageResource(R.mipmap.ddh);
                dd_text.setTextColor(Color.parseColor("#ff9147"));
                if (spzx_dd==null){
                    spzx_dd = new SPZX_DD();
                    ft.add(R.id.spzx_shang,spzx_dd);
                }else {
                    ft.show(spzx_dd);
                }
                break;
            case R.id.spzx_geren:
                wode_img.setImageResource(R.mipmap.hyh);
                wode_text.setTextColor(Color.parseColor("#ff9147"));
                if (spzx_woDo==null){
                    spzx_woDo = new SPZX_WoDo();
                    ft.add(R.id.spzx_shang,spzx_woDo);
                }else {
                    ft.show(spzx_woDo);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }
    public void hintFragment(){
        FragmentTransaction ft = fm.beginTransaction();
        if (spzx_spzx!=null){
            ft.hide(spzx_spzx);
        }
        if (spzx_dd!=null){
            ft.hide(spzx_dd);
        }
        if (spzx_woDo!=null){
            ft.hide(spzx_woDo);
        }
        ft.commit();
    }
    public void setTextColor(){
        shangpin_img.setImageResource(R.mipmap.spl);
        dd_img.setImageResource(R.mipmap.ddl);
        wode_img.setImageResource(R.mipmap.hyl);
        shangpin_text.setTextColor(Color.parseColor("#666666"));
        dd_text.setTextColor(Color.parseColor("#666666"));
        wode_text.setTextColor(Color.parseColor("#666666"));
    }
}
