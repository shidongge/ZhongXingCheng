package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/8.
 */
//添加发票抬头
public class TJFPTT extends Activity implements View.OnClickListener {

    private EditText name;
    private EditText shibiema;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjfptt);
        initView();
    }

    private void initView() {
        TextView xinzeng = (TextView) findViewById(R.id.tjttfp_baocun);
        ImageView back = (ImageView) findViewById(R.id.tjttfp_back);
        name = (EditText) findViewById(R.id.tjttfp_name);
        shibiema = (EditText) findViewById(R.id.tjttfp_shibiema);
        back.setOnClickListener(this);
        xinzeng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tjttfp_back:
                finish();
                break;
            case R.id.tjttfp_baocun:
                String trim = name.getText().toString().trim();
                String trim1 = shibiema.getText().toString().trim();
                if (trim.equals("")||trim1.equals("")){
                    ToSi.show(TJFPTT.this,"信息填写不完整，请重新检查输入框");
                }else {
                    ToSi.show(TJFPTT.this,"填写完整");
                }
                break;
        }
    }

}
