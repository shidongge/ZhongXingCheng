package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
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
 *帮助中心的界面
 */
public class BZZX extends Activity implements View.OnClickListener {

    private LinearLayout tixian,kuoda,huiyuan,dizhi,renzheng,bangding;
    private ImageView back;
    private String bangzhu;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bangzhu = getIntent().getStringExtra("bangzhu");
        setContentView(R.layout.activity_bzzx);
        initView();
    }

    private void initView() {
        tixian = (LinearLayout) findViewById(R.id.bz_tixian);
        kuoda = (LinearLayout) findViewById(R.id.bz_kuoda);
        huiyuan = (LinearLayout) findViewById(R.id.bz_huiyuan);
        dizhi = (LinearLayout) findViewById(R.id.bz_dizhi);
        renzheng = (LinearLayout) findViewById(R.id.bz_renzheng);
        back = (ImageView) findViewById(R.id.title_back);
//        tixian.setOnClickListener(this);
//        kuoda.setOnClickListener(this);
//        huiyuan.setOnClickListener(this);
//        dizhi.setOnClickListener(this);
//        renzheng.setOnClickListener(this);
//        bangding.setOnClickListener(this);

        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title_text);
        title.setText(bangzhu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.bz_tixian:
//                Intent intent1 = new Intent(BZZX.this, TiXian.class);
//                intent1.putExtra("tixian","新手帮助");
//                startActivity(intent1);
//                break;
//            case R.id.bz_kuoda:
//                Intent intent2 = new Intent(BZZX.this, KuoDa.class);
//                intent2.putExtra("kuoda","新手帮助");
//                startActivity(intent2);
//                break;
//            case R.id.bz_huiyuan:
//                Intent intent3 = new Intent(BZZX.this, HuiYuan.class);
//                intent3.putExtra("huiyuan","新手帮助");
//                startActivity(intent3);
//                break;
//            case R.id.bz_dizhi:
//                Intent intent4 = new Intent(BZZX.this, DiZhi.class);
//                intent4.putExtra("dizhi","热点问题");
//                startActivity(intent4);
//                break;
//            case R.id.bz_renzheng:
//                Intent intent5 = new Intent(BZZX.this, RenZheng.class);
//                intent5.putExtra("renzheng","热点问题");
//                startActivity(intent5);
//                break;
//            case R.id.bz_bangding:
//                Intent intent6 = new Intent(BZZX.this, BangDing.class);
//                intent6.putExtra("bangding","热点问题");
//                startActivity(intent6);
//                break;
//            case R.id.zhaq_back:
//                finish();
//                break;
//        }
//    }
}
