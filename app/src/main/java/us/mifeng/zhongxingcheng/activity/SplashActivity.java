package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.denlgu.Activity_denglu;
import us.mifeng.zhongxingcheng.utils.SharedUtils;

/**
 * Created by shido on 2017/10/31.
 */

public class SplashActivity extends Activity {
    private int inex = 3;
    private LinearLayout ll_time;
    private TextView time;
    boolean xx=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ll_time = (LinearLayout) findViewById(R.id.ll_time);
        time = (TextView) findViewById(R.id.time);
        ll_time.setVisibility(View.VISIBLE);
        time.setText(inex+"");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (xx){
                            try {
                                Thread.sleep(1000);
                                hand.sendEmptyMessage(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                inex--;
                time.setText(inex+"");
                if (inex==0){
                    xx=false;
                    ll_time.setVisibility(View.GONE);
                    String tag = new SharedUtils().getShared("token",SplashActivity.this);
                    if (tag.equals("")){
                        startActivity(new Intent(SplashActivity.this,Activity_denglu.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
                    finish();
                }
            }
        }
    };
}
