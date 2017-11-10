package us.mifeng.zhongxingcheng.liaotian.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * Created by user on 2017/10/30.
 */

public class LoadMoreFooterView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    ProgressBar bar;
    private TextView tv;
    private static final String TAG = "LoadMoreFooterView";

    public LoadMoreFooterView(Context context) {
        super(context);
        //initView();
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //initView();
    }

    private void initView() {
        bar = new ProgressBar(context);
        tv = new TextView(context);
        bar.setVisibility(GONE);
    }

    @Override
    public void onLoadMore() {
        //加载
        //bar.setVisibility(VISIBLE);
        //tv.setVisibility(GONE);
        setText("加载...");
    }

    @Override
    public void onPrepare() {
        //setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                //释放立即加载
                //tv.setVisibility(GONE);
                //bar.setVisibility(VISIBLE);
                setText("释放加载");
            } else {
                //上拉加载
                setText("上拉加载");
            }
        } else {
            //加载完成
            setText("加载成功");
        }
    }

    @Override
    public void onRelease() {
        //setText("LOADING MORE");
    }

    @Override
    public void onComplete() {
        //setText("COMPLETE");
    }

    @Override
    public void onReset() {
        //setText("");
    }

}
