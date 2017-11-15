package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/10.
 */

public class ZZC extends Activity implements View.OnClickListener {
    private PieChart mPieChart;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzc);

        initView();
        TongMing();
    }
    //初始化View
    private void initView() {

        back = (ImageView) findViewById(R.id.zzc_back);
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

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(1);

        mPieChart.setHoleRadius(40f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(false);
        mPieChart.setHighlightPerTapEnabled(true);



        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(14, ""));
        entries.add(new PieEntry(40, ""));
        entries.add(new PieEntry(20000, ""));
        entries.add(new PieEntry(90, ""));
        entries.add(new PieEntry(66, ""));
        entries.add(new PieEntry(66, ""));

        //设置数据
        setData(entries);

       // mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);  //控制旁边的文字在什么地方显示
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
        switch (v.getId()){
            case R.id.zzc_back:
                finish();
                break;
        }
    }
    //设置状态栏
    public void TongMing(){
        //如果手机有虚拟按键 那么不能添加透明状态栏
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //   tintManager.setStatusBarTintResource(R.color.zhuangtailan);
        tintManager.setTintColor(Color.parseColor("#000000"));

    }
}
