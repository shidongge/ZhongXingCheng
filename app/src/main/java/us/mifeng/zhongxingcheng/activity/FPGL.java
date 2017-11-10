package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/10.
 */
//发票管理界面
public class FPGL extends Activity {

    private ListView mlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpgl);
        initView();
    }

    private void initView() {
        mlv = (ListView) findViewById(R.id.fpgl_lv);
    }
}
