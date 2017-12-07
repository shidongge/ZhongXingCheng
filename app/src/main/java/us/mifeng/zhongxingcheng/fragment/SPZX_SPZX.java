package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/12/6.
 */

public class SPZX_SPZX extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_spzx_spzx, null);
        return inflate;
    }
}
