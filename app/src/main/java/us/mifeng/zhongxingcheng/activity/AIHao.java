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
import us.mifeng.zhongxingcheng.utils.AiHaoEvent;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/8.
 */

/**
 * 爱好界面
 */
public class AIHao extends Activity implements View.OnClickListener {

    private EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aihao);
        initView();
    }

    private void initView() {
        TextView baocun = (TextView) findViewById(R.id.aihao_bc);
        edit = (EditText) findViewById(R.id.aihao_aihao);
        ImageView back = (ImageView) findViewById(R.id.aihao_back);
        back.setOnClickListener(this);
        baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aihao_back:
                finish();
                break;
            case R.id.aihao_bc:
                String trim = edit.getText().toString().trim();
                if ("".equals(trim)) {
                    ToSi.show(AIHao.this,"请输入爱好");
                }else {
                    EventBus.getDefault().post(new AiHaoEvent(trim));
                    finish();
                }
                break;
        }
    }
}
