package us.mifeng.zhongxingcheng.activity;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.ShouYeFragment;
import us.mifeng.zhongxingcheng.fragment.WoDeFragment;
import us.mifeng.zhongxingcheng.fragment.XinWenFragment;
import us.mifeng.zhongxingcheng.liaotian.ConversationFragment;
import us.mifeng.zhongxingcheng.upload.IPermissionSix;
import us.mifeng.zhongxingcheng.upload.PermissionUtils;
import us.mifeng.zhongxingcheng.upload.UpLoadAppUtils;
import us.mifeng.zhongxingcheng.utils.FirstEvent;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.TuiChuEvent;

import static us.mifeng.zhongxingcheng.R.mipmap.sy_l;

public class MainActivity extends FragmentActivity implements View.OnClickListener, IPermissionSix {
    private long exitTime = 0;
    private LinearLayout home_ll;
    private LinearLayout shouye,xinwen,wode,haoyou;
    private TextView shouye_text,xinwen_text,haoyou_text,wode_text;
    private ShouYeFragment shouYeFragment;
    private FragmentManager fm;
    private XinWenFragment xinWenFragment;
    private WoDeFragment woDeFragment;
    private ImageView shouye_img,xinwen_img,haoyou_img,wode_img;
    private ConversationFragment haoYouFragment;
    private int versionCode;
    private String str = "http://taogt.cn/app/checkImVer";
    private UpLoadAppUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        shouYeFragment = new ShouYeFragment();
        ft.add(R.id.home_ll, shouYeFragment);
        ft.commit();

        initGetVer();
        initupload();
    }


    private void initView() {
        home_ll = (LinearLayout) findViewById(R.id.home_ll);
        shouye = (LinearLayout) findViewById(R.id.home_shouye);
        wode = (LinearLayout) findViewById(R.id.home_wode);
        haoyou = (LinearLayout) findViewById(R.id.home_haoyou);
        xinwen = (LinearLayout) findViewById(R.id.home_xinwen);

        shouye_img = (ImageView) findViewById(R.id.home_shouye_img);
        xinwen_img = (ImageView) findViewById(R.id.home_xinwen_img);
        haoyou_img = (ImageView) findViewById(R.id.home_haoyou_img);
        wode_img = (ImageView) findViewById(R.id.home_wode_img);

        shouye_text = (TextView) findViewById(R.id.home_shouye_text);
        xinwen_text = (TextView) findViewById(R.id.home_xinwen_text);
        haoyou_text = (TextView) findViewById(R.id.home_haoyou_text);
        wode_text = (TextView) findViewById(R.id.home_wode_text);

        wode.setOnClickListener(this);
        xinwen.setOnClickListener(this);
        haoyou.setOnClickListener(this);
        shouye.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        hintFragment();
        initText();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_shouye:
                shouye_img.setImageResource(sy_l);
                if (shouYeFragment==null){
                    ft.add(R.id.home_ll,shouYeFragment);
                }else {

                    ft.show(shouYeFragment);
                }
                break;
            case R.id.home_xinwen:
                xinwen_img.setImageResource(R.mipmap.fx_l);
                if (xinWenFragment ==null){
                    xinWenFragment = new XinWenFragment();
                    ft.add(R.id.home_ll, xinWenFragment);
                }else {
                    ft.show(xinWenFragment);
                }
                break;

            case R.id.home_wode:
                wode_img.setImageResource(R.mipmap.wd_l);
                if (woDeFragment==null){
                    woDeFragment = new WoDeFragment();
                    ft.add(R.id.home_ll,woDeFragment);
                }else {
                    ft.show(woDeFragment);
                }
                break;
            case R.id.home_haoyou:
                haoyou_img.setImageResource(R.mipmap.hy_l);
                if (haoYouFragment==null){
                    haoYouFragment = new ConversationFragment();
                    ft.add(R.id.home_ll,haoYouFragment);
                }else {
                    ft.show(haoYouFragment);
                }
                break;
        }
        ft.commit();
    }
    private void initText(){
        shouye_img.setImageResource(R.mipmap.sy_h);
        xinwen_img.setImageResource(R.mipmap.fx_h);
        haoyou_img.setImageResource(R.mipmap.hy_h);
        wode_img.setImageResource(R.mipmap.wd_h);

    }
    private void hintFragment(){
        FragmentTransaction ft = fm.beginTransaction();
        if (shouYeFragment!=null){
            ft.hide(shouYeFragment);
        }
        if (xinWenFragment !=null){
            ft.hide(xinWenFragment);
        }
        if (woDeFragment!=null){
            ft.hide(woDeFragment);
        }
        if (haoYouFragment!=null){
            ft.hide(haoYouFragment);
        }
        ft.commit();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(FirstEvent event) {
        finish();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
//    双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {

                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void initGetVer() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionCode = packInfo.versionCode;
    }
    private void initupload() {
        HashMap<String, String> map = new HashMap<>();
        OkUtils.UploadSJ(str, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
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
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String strh = (String) msg.obj;
                try {
                    JSONObject jo = new JSONObject(strh);
                    String ver = jo.getString("ver");
                    if (ver.equals(""+versionCode)){
                        initView();
                    }else {
                        initUtils();
                        utils.showUpdateDialog(MainActivity.this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private void initUtils() {
        utils = new UpLoadAppUtils(this);
        permission();
        utils.getVersionCode(MainActivity.this);
    }
    private void permission() {
        PermissionUtils utils = new PermissionUtils(this);
        utils.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 1000);
    }

    @Override
    public void onPermissionListener() {

            utils.showUpdateDialog(MainActivity.this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(TuiChuEvent event) {
        finish();
    }



}
