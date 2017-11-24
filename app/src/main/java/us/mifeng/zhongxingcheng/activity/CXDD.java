package us.mifeng.zhongxingcheng.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.view.MyViewPager;

/**
 * Created by shido on 2017/11/24.
 */

/**
 * 中心商城 查询订单界面
 */

public class CXDD extends FragmentActivity {

    private String zhuangtai;
    private static final String[] TITLE = new String[] { "全部", "待付款", "待发货", "已发货",
            "待评价"};
    private MyViewPager viewPager;
    private TabPageIndicator indicator;
    private int integer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cxdd);
        initView();
        zhuangtai = getIntent().getStringExtra("intent");
        integer = Integer.valueOf(zhuangtai).intValue();
        viewPager.setCurrentItem(integer);
    }

    private void initView() {
        viewPager = (MyViewPager) findViewById(R.id.dingdan_pager);
        indicator = (TabPageIndicator) findViewById(R.id.dingdan_tab);
        TabPageIndicatorAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                ToSi.show(CXDD.this,TITLE[position]);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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


            //这里是为了清楚TabPageIndicator时报的ViewPager has not been bound异常，先给TabPageIndicator设置android:visibility="gone"
            Fragment fragment = new HomeFragment_shouye();
            Bundle args = new Bundle();
            args.putString("arg", TITLE[position]);
            fragment.setArguments(args);

            return fragment;
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
