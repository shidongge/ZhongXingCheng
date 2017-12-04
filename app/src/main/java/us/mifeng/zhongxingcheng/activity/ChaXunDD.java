package us.mifeng.zhongxingcheng.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.fragment.DD_DaiFaHuo;
import us.mifeng.zhongxingcheng.fragment.DD_DaiFuKuan;
import us.mifeng.zhongxingcheng.fragment.DD_DaiPingJia;
import us.mifeng.zhongxingcheng.fragment.DD_DaiShouHuo;
import us.mifeng.zhongxingcheng.fragment.DD_QuanBu;
import us.mifeng.zhongxingcheng.view.MyViewPager;


/**
 * Created by shido on 2017/8/9.
 */

/**
 * 查询订单界面
 */
public class ChaXunDD extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "ChaXunDD";
    private static final String[] TITLE = new String[]{"全部", "待支付", "待发货","待收货","待评价"};
    private MyViewPager viewpager;
    private TabPageIndicator tabPageIndicator;
    private String zhuangtai;
    private int integer;
    private ImageView back;
    private List<Fragment> list;
    private FragmentManager fm;
    private DD_QuanBu dd_quanBu;
    private DD_DaiFuKuan dd_daiFuKuan;
    private DD_DaiFaHuo dd_daiFaHuo;
    private DD_DaiShouHuo dd_daiShouHuo;
    private DD_DaiPingJia dd_daiPingJia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cxdd);
        fm = getSupportFragmentManager();
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
            FragmentTransaction ft = fm.beginTransaction();
            Log.e(TAG, "getItem: 00" + position);
            if (position==0){
                Fragment fragment = new DD_DaiFuKuan();
                return fragment;
            }
            else if (position==1){
                Fragment fragment = new DD_DaiFuKuan();
                return fragment;
            }else if (position==2){
                Fragment fragment = new DD_DaiFuKuan();
                return fragment;
                }
             else if (position==3){
                Fragment fragment = new DD_DaiFuKuan();
                return fragment;
            }else if (position==4){
                Fragment fragment = new DD_DaiFuKuan();
                return fragment;
            }

            else {
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

}
