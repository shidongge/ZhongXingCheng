package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/12/8.
 */

public class WDQB extends Activity implements View.OnClickListener {

    private LinearLayout tx,cz;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdqb);
        initView();
    }

    private void initView() {
        tx = (LinearLayout) findViewById(R.id.wdqb_tx);
        cz = (LinearLayout) findViewById(R.id.wdqb_cz);
        back = (ImageView) findViewById(R.id.wdqb_back);
        tx.setOnClickListener(this);
        cz.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wdqb_tx:
                ToSi.show(WDQB.this,"功能暂停使用");
                break;
            case R.id.wdqb_cz:
                break;
            case R.id.wdqb_back:
                finish();
                break;
            default:
        break;}
    }
}
