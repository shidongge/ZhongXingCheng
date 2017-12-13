package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 *
 * @author shido
 * @author shido
 * @date 2017/11/10
 */

public class ZZC extends Activity implements View.OnClickListener {
    private PieChart mPieChart;
    private ImageView back, img;
    private String zzc;
    private String token;
    private String mobile;
    private static final String TAG = "ZZC";
    private TextView nincheng,shouji,zongzichan,keyong,dongjie,tixian,xiangbi,scky,scbd;
    private String portrait;
    private String cashMoney;
    private String balance;
    private String available;
    private String frozen;
    private String shareholder;
    private String shopAvailable;
    private String shopBindMoney;
    private String nickName;
    private String shopBalance;
    private String shopFrozen;
    private float v5;
    private float v8;
    private String secondMoney;
    private float v11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzc);
        zzc = getIntent().getStringExtra("zzc");
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", ZZC.this);
        mobile = sharedUtils.getShared("mobile", ZZC.this);
        initLianWang();
        initView();
    }

    private void initLianWang() {
        String s = JiaMi.jdkBase64Encoder("portrait,balance,shopBalance,available,frozen,cashMoney,shareholder,shopAvailable,shopBindMoney,nickName,shopFrozen,secondMoney");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("field", s);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: " + response.body().string());
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 100;
                hand.sendMessage(mess);
            }
        });
    }

    //初始化View
    private void initView() {
        img = (ImageView) findViewById(R.id.zzc_img);
        back = (ImageView) findViewById(R.id.zzc_back);
        nincheng = (TextView) findViewById(R.id.zzc_nincheng);
        shouji = (TextView) findViewById(R.id.zzc_shoujihao);
        zongzichan = (TextView) findViewById(R.id.zzc_zzc);
        keyong = (TextView) findViewById(R.id.zzc_keyong);
        dongjie = (TextView) findViewById(R.id.zzc_dongjie);
        tixian = (TextView) findViewById(R.id.zzc_tixian);
        xiangbi = (TextView) findViewById(R.id.zzc_xbje);
        scky = (TextView) findViewById(R.id.zzc_scky);
        scbd = (TextView) findViewById(R.id.zzc_scbd);
        shouji.setText(mobile);
        back.setOnClickListener(this);
        //饼状图
        mPieChart = (PieChart) findViewById(R.id.mPieChart);
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        //饼形图是否显示文字
        mPieChart.setDrawSliceText(false);
        //设置是否铺满
        mPieChart.setDrawHoleEnabled(true);


        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.setTransparentCircleColor(Color.BLACK);
        mPieChart.setTransparentCircleAlpha(1);

        mPieChart.setHoleRadius(40f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(false);
        mPieChart.setHighlightPerTapEnabled(true);






        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);//控制旁边的文字在什么地方显示
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setFormSize(0f);//比例块字体大小
        //设置比例块换行...
        l.setWordWrapEnabled(true);

        l.setXEntrySpace(20f);//设置距离饼图的距离，防止与饼图重合
        l.setYEntrySpace(10f);


        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.BLACK);
        mPieChart.setEntryLabelTextSize(0f);
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#ff6056"));
        colors.add(Color.parseColor("#5a8eff"));
        colors.add(Color.parseColor("#ff8a00"));
        colors.add(Color.parseColor("#3bc62e"));
        colors.add(Color.parseColor("#886cff"));
        colors.add(Color.parseColor("#1fc4e7"));

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        //设置饼形图半分比字体大小
        data.setValueTextSize(11f);
        //设置饼形图半分比字体颜色
        data.setValueTextColor(Color.BLACK);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);

        //刷新
        mPieChart.invalidate();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zzc_back:
                finish();
                break;
            default:
                break;
        }
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject userInfo = data.getJSONObject("userInfo");
                    Log.e(TAG, "handleMessage: "+userInfo );
                    portrait = userInfo.getString("portrait");
                    balance = userInfo.getString("balance");
                    available = userInfo.getString("available");
                    frozen = userInfo.getString("frozen");
                    cashMoney = userInfo.getString("cashMoney");
                    shareholder = userInfo.getString("shareholder");
                    shopAvailable = userInfo.getString("shopAvailable");
                    shopBindMoney = userInfo.getString("shopBindMoney");
                    nickName = userInfo.getString("nickName");
                    shopBalance = userInfo.getString("shopBalance");
                    shopFrozen = userInfo.getString("shopFrozen");
                    secondMoney = userInfo.getString("secondMoney");
                    float v = Float.parseFloat(balance);
                    float v1 = Float.parseFloat(shopBalance);
                    float v2 = v + v1;
                    zongzichan.setText(v2+"");
                    float v3 = Float.parseFloat(available);
                    float v4 = Float.parseFloat(shopAvailable);
                    v5 = v3 + v4;
                    keyong.setText(v5 +"");

                    float v6 = Float.parseFloat(shopFrozen);
                    float v7 = Float.parseFloat(frozen);
                    v8 = v6 + v7;
                    dongjie.setText(v8 +"");
                    xiangbi.setText(shareholder);
                    float v9 = Float.parseFloat(cashMoney);
                    float v10 = Float.parseFloat(secondMoney);
                    v11 = v9 + v10;
                    tixian.setText(v11 +"");
                    scky.setText(shopAvailable);
                    scbd.setText(shopBindMoney);
                    if ("".equals(nickName)){
                        nincheng.setText("未设置");
                    }else {
                        nincheng.setText(nickName);
                    }
                    if ("".equals(portrait)){
                        img.setImageResource(R.mipmap.tx);
                    }else {

                        Glide.with(ZZC.this).load(WangZhi.TUPIAN + zzc).apply(bitmapTransform(new CropCircleTransformation())).into(img);
                    }
                    shezhi();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public void shezhi(){
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        float v3 = Float.parseFloat(cashMoney);
        float v4 = Float.parseFloat(shareholder);
        float v512 = Float.parseFloat(shopAvailable);
        float v6 = Float.parseFloat(shopBindMoney);
        entries.add(new PieEntry(v5, ""));
        entries.add(new PieEntry(v8, ""));
        entries.add(new PieEntry(v11, ""));
        entries.add(new PieEntry(v4, ""));
        entries.add(new PieEntry(v512, ""));
        entries.add(new PieEntry(v6, ""));
        //设置数据
        setData(entries);

    }


}
