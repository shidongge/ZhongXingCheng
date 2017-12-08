package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.AddressPickTask;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 新增收货地址界面
 */
public class ZXSC_XZSHDZ extends Activity implements View.OnClickListener {

    private LinearLayout diqu;
    private TextView diqu_text, baocun;
    private String xzshdz;
    private ImageView back;
    private EditText name, mobile, xxdz, youbian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxsc_xzshdz);
        initView();
    }

    private void initView() {
        diqu = (LinearLayout) findViewById(R.id.zxsc_xzshdz_diqu);
        diqu_text = (TextView) findViewById(R.id.zxsc_xzshdz_text);
        name = (EditText) findViewById(R.id.zxsc_xzshdz_name);
        xxdz = (EditText) findViewById(R.id.zxsc_xzshdz_xxdz);
        mobile = (EditText) findViewById(R.id.zxsc_xzshdz_number);
        youbian = (EditText) findViewById(R.id.zxsc_xzshdz_youbian);
        back = (ImageView) findViewById(R.id.zxsc_xzshdz_back);
        baocun = (TextView) findViewById(R.id.zxsc_xzshdz_bc);
        diqu.setOnClickListener(this);
        back.setOnClickListener(this);
        baocun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxsc_xzshdz_diqu:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(ZXSC_XZSHDZ.this, "数据初始化失败", Toast.LENGTH_SHORT).show();
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
            case R.id.zxsc_xzshdz_back:
                finish();
                break;
            case R.id.zxsc_xzshdz_bc:
                String trim = name.getText().toString().trim();
                String trim1 = mobile.getText().toString().trim();
                String trim2 = xxdz.getText().toString().trim();
                String trim3 = youbian.getText().toString().trim();
                String trim4 = diqu_text.getText().toString().trim();
                if (trim.equals("") || trim1.equals("") || trim2.equals("") || trim3.equals("") || trim4.equals("")) {
                    ToSi.show(ZXSC_XZSHDZ.this, "信息未填写完整");
                } else {
                    ToSi.show(ZXSC_XZSHDZ.this, "信息已完善");
                }
                break;
            default:
                break;
        }
    }
}
