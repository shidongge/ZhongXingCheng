package us.mifeng.zhongxingcheng.fragment;

import android.view.View;
import android.widget.ImageView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.base.BaseFragment;


/**
 * Created by shido on 2017/11/27.
 */

public class ZXSC_GouWuCheFragment extends BaseFragment {


    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_gouwuchefragment, null);
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_gouwuche_back);
//        buttom = (LinearLayout) inflate.findViewById(R.id.llayout_bottom);
        return inflate;
    }

    @Override
    protected void initList() {

    }


}
