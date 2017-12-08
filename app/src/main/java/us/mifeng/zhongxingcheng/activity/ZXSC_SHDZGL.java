package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 收货地址管理界面
 */
public class ZXSC_SHDZGL extends Activity implements View.OnClickListener {

    private TextView xz;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc_shdzgl);
        initView();
    }

    private void initView() {
        xz = (TextView) findViewById(R.id.zxsc_shdzgl_xz);
        back = (ImageView) findViewById(R.id.zxsc_shdzgl_back);
        xz.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zxsc_shdzgl_xz:
                Intent intent = new Intent(ZXSC_SHDZGL.this, XZSHDZ.class);
                intent.putExtra("xzshdz","新增收货地址");
                startActivity(intent);
                break;
            case R.id.zxsc_shdzgl_back:
                finish();
                break;
            default:
                break;
        }
    }
}
