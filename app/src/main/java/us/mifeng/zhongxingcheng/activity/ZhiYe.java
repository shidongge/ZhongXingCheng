package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.ZhiYeEvent;

/**
 * Created by shido on 2017/11/8.
 */

public class ZhiYe extends Activity implements View.OnClickListener {

    private ImageView back;
    private TextView baocun;
    private EditText zhiye;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhiye);
        initView();
    }

    private void initView() {
        zhiye = (EditText) findViewById(R.id.zhiye_zhiye);
        baocun = (TextView) findViewById(R.id.zhiye_bc);
        back = (ImageView) findViewById(R.id.zhiye_back);
        back.setOnClickListener(this);

        baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhiye_back:
                finish();
                break;
            case R.id.zhiye_bc:
                String trim = zhiye.getText().toString().trim();
                if (trim.equals("")){
                    ToSi.show(ZhiYe.this,"请输入职业");
                }else {
                    EventBus.getDefault().post(new ZhiYeEvent(trim));
                    finish();
                }

                break;
        }
    }

}
