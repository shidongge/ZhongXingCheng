package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.adapter.SXPPAdapter;
import us.mifeng.zhongxingcheng.bean.Home_ShangPinBean;

/**
 * Created by shido on 2017/11/16.
 */

/**
 * 上新品牌界面
 */
public class ShangXinPinPai extends Activity implements View.OnClickListener {
    public ListView mLv;
    private List<Home_ShangPinBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sxpp);
        initList();
        initView();
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i =0;i<10;i++){
            Home_ShangPinBean bean = new Home_ShangPinBean();


            list.add(bean);
        }
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.sxpp_mlv);
        ImageView back = (ImageView) findViewById(R.id.sxpp_back);
        SXPPAdapter sxppAdapter = new SXPPAdapter(list, this);
        mLv.setAdapter(sxppAdapter);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sxpp_back:
                finish();
                break;
        }
    }
}