package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/3.
 */

public class DianPu extends Activity implements View.OnClickListener {

    private RecyclerView youhuiquan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianpu);
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.title_back);
        TextView biaoti = (TextView) findViewById(R.id.title_text);
        youhuiquan = (RecyclerView) findViewById(R.id.dianpu_youhuiquan);

        biaoti.setText("商家店铺");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }
}