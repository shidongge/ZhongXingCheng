package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.ShangXinPinPai;

/**
 * Created by shido on 2017/11/16.
 */

public class ZXSC_FenLeiFragment extends Fragment implements View.OnClickListener {

    private TextView sousuo;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_fenlei, null);
        sousuo = (TextView) inflate.findViewById(R.id.zxsc_fenlei_sousuo);
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_home_back);
        back.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zxsc_fenlei_sousuo:
                startActivity(new Intent(getActivity(), ShangXinPinPai.class));
                break;
            case R.id.zxsc_home_back:
                getActivity().finish();
                break;
        }
    }
}
