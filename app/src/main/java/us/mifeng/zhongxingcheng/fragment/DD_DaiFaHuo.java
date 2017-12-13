package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/12/1.
 */

public class DD_DaiFaHuo extends Fragment {
    private ListView lv;
    private List<String> list;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.dd_daifahuo, null);
        initView();
        return inflate;

    }

    private void initView() {
        lv = (ListView) inflate.findViewById(R.id.dd_daifahuo_lv);
    }
}
