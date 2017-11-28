package us.mifeng.zhongxingcheng.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.viewpagerindicator.TabPageIndicator;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.view.MyViewPager;

/**
 * Created by shido on 2017/11/17.
 */

/**
 * 我的订单界面
 */
public class WDDD extends FragmentActivity {
    private static final String[] TITLE = new String[] { "全部", "待付款", "待发货", "已发货",
            "待评价"};
    private MyViewPager pager;
    private TabPageIndicator tabPageIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        pager = (MyViewPager) findViewById(R.id.wddd_pager);
        tabPageIndicator = (TabPageIndicator) findViewById(R.id.wdddd_tab);
    }
}
