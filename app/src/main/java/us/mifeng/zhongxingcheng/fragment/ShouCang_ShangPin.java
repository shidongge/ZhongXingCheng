package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.ZXSC_Android;
import us.mifeng.zhongxingcheng.base.BaseFragment;

/**
 * Created by shido on 2017/11/24.
 */

public class ShouCang_ShangPin extends BaseFragment implements View.OnClickListener {

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_shoucang_shangpin, null);
        TextView guangguang = (TextView) inflate.findViewById(R.id.shangpin_gg);
        guangguang.setTextColor(Color.parseColor("#00ff00"));
        guangguang.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        guangguang.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void initList() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shangpin_gg:
                Intent intent = new Intent(getActivity(),ZXSC_Android.class);
                intent.putExtra("page",1);
                startActivity(intent);
                break;
        }
    }
}
