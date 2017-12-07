package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.HomeFragment_shouye;
import us.mifeng.zhongxingcheng.view.MyViewPager;

/**
 * Created by shido on 2017/12/6.
 */

public class SPZX_DD extends Fragment implements View.OnClickListener {
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
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_spzx_dd, null);
        fm = getFragmentManager();
        initView();
        return inflate;
    }

    private void initView() {
        back = (ImageView) inflate.findViewById(R.id.dingdan_back);
        back.setOnClickListener(this);

        viewpager = (MyViewPager) inflate.findViewById(R.id.dingdan_pager);
        tabPageIndicator = (TabPageIndicator) inflate.findViewById(R.id.dingdan_tab);
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getFragmentManager());
        viewpager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dingdan_back:
                getActivity().finish();
                break;
            default:
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
