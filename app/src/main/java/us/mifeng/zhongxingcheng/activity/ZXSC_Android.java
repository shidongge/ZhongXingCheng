package us.mifeng.zhongxingcheng.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.ZXSC_FenLeiFragment;
import us.mifeng.zhongxingcheng.fragment.ZXSC_ShouYeFragment;
import us.mifeng.zhongxingcheng.fragment.ZXSC_WoDeFragment;

/**
 * Created by shido on 2017/11/15.
 */

public class ZXSC_Android extends FragmentActivity implements View.OnClickListener {

    private LinearLayout shouye,fenlei,gouwuche,wode;
    private FragmentManager fm;
    private ZXSC_ShouYeFragment zxsc_shouYeFragment;
    private ZXSC_WoDeFragment zxsc_woDeFragment;
    private ZXSC_FenLeiFragment zxsc_fenLeiFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc_android);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        zxsc_shouYeFragment = new ZXSC_ShouYeFragment();
        ft.add(R.id.zxsc_home_ll, zxsc_shouYeFragment);
        ft.commit();
        initView();
    }

    private void initView() {
        shouye = (LinearLayout) findViewById(R.id.zxsc_home_shouye);
        fenlei = (LinearLayout) findViewById(R.id.zxsc_home_fenlei);
        gouwuche = (LinearLayout) findViewById(R.id.zxsc_home_gouwuche);
        wode = (LinearLayout) findViewById(R.id.zxsc_home_wode);
        shouye.setOnClickListener(this);
        fenlei.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
        wode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hintFragment();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.zxsc_home_shouye:
                if (zxsc_shouYeFragment==null){
                    ft.add(R.id.zxsc_home_ll, zxsc_shouYeFragment);
                }else {
                    ft.show(zxsc_shouYeFragment);
                }
                break;
            case R.id.zxsc_home_fenlei:
                if (zxsc_fenLeiFragment==null){
                    zxsc_fenLeiFragment = new ZXSC_FenLeiFragment();
                    ft.add(R.id.zxsc_home_ll,zxsc_fenLeiFragment);
                }else {
                    ft.show(zxsc_fenLeiFragment);
                }

                break;
            case R.id.zxsc_home_gouwuche:

                break;
            case R.id.zxsc_home_wode:
                if (zxsc_woDeFragment==null){
                    zxsc_woDeFragment = new ZXSC_WoDeFragment();
                    ft.add(R.id.zxsc_home_ll,zxsc_woDeFragment);
                }else {
                    ft.show(zxsc_woDeFragment);
                }
                break;
        }
        ft.commit();
    }
    public void hintFragment(){
        FragmentTransaction ft = fm.beginTransaction();

        if (zxsc_shouYeFragment!=null){
            ft.hide(zxsc_shouYeFragment);
        }
        if (zxsc_fenLeiFragment !=null){
            ft.hide(zxsc_fenLeiFragment);
        }
        if (zxsc_woDeFragment!=null){
            ft.hide(zxsc_woDeFragment);
        }
//        if (haoYouFragment!=null){
//            ft.hide(haoYouFragment);
//        }
        ft.commit();
    }
}
