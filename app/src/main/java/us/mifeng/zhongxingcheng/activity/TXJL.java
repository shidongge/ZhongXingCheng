package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/12/7.
 */

/**
 * 提现记录界面
 */
public class TXJL extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txjl);
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.txjl_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txjl_back:
                finish();
                break;
            default:
                break;
        }
    }
}
