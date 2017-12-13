package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/12/1.
 */

public class DD_DaiShouHuo extends Fragment {
    private ListView lv;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.dd_daishouhuo, null);
        initList();
        initView();
        return inflate;
    }

    public View initView() {
        View inflate = View.inflate(getActivity(), R.layout.dd_daishouhuo, null);
        lv = (ListView) inflate.findViewById(R.id.dd_daishouhuo_lv);

        return inflate;
    }


    public void initList() {
        list = new ArrayList<>();
        for (int i = 0 ;i<4;i++){
            list.add(i+"");
        }
    }


}
