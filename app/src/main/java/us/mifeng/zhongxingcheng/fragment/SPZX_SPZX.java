package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.wpxq_ceshi.WPXQ_CeSi;

/**
 * Created by shido on 2017/12/6.
 */

public class SPZX_SPZX extends Fragment implements View.OnClickListener {

    private View inflate;
    private LinearLayout yihao,  sanhao, sihao, wuhao, liuhao;
    private RelativeLayout erhao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_spzx_spzx, null);
        initView();
        return inflate;
    }

    private void initView() {
        yihao = (LinearLayout) inflate.findViewById(R.id.spzx_spzx_yihao);
        erhao = (RelativeLayout) inflate.findViewById(R.id.spzx_spzx_erhao);
        sanhao = (LinearLayout) inflate.findViewById(R.id.spzx_spzx_sanhao);
        sihao = (LinearLayout) inflate.findViewById(R.id.spzx_spzx_sihao);
        wuhao = (LinearLayout) inflate.findViewById(R.id.spzx_spzx_wuhao);
        liuhao = (LinearLayout) inflate.findViewById(R.id.spzx_spzx_liuhao);
        yihao.setOnClickListener(this);
        erhao.setOnClickListener(this);
        sanhao.setOnClickListener(this);
        sihao.setOnClickListener(this);
        wuhao.setOnClickListener(this);
        liuhao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spzx_spzx_yihao:
                Intent yihao = new Intent(getActivity(), WPXQ_CeSi.class);
                startActivity(yihao);
                break;
            case R.id.spzx_spzx_erhao:
                Intent erhao = new Intent(getActivity(), WPXQ_CeSi.class);
                startActivity(erhao);
                break;
            case R.id.spzx_spzx_sanhao:
                Intent sanhao = new Intent(getActivity(), WPXQ_CeSi.class);
                startActivity(sanhao);
                break;
            case R.id.spzx_spzx_sihao:
                Intent sihao = new Intent(getActivity(), WPXQ_CeSi.class);
                startActivity(sihao);
                break;
            case R.id.spzx_spzx_wuhao:
                Intent wuhao = new Intent(getActivity(), WPXQ_CeSi.class);
                startActivity(wuhao);
                break;
            case R.id.spzx_spzx_liuhao:
                Intent liuhao = new Intent(getActivity(), WPXQ_CeSi.class);
                startActivity(liuhao);
                break;



            default:
                break;
        }
    }
}
