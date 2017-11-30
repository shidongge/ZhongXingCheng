package us.mifeng.zhongxingcheng.dianpu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;

public class DianPuActivity extends FragmentActivity implements View.OnClickListener {
    private String[] mTitles = new String[]{"全部", "销量", "价格", "新品", "筛选"};
    private RecyclerView youhuiquan;
    private LinearLayout mIndicator;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<String>();
    private String mTitle = "hahah";
    private TextView yihao, erhao, sanhao, sihao, biaoti;
    private DianPuAdapter dianPuAdapter;
    private ImageView back;
    private static final String TAG = "DianPuActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_pu);
        //；粘性布局
        initViews();
    }

    private void initViews() {
        yihao = (TextView) findViewById(R.id.dp_yihao);
        erhao = (TextView) findViewById(R.id.dp_erhao);
        sanhao = (TextView) findViewById(R.id.dp_sanhao);
        sihao = (TextView) findViewById(R.id.dp_sihao);
        back = (ImageView) findViewById(R.id.title_back);
        biaoti = (TextView) findViewById(R.id.title_text);

        youhuiquan = (RecyclerView) findViewById(R.id.dianpu_youhuiquan);
        mIndicator = (LinearLayout) findViewById(R.id.id_stickynavlayout_indicator);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_stickynavlayout_vp);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        for (int i = 0; i < 51; i++) {
            mDatas.add(mTitle + " -> " + i);
        }
        dianPuAdapter = new DianPuAdapter(DianPuActivity.this, mDatas);
        mRecyclerView.setAdapter(dianPuAdapter); dianPuAdapter.setOnItemClickListener(new DianPuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e(TAG, "onItemClick: 000000"+"点击了哈哈哈" );
            }
        });

        biaoti.setText("商家店铺");
        back.setOnClickListener(this);
        yihao.setOnClickListener(this);
        erhao.setOnClickListener(this);
        sanhao.setOnClickListener(this);
        sihao.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dp_yihao:
                dianPuAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(dianPuAdapter);
                dianPuAdapter.setOnItemClickListener(new DianPuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e(TAG, "onItemClick: 000"+"点击了哈哈哈" );
                    }
                });
                break;
            case R.id.dp_erhao:
                dianPuAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(dianPuAdapter);
                dianPuAdapter.setOnItemClickListener(new DianPuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e(TAG, "onItemClick: 111111"+"点击了哈哈哈" );
                    }
                });
                break;
            case R.id.dp_sanhao:
                dianPuAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(dianPuAdapter);
                dianPuAdapter.setOnItemClickListener(new DianPuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e(TAG, "onItemClick: 222222"+"点击了哈哈哈" );
                    }
                });
                break;
            case R.id.dp_sihao:
                dianPuAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(dianPuAdapter);
                dianPuAdapter.setOnItemClickListener(new DianPuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e(TAG, "onItemClick: 333333"+"点击了哈哈哈" );
                    }
                });
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
