package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.AddressPickTask;

/**
 * Created by shido on 2017/10/30.
 */

public class XZSHDZ extends Activity implements View.OnClickListener {

    private LinearLayout diqu;
    private TextView diqu_text;
    private String xzshdz;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzshdz);
        initView();
    }

    private void initView() {
        diqu = (LinearLayout) findViewById(R.id.xzshdz_diqu);
        diqu_text = (TextView) findViewById(R.id.xzshdz_text);
        back = (ImageView) findViewById(R.id.xzshdz_back);
        diqu.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xzshdz_diqu:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(XZSHDZ.this, "数据初始化失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            final String address = province.getAreaName() + city.getAreaName();
                            diqu_text.setText(address);
                        } else {
                            final String address = province.getAreaName() + city.getAreaName() + county.getAreaName();
                            diqu_text.setText(address);
                        }
                    }
                });
                task.execute("北京市", "北京市", "东城区");
                break;
            case R.id.xzshdz_back:
                finish();
                break;
        }
    }
}
