package us.mifeng.zhongxingcheng.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.dianpu.SimpleViewPagerIndicator;
import us.mifeng.zhongxingcheng.dianpu.TabFragment;
import us.mifeng.zhongxingcheng.wpxq_ceshi.NoScrollViewPager;

/**
 * Created by shido on 2017/11/3.
 */

public class DianPu extends FragmentActivity implements View.OnClickListener {
    private String[] mTitles = new String[]{"全部", "销量", "价格","新品","筛选"};
    private RecyclerView youhuiquan;
    private SimpleViewPagerIndicator mIndicator;
    private NoScrollViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_pu);
        initView();

        //；粘性布局
        initViews();


    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.title_back);
        TextView biaoti = (TextView) findViewById(R.id.title_text);
        youhuiquan = (RecyclerView) findViewById(R.id.dianpu_youhuiquan);

        biaoti.setText("商家店铺");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }

    private void initViews() {
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
//        mViewPager = (NoScrollViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        mIndicator.setIndicatorListener(new SimpleViewPagerIndicator.OnIndicatorClickListener() {
            @Override
            public void onIndicator(TextView tv) {
                if ("全部".equals(tv.getText())) {
                    mViewPager.setCurrentItem(0);
                }
                if ("销量".equals(tv.getText())) {
                    mViewPager.setCurrentItem(1);
                }
                if ("价格".equals(tv.getText())) {
                    mViewPager.setCurrentItem(2);
                }
                if ("新品".equals(tv.getText())) {
                    mViewPager.setCurrentItem(3);
                }
                if ("筛选".equals(tv.getText())) {
                    mViewPager.setCurrentItem(4);
                }
            }
        });

    }
    private void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}