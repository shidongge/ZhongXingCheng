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
import us.mifeng.zhongxingcheng.fragment.WeiShiYongFragment;
import us.mifeng.zhongxingcheng.fragment.YiGuoQiFragment;
import us.mifeng.zhongxingcheng.fragment.YiShiYongFragment;

/**
 * Created by shido on 2017/11/24.
 */

public class ZXSC_YouHuiQuan extends FragmentActivity implements View.OnClickListener {

    private FragmentManager fm;
    private WeiShiYongFragment weiShiYongFragment;
    private YiShiYongFragment yiShiYongFragment;
    private YiGuoQiFragment yiGuoQiFragment;
    private TextView weishiyong, yishiyong, yiguoqi;
    private LinearLayout da;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc_yhq);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        weiShiYongFragment = new WeiShiYongFragment();
        ft.add(R.id.yhq_da, weiShiYongFragment);
        ft.commit();
        initView();
    }

    private void initView() {
        weishiyong = (TextView) findViewById(R.id.activity_yhq_weishiyong);
        yishiyong = (TextView) findViewById(R.id.activity_yhq_yishiyong);
        yiguoqi = (TextView) findViewById(R.id.activity_yhq_yiguoqi);
        ImageView back = (ImageView) findViewById(R.id.yhq_back);
        da = (LinearLayout) findViewById(R.id.yhq_da);
        weishiyong.setOnClickListener(this);
        yishiyong.setOnClickListener(this);
        yiguoqi.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    public void hintFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (weiShiYongFragment != null) {
            ft.hide(weiShiYongFragment);
        }
        if (yiShiYongFragment != null) {
            ft.hide(yiShiYongFragment);
        }
        if (yiGuoQiFragment != null) {
            ft.hide(yiGuoQiFragment);
        }
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        hintFragment();
        relateText();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.activity_yhq_weishiyong:
                weishiyong.setBackgroundColor(Color.parseColor("#777777"));
                weishiyong.setTextColor(Color.parseColor("#ffffff"));
                if (weiShiYongFragment == null) {
                    ft.add(R.id.yhq_da, weiShiYongFragment);
                } else {
                    ft.show(weiShiYongFragment);
                }
                break;
            case R.id.activity_yhq_yishiyong:
                yishiyong.setBackgroundColor(Color.parseColor("#777777"));
                yishiyong.setTextColor(Color.parseColor("#ffffff"));
                if (yiShiYongFragment == null) {
                    yiShiYongFragment = new YiShiYongFragment();
                    ft.add(R.id.yhq_da, yiShiYongFragment);
                } else {
                    ft.show(yiShiYongFragment);
                }
                break;
            case R.id.activity_yhq_yiguoqi:
                yiguoqi.setBackgroundColor(Color.parseColor("#777777"));
                yiguoqi.setTextColor(Color.parseColor("#ffffff"));
                if (yiGuoQiFragment == null) {
                    yiGuoQiFragment = new YiGuoQiFragment();
                    ft.add(R.id.yhq_da, yiGuoQiFragment);
                } else {
                    ft.show(yiGuoQiFragment);
                }
                break;
            case R.id.yhq_back:
                finish();
                break;
        }
        ft.commit();
    }

    public void relateText() {
        weishiyong.setBackgroundColor(Color.parseColor("#ffffff"));
        yishiyong.setBackgroundColor(Color.parseColor("#ffffff"));
        yiguoqi.setBackgroundColor(Color.parseColor("#ffffff"));
        weishiyong.setTextColor(Color.parseColor("#000000"));
        yishiyong.setTextColor(Color.parseColor("#000000"));
        yiguoqi.setTextColor(Color.parseColor("#000000"));
    }

}
