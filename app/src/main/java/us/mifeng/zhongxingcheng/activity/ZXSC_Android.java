package us.mifeng.zhongxingcheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.ZXSC_FenLeiFragment;
import us.mifeng.zhongxingcheng.fragment.ZXSC_GouWuCheFragment;
import us.mifeng.zhongxingcheng.fragment.ZXSC_ShouYeFragment;
import us.mifeng.zhongxingcheng.fragment.ZXSC_WoDeFragment;


/**
 * Created by shido on 2017/11/15.
 */

/**
 * Z中星商城的activity
 */
public class ZXSC_Android extends FragmentActivity implements View.OnClickListener {

    private LinearLayout shouye,fenlei,gouwuche,wode;
    private FragmentManager fm;
    private ZXSC_ShouYeFragment zxsc_shouYeFragment;
    private ZXSC_WoDeFragment zxsc_woDeFragment;
    private ZXSC_FenLeiFragment zxsc_fenLeiFragment;
    private ZXSC_GouWuCheFragment zxsc_gouWuCheFragment;
    private ImageView shou_img,fenlei_img,gouwuche_img,wode_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc_android);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        initView();
        Intent intent = getIntent();
        int page = intent.getIntExtra("page",0);
        zxsc_shouYeFragment = new ZXSC_ShouYeFragment();
        ft.add(R.id.zxsc_home_ll, zxsc_shouYeFragment);
        if (page==1){
            initText();
            hintFragment();
            fenlei_img.setImageResource(R.mipmap.zxsc_fl_l);
            if (zxsc_fenLeiFragment==null){
                zxsc_fenLeiFragment = new ZXSC_FenLeiFragment();
                ft.add(R.id.zxsc_home_ll,zxsc_fenLeiFragment);
            }else {
                ft.show(zxsc_fenLeiFragment);
            }
        }
        ft.commit();

    }

    private void initView() {
        shouye = (LinearLayout) findViewById(R.id.zxsc_home_shouye);
        fenlei = (LinearLayout) findViewById(R.id.zxsc_home_fenlei);
        gouwuche = (LinearLayout) findViewById(R.id.zxsc_home_gouwuche);
        wode = (LinearLayout) findViewById(R.id.zxsc_home_wode);

        shou_img = (ImageView) findViewById(R.id.zxsc_home_shouye_img);
        fenlei_img = (ImageView) findViewById(R.id.zxsc_home_fenlei_img);
        gouwuche_img = (ImageView) findViewById(R.id.zxsc_home_gouwuche_img);
        wode_img = (ImageView) findViewById(R.id.zxsc_home_wode_img);

        shouye.setOnClickListener(this);
        fenlei.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
        wode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        initText();
        hintFragment();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.zxsc_home_shouye:
                shou_img.setImageResource(R.mipmap.zxsc_sy_l);
                if (zxsc_shouYeFragment==null){
                    ft.add(R.id.zxsc_home_ll, zxsc_shouYeFragment);
                }else {
                    ft.show(zxsc_shouYeFragment);
                }
                break;
            case R.id.zxsc_home_fenlei:
                fenlei_img.setImageResource(R.mipmap.zxsc_fl_l);
                if (zxsc_fenLeiFragment==null){
                    zxsc_fenLeiFragment = new ZXSC_FenLeiFragment();
                    ft.add(R.id.zxsc_home_ll,zxsc_fenLeiFragment);
                }else {
                    ft.show(zxsc_fenLeiFragment);
                }
                break;
            case R.id.zxsc_home_gouwuche:
                gouwuche_img.setImageResource(R.mipmap.zxsc_gwc_l);
                if (zxsc_gouWuCheFragment==null){
                    zxsc_gouWuCheFragment = new ZXSC_GouWuCheFragment();
                    ft.add(R.id.zxsc_home_ll,zxsc_gouWuCheFragment);
                }else {
                    ft.show(zxsc_gouWuCheFragment);
                }
                break;
            case R.id.zxsc_home_wode:
                wode_img.setImageResource(R.mipmap.zxsc_wd_l);
                if (zxsc_woDeFragment==null){
                    zxsc_woDeFragment = new ZXSC_WoDeFragment();
                    ft.add(R.id.zxsc_home_ll,zxsc_woDeFragment);
                }else {
                    ft.show(zxsc_woDeFragment);
                }
                break;
            default:
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
        if (zxsc_gouWuCheFragment!=null){
            ft.hide(zxsc_gouWuCheFragment);
        }
        ft.commit();
    }
    private void initText(){
        shou_img.setImageResource(R.mipmap.zxsc_sy_h);
        fenlei_img.setImageResource(R.mipmap.zxsc_fl_h);
        gouwuche_img.setImageResource(R.mipmap.zxsc_gwc_h);
        wode_img.setImageResource(R.mipmap.zxsc_wd_h);

    }
}
