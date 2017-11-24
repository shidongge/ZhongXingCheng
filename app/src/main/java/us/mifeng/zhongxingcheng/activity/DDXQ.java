package us.mifeng.zhongxingcheng.activity;

/**
 * Created by shido on 2017/11/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import us.mifeng.zhongxingcheng.R;

/**
 * 订单详情界面
 */
public class DDXQ extends Activity implements View.OnClickListener {

    private Button tjdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddxq);
        initView();
    }

    private void initView() {
        tjdd = (Button) findViewById(R.id.ddxp_tjdd);
        tjdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ddxp_tjdd:
                AlertDialog.Builder builder = new AlertDialog.Builder(DDXQ.this);
                break;
        }
    }
}
