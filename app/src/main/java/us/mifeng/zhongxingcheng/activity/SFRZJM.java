package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/3.
 */

public class SFRZJM extends Activity implements View.OnClickListener {

    private String zjzp;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfrzjm);
        zjzp = getIntent().getStringExtra("zjzp");
        initView();
    }

    private void initView() {
        LinearLayout fanmian = (LinearLayout) findViewById(R.id.sfrzjm_fanmian);
        LinearLayout zhengmian = (LinearLayout) findViewById(R.id.sfrzjm_zhengmian);
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText(zjzp);
        back = (ImageView) findViewById(R.id.title_back);
        fanmian.setOnClickListener(this);
        zhengmian.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sfrzjm_fanmian:
                Intent intent = new Intent(SFRZJM.this, SFRZFM.class);
                intent.putExtra("sfrzfm","身份认证反面");
                startActivity(intent);
                break;
            case R.id.sfrzjm_zhengmian:
                Intent intent1 = new Intent(SFRZJM.this, SFRZZM.class);
                intent1.putExtra("sfrzzm","身份认证正面");
                startActivity(intent1);
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
