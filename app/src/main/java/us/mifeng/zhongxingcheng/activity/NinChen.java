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
import us.mifeng.zhongxingcheng.utils.NinChengEvent;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 昵称界面
 */
public class NinChen extends Activity {

    private TextView bc;
    private EditText nc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninchen);
        initView();
    }

    private void initView() {
        bc = (TextView) findViewById(R.id.nc_bc);
        nc = (EditText) findViewById(R.id.nc_nc);
        ImageView back = (ImageView) findViewById(R.id.nincheng_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = nc.getText().toString().trim();
                if (trim.equals("")){
                    ToSi.show(NinChen.this,"请输入昵称");
                }else {
                    EventBus.getDefault().post(new NinChengEvent(trim));
                    finish();
                }
            }
        });
    }
}
