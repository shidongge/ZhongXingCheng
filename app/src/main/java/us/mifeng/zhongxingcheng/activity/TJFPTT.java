package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/8.
 */
//添加发票抬头
public class TJFPTT extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjfptt);
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.tjttfp_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tjttfp_back:
                finish();
                break;
        }
    }
}
