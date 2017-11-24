package us.mifeng.zhongxingcheng.fragment;

import android.view.View;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.base.BaseFragment;

/**
 * Created by shido on 2017/11/24.
 */

public class ShouCang_DianPu extends BaseFragment {

    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("我是店铺界面");
        return textView;
    }

    @Override
    protected void initList() {

    }


}
