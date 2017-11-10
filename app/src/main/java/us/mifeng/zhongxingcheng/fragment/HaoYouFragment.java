package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.adapter.LXRAdapter;
import us.mifeng.zhongxingcheng.bean.LXRBean;


/**
 * Created by shido on 2017/10/17.
 */

public class HaoYouFragment extends Fragment implements OnLoadMoreListener, AdapterView.OnItemClickListener {
    private Bundle bundle;
    private LXRAdapter adapter;
    private List<LXRBean> list;
    private int start;
    private int item;
    //保证数据加载时的准确性
    private boolean isState = false;
    private View infalter;
    private SwipeToLoadLayout swipe;
    private ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        infalter = View.inflate(getActivity(), R.layout.fragment_haoyou, null);
        initView();
        //用于分段从list中取值
        start = 0;
        item = 0;
        initData();
        return infalter;
    }

    private void initView() {
        swipe = (SwipeToLoadLayout) infalter.findViewById(R.id.haoyou_swipe);
        lv = (ListView) infalter.findViewById(R.id.haoyou_lv);
        swipe.setOnLoadMoreListener(this);
        lv.setOnItemClickListener(this);
        list = new ArrayList<>();
    }

    private void initData() {
        //获取保存的状态
        if (bundle != null && isState == true) {
            item = bundle.getInt("item");
            isState = false;
        } else {
            //赋值，确保不重复取值
            start = item;
            item += 20;
        }
        for (int i = start; i < item; i++) {
            LXRBean bean = new LXRBean();
            bean.setMobile(i + "");
            list.add(bean);
        }
        if (adapter == null) {
            adapter = new LXRAdapter(getActivity(), list);
            lv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMore() {
        initData();
        swipe.setLoadingMore(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
