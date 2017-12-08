package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/1.
 */

/**
 * 充值中心
 */
public class CZZX extends Activity implements View.OnClickListener {
    private ImageView you;
    private LinearLayout fangshi,yincang;
    private EditText jine;
    private boolean hah = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czzx);
        initView();
    }

    private void initView() {
        you = (ImageView) findViewById(R.id.czgl_zffs_you);
        fangshi = (LinearLayout) findViewById(R.id.czgl_fangshi);
        jine = (EditText) findViewById(R.id.czgl_jine);
        yincang = (LinearLayout) findViewById(R.id.czgl_yincang);

        fangshi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.czgl_fangshi:
                if (hah=false){
                    yincang.setVisibility(View.GONE);
                    hah=true;
                }else {
                    yincang.setVisibility(View.VISIBLE);
                    hah=false;
                }
                break;
            default:
                break;
        }
    }
}
