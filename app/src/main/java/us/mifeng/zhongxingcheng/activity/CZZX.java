package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/1.
 */

/**
 * 充值中心
 */
public class CZZX extends Activity implements View.OnClickListener {
    private ImageView you, wxzf, kqzf;
    private LinearLayout fangshi, yincang, kuaiqian, weixin;
    private EditText jine;
    private boolean hah = true;
    private TextView text;
    private static final String TAG = "CZZX";
    private Button chongzhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czzx);
        initView();
    }

    private void initView() {
        chongzhi = (Button) findViewById(R.id.czgl_chongzhi);
        text = (TextView) findViewById(R.id.czgl_zffs_text);
        you = (ImageView) findViewById(R.id.czgl_zffs_you);
        wxzf = (ImageView) findViewById(R.id.czgl_wxzf);
        kqzf = (ImageView) findViewById(R.id.czgl_kqzf);
        fangshi = (LinearLayout) findViewById(R.id.czgl_fangshi);
        jine = (EditText) findViewById(R.id.czgl_jine);
        yincang = (LinearLayout) findViewById(R.id.czgl_yincang);
        kuaiqian = (LinearLayout) findViewById(R.id.czgl_ll_kuaiqian);
        weixin = (LinearLayout) findViewById(R.id.czgl_ll_weixin);
        fangshi.setOnClickListener(this);
        weixin.setOnClickListener(this);
        kuaiqian.setOnClickListener(this);
        chongzhi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.czgl_fangshi:
                if (hah == false) {
                    yincang.setVisibility(View.GONE);
                    hah = true;
                } else {
                    yincang.setVisibility(View.VISIBLE);
                    hah = false;
                }
                break;
            case R.id.czgl_ll_kuaiqian:
                text.setText("快钱支付");
                if (hah==false){
                    hah=true;
                }else {
                    hah=false;
                }
                wxzf.setImageResource(R.mipmap.weigou);
               kqzf.setImageResource(R.mipmap.yigou);
                yincang.setVisibility(View.GONE);
                break;
            case R.id.czgl_ll_weixin:
                if (hah==false){
                    hah=true;
                }else {
                    hah=false;
                }
                kqzf.setImageResource(R.mipmap.weigou);
                wxzf.setImageResource(R.mipmap.yigou);
                yincang.setVisibility(View.GONE);
                text.setText("微信支付");
                break;
            case R.id.czgl_chongzhi:
                String trim = jine.getText().toString().trim();
                if ("".equals(trim)){
                    ToSi.show(CZZX.this,"请输入充值金额");
                }else {

                    Integer integer = Integer.valueOf(trim);
                    //判断能否被100整除
                    int i = integer % 100;
                    if (i==0){
                        ToSi.show(CZZX.this,"充值成功");
                    }else {
                        ToSi.show(CZZX.this,"充值金额有误");
                    }
                }

                break;
            default:
                break;
        }
    }
    public void setImg(){
        kqzf.setImageResource(R.mipmap.weigou);
        wxzf.setImageResource(R.mipmap.weigou);
    }
}
