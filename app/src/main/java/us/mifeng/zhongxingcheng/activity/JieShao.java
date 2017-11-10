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
import us.mifeng.zhongxingcheng.utils.QianMingEvent;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 个人签名界面
 */

public class JieShao extends Activity {

    private TextView bc;
    private EditText qm;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianming);
        initView();
    }
    private void initView() {
        back = (ImageView) findViewById(R.id.qianming_back);
        bc = (TextView) findViewById(R.id.qm_bc);
        qm = (EditText) findViewById(R.id.qm_edit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = qm.getText().toString().trim();
                if (trim.equals("")){
                    ToSi.show(JieShao.this,"请输入签名");
                }else {
                    EventBus.getDefault().post(new QianMingEvent(trim));
                    finish();
                }
            }
        });
    }

}
