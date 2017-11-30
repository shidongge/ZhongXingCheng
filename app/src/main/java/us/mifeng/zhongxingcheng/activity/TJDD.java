package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.adapter.TJDDAdapter;
import us.mifeng.zhongxingcheng.bean.Home_ShangPinBean;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.view.MyListView;

/**
 * Created by shido on 2017/11/17.
 */

/**
 * 提交订单界面
 */
public class TJDD extends Activity implements View.OnClickListener {
    private static final String TAG = "TJDD";
    private Button tjdd;
    private MyListView mlv;
    private List<Home_ShangPinBean> list;
    private TJDDAdapter tjddAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjdd);
        initList();
        initView();
    }

    private void initList() {
        list= new ArrayList<>();
        for (int i =0;i<1;i++){
            Home_ShangPinBean bean = new Home_ShangPinBean();
            list.add(bean);
        }
    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("订单中心");
        ImageView back = (ImageView) findViewById(R.id.title_back);
        LinearLayout dizhi = (LinearLayout) findViewById(R.id.tjdd_dizhi);
        back.setOnClickListener(this);
        mlv = (MyListView) findViewById(R.id.tjdd_mlv);
        tjdd = (Button) findViewById(R.id.ddxp_tjdd);
        tjddAdapter = new TJDDAdapter(list, TJDD.this);
        mlv.setAdapter(tjddAdapter);
        tjdd.setOnClickListener(this);
        dizhi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ddxp_tjdd:
                View contentView = LayoutInflater.from(TJDD.this).inflate(R.layout.dialog_zhifu, null);
                LinearLayout wx = (LinearLayout) contentView.findViewById(R.id.zhifu_weixin);
                LinearLayout zhifubao = (LinearLayout) contentView.findViewById(R.id.zhifu_zhifubao);
                LinearLayout kuaiqian = (LinearLayout) contentView.findViewById(R.id.zhifu_kuaiqian);
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setView(contentView);
                dialog.show();
                WindowManager windowManager = getWindowManager();
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Window window = dialog.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.height = (int) (defaultDisplay.getHeight() * 0.12);
                attributes.width = defaultDisplay.getWidth();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.anim_menu_bottombar);
                dialog.getWindow().setAttributes(attributes);
                wx.setOnClickListener(this);
                zhifubao.setOnClickListener(this);
                kuaiqian.setOnClickListener(this);

                break;
            case R.id.zhifu_kuaiqian:
                startActivity(new Intent(TJDD.this,ZhiFu.class));
                break;


            case R.id.zhifu_weixin:
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss ");
//                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//                String str = formatter.format(curDate);
//
//                HashMap<String ,String > map = new HashMap<>();
//                map.put("inputCharset","1");
//                map.put("pageUrl","");
//                map.put("bgUrl","http://www.baidu.com");
//                map.put("version","mobile1.0");
//                map.put("language","1");
//                map.put("signType","4");
//                map.put("merchantAcctId","1020996764101");
//                map.put("payerName","hahah");
//                map.put("payerIdType","3");
//                map.put("payerId","18801105410");
//                map.put("orderId","20171117172953");
//                map.put("orderAmount",""+1000);
//                map.put("orderTime","20171117172953");
//                map.put("payType","21-1");
//
//                String chuan = "inputCharset=1&" +
//                        "bgUrl=http://www.baidu.com&" +
//                        "version=mobile1.0&" +
//                        "language=1&" +
//                        "signType=4&" +
//                        "merchantAcctId=1020996764101&" +
//                        "payerIdType=3&" +
//                        "payerId=18994659500&" +
//                        "orderId=20171117172953&" +
//                        "orderAmount=1000&" +
//                        "orderTime=20171117172953&" +
//                        "payType=21-1";
//                String s = SignUtils3.signMsg(chuan);
//                map.put("singMsg",s);
//                Log.e(TAG, "onClick: "+s );
//                //https://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm?inputCharset=1&pageUrl=http%3A%2F%2Fwww.taogt.cn%2Fuserinfo%2Fwallet_list&bgUrl=http%3A%2F%2Fwww.taogt.cn%2Findex%2Fpay_callback%2F&version=mobile1.0&language=1&signType=4&merchantAcctId=1020996764101&payerName=%E5%85%85%E5%80%BC&payerContactType=2&payerContact=13212345678&payerIdType=3&payerId=66531&orderId=1511419558946&orderAmount=27360&orderTime=20171123144558&productName=%E5%85%85%E5%80%BC&productNum=&productId=&productDesc=%E5%85%85%E5%80%BC&ext1=&ext2=&payType=21-1&bankId=&redoFlag=1&pid=&signMsg=m6gfcLMHcSLPSX%2FuEzTu87AhkAHKPOOd1MSnbc%2FX6OLi4nEfhEoXR%2FFODT6c0VM5Sj3%2FoYeAInKpmI9p14qrUGcg5UzjlC2dCSbubCS4zYbzQvmYPZogMaAjMcTT3%2FE4xOGBKyjPsddylIDj5UumB4vDg0dYR2B2NFfdKD7CmPQ%3D
//                HashMap<String, String> map1 = new HashMap<>();
//                map1.put("signMsg",s);
//                OkUtils.UploadSJ("https://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm?"+chuan, map1, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.e(TAG, "onResponse: "+response.body().string() );
//                    }
//                });


                ToSi.show(TJDD.this, "点击的是微信支付");
                break;

            case R.id.zhifu_zhifubao:
                ToSi.show(TJDD.this,"支付宝支付");
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.tjdd_dizhi:
                startActivity(new Intent(TJDD.this,SHDZGL.class));

                break;
        }
    }
}
