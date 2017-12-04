package us.mifeng.zhongxingcheng.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.adapter.DD_DaiFuKuanAdapter;
import us.mifeng.zhongxingcheng.base.LJZBaseFragment;

/**
 * Created by shido on 2017/12/1.
 */

public class DD_QuanBu extends LJZBaseFragment {
    private ListView lv;
    private List<String> list;

    @Override
    public View initView() {
        View inflate = View.inflate(getActivity(), R.layout.dd_daifukuai, null);
        lv = (ListView) inflate.findViewById(R.id.dd_daifukan_lv);

        return inflate;
    }

    @Override
    public void initList() {
        list = new ArrayList<>();
        for (int i = 0 ;i<4;i++){
            list.add(i+"");
        }
    }

    @Override
    public void setOnclick() {

    }

    @Override
    protected void Start() {
        DD_DaiFuKuanAdapter dd_daiFuKuanAdapter = new DD_DaiFuKuanAdapter(list, getActivity());
        lv.setAdapter(dd_daiFuKuanAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
