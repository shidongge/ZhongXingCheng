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
 * Created by shido on 2017/10/20.
 */

/**
 * 帮助中心的界面
 */
public class BZZX extends Activity implements View.OnClickListener {

    private LinearLayout liucheng, jiage, tiaokuan, fukuan, shibai;
    private ImageView back;
    private String bangzhu;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bzzx);
        initView();
    }

    private void initView() {
        liucheng = (LinearLayout) findViewById(R.id.bz_liucheng);
        jiage = (LinearLayout) findViewById(R.id.bz_jiage);
        tiaokuan = (LinearLayout) findViewById(R.id.bz_tiaokuan);
        fukuan = (LinearLayout) findViewById(R.id.bz_fukuan);
        shibai = (LinearLayout) findViewById(R.id.bz_shibai);
        back = (ImageView) findViewById(R.id.bbzx_back);

        liucheng.setOnClickListener(this);
        jiage.setOnClickListener(this);
        tiaokuan.setOnClickListener(this);
        fukuan.setOnClickListener(this);
        shibai.setOnClickListener(this);

        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bz_liucheng:
                startActivity(new Intent(BZZX.this,GMLC.class));
                break;
            case R.id.bz_jiage:
                startActivity(new Intent(BZZX.this,SCJGSM.class));
                break;
            case R.id.bz_tiaokuan:
                startActivity(new Intent(BZZX.this,JYTK.class));
                break;
            case R.id.bz_fukuan:

                startActivity(new Intent(BZZX.this,RHFK.class));
                break;
            case R.id.bz_shibai:

                startActivity(new Intent(BZZX.this,ZFSB.class));
                break;
            case R.id.title_back:
                finish();
                break;
            default:
                break;
        }
    }
}
