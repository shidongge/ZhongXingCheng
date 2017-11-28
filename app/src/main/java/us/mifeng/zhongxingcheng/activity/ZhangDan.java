package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/14.
 */

public class ZhangDan extends Activity implements View.OnClickListener {

    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhangdan);

        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.zd_back);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zd_back:
                finish();
                break;
        }
    }
}
