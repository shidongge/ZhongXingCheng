package us.mifeng.zhongxingcheng.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.viewpagerindicator.TabPageIndicator;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.DD_DaiFuKuan;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.view.MyViewPager;


/**
 * Created by shido on 2017/8/9.
 */

public class DingDan extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "DingDan";
    private static final String[] TITLE = new String[]{"全部", "待支付", "待发货","待收货","待评价"};
    private MyViewPager viewpager;
    private TabPageIndicator tabPageIndicator;
    private String zhuangtai;
    private int integer;
    private ImageView back;
    private ImageView sousuo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cxdd);
        TongMing();
        initView();
        zhuangtai = getIntent().getStringExtra("intent");
        integer = Integer.valueOf(zhuangtai).intValue();
        viewpager.setCurrentItem(integer);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.dingdan_back);
        back.setOnClickListener(this);
        viewpager = (MyViewPager) findViewById(R.id.dingdan_pager);
        tabPageIndicator = (TabPageIndicator) findViewById(R.id.dingdan_tab);
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewpager);
        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ToSi.show(DingDan.this, TITLE[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dingdan_back:
                finish();
                break;
        }
    }


    /**
     * 适配器代码
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Log.e(TAG, "getItem: 00" + position);
            if (position==1){
                Fragment fragment = new DD_DaiFuKuan();
                return fragment;
            }else {

                //这里是为了清楚TabPageIndicator时报的ViewPager has not been bound异常，先给TabPageIndicator设置android:visibility="gone"
                Fragment fragment = new HomeFragment_shouye();
                Bundle args = new Bundle();
                args.putString("arg", TITLE[position]);
                fragment.setArguments(args);
                return fragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return TITLE[position % TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }

    //设置状态栏
    public void TongMing() {
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
