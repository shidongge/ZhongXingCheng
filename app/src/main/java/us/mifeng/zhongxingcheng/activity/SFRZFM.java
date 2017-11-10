package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/3.
 */
//身份认证反面
public class SFRZFM extends Activity implements View.OnClickListener {

    private String sfrzzm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfrzfm);
        sfrzzm = getIntent().getStringExtra("sfrzzm");
        initView();


    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.title_back);
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText(sfrzzm);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}
