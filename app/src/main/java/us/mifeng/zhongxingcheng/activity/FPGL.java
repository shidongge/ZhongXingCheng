package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/10.
 */
//发票管理界面
public class FPGL extends Activity implements View.OnClickListener {

    private ListView mlv;
    private TextView xinzeng;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpgl);
        initView();
    }

    private void initView() {
        mlv = (ListView) findViewById(R.id.fpgl_lv);
        xinzeng = (TextView) findViewById(R.id.fpgl_xinzeng);
        back = (ImageView) findViewById(R.id.fpgl_back);
        back.setOnClickListener(this);
        xinzeng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fpgl_xinzeng:
                startActivity(new Intent(FPGL.this,TJFPTT.class));
                break;
            case R.id.fpgl_back:
                finish();
                break;
        }
    }
}
