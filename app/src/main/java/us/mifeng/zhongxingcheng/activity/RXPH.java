package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.adapter.RXPHAdapter;
import us.mifeng.zhongxingcheng.bean.RXPHBean;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/12/18.
 */

/**
 * 热销排行界面
 */
public class RXPH extends Activity implements AbsListView.OnScrollListener {
    private static final String TAG = "RXPH";
    private ListView lv;
    private List<RXPHBean.DataBean> list;
    private ProgressBar mBar;
    private View inflate;
    private int index = 0;
    private RXPHAdapter rxphAdapter;
    private int lastVisIdnex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxph);
        initView();
        inflate = View.inflate(RXPH.this, R.layout.footerview, null);
        mBar = (ProgressBar) inflate.findViewById(R.id.mBar);
        list = new ArrayList<>();
        lv.addFooterView(inflate);
        initLianWang();
        lv.setOnScrollListener(this);
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",""+index++);
        JSONObject jsonObject = new JSONObject(map);
        String string = jsonObject.toString();
        String s = JiaMi.jdkBase64Encoder(string);
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("secret",s);
        OkUtils.UploadSJ(WangZhi.RXPH, map1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = hand.obtainMessage();
                message.obj=string;
                message.what=100;
                hand.sendMessage(message);

            }
        });
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.rxph_lv);
    }
    Handler hand = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String status = jsonObject.getString("status");
                    if ("0".equals(status)){
                        JSONArray data = jsonObject.getJSONArray("data");
                        String page = jsonObject.getString("page");
                        String page_count = jsonObject.getString("page_count");
//                        if (page.equals(page_count)){
//                            mBar.setVisibility(View.GONE);
//                            ToSi.show(RXPH.this,"没有更多数据了");
//                        }else {
                            for (int i = 0;i<data.length();i++){
                                JSONObject jsonObject1 = data.getJSONObject(i);
                                String id = jsonObject1.getString("id");

                                String shopId = jsonObject1.getString("shopId");

                                String goodsName = jsonObject1.getString("goodsName");

                                String shorDesc = jsonObject1.getString("shortDesc");
                                String retailPrice = jsonObject1.getString("retailPrice");
                                String goodsMoney = jsonObject1.getString("goodsMoney");
                                String goodsMoney1 = jsonObject1.getString("goodsMoney1");
                                String sellCount = jsonObject1.getString("sellCount");
                                String imgCart = jsonObject1.getString("imgCart");
                                String freight = jsonObject1.getString("freight");
                                String imgUrl = jsonObject1.getString("imgUrl");
                                RXPHBean.DataBean dataBean = new RXPHBean.DataBean();
                                dataBean.setFreight(freight);
                                dataBean.setId(id);
                                dataBean.setShopId(shopId);
                                dataBean.setSellCount(sellCount);
                                dataBean.setGoodsName(goodsName);
                                dataBean.setShortDesc(shorDesc);
                                dataBean.setRetailPrice(retailPrice);
                                dataBean.setGoodsMoney(goodsMoney);
                                dataBean.setGoodsMoney1(goodsMoney1);
                                dataBean.setImgCart(imgCart);
                                dataBean.setImgUrl(imgUrl);
                                list.add(dataBean);
                            }
                            if (rxphAdapter==null){
                                rxphAdapter = new RXPHAdapter(list, RXPH.this);
                                lv.setAdapter(rxphAdapter);
                            }else {
                                lv.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        rxphAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
//                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState==SCROLL_STATE_IDLE&&lastVisIdnex==rxphAdapter.getCount()){
            //确认滑倒底部 加载更多
            mBar.setVisibility(View.VISIBLE);
            initLianWang();

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastVisIdnex=firstVisibleItem+visibleItemCount-1;
    }
}
