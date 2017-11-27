package us.mifeng.zhongxingcheng.fragment;

import android.view.View;
import android.widget.ImageView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.base.BaseFragment;

/**
 * Created by shido on 2017/11/27.
 */

public class ZXSC_GouWuCheFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_gouwuche, null);
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_gouwuche_back);
        back.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void initList() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zxsc_gouwuche_back:
                getActivity().finish();
                break;
        }
    }
}
