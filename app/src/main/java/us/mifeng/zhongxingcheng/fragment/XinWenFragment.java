package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
import us.mifeng.zhongxingcheng.activity.XinWen_NeiRong;
import us.mifeng.zhongxingcheng.adapter.GWGGAdapter;
import us.mifeng.zhongxingcheng.bean.GWGGBean;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;


/**
 * Created by shido on 2017/10/17.
 */

public class XinWenFragment extends Fragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private List<GWGGBean.DataBean> list;
    private View view;
    private int index = 0;
    private GWGGAdapter gwggAdapter;
    private ListView lv;
    private static final String TAG = "XinWenFragment";
    private View inflate;
    private ProgressBar mBar;
    private String page;
    private String pageCound;
    private int lastVisIdnex;
    private String token,zxcid,substring;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_xinwen, null);
        lv = (ListView) view.findViewById(R.id.xinwen_lv);
        inflate = View.inflate(getActivity(), R.layout.footerview, null);
        mBar = (ProgressBar) inflate.findViewById(R.id.mBar);
        list = new ArrayList<>();
        lv.addFooterView(inflate);
        initLianWang();
        lv.setOnScrollListener(this);
        lv.setOnItemClickListener(this);
        SharedUtils sharedUtils = new SharedUtils();
        String id = sharedUtils.getShared("id", getActivity());
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", getActivity());
        zxcid = sharedUtils.getShared("zxcid", getActivity());
        return view;

    }



    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",""+index++);
        map.put("user_id",zxcid);
        map.put("user_token",token);
        map.put("user_mobile",substring);
        JSONObject jsonObject = new JSONObject(map);
        String string = jsonObject.toString();
        String s = JiaMi.jdkBase64Encoder(string);
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("secret",s);
        OkUtils.UploadSJ(WangZhi.GWGG, map1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: "+response.body().string() );
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj=string;
                mess.what=100;
                hand.sendMessage(mess);
            }
        });
    }

    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);

                    pageCound = jsonObject.getString("page_count");
                    page = jsonObject.getString("page");
                    if (page.equals(pageCound)){
                        mBar.setVisibility(View.GONE);
                        ToSi.show(getActivity(),"没有更多数据了");
                    }else {
                        JSONArray info = jsonObject.getJSONArray("data");
                        Log.e(TAG, "handleMessage: "+info.length() );
                        for (int i = 0;i<info.length();i++){
                            JSONObject jsonObject1 = (JSONObject) info.get(i);
                            String id = jsonObject1.getString("id");
                            String title = jsonObject1.getString("title");
                            String publisher = jsonObject1.getString("publisher");
                            String read = jsonObject1.getString("read");
                            String thumnai = jsonObject1.getString("thumbnail");
                            String content = jsonObject1.getString("content");
                            String url = jsonObject1.getString("url");
                            GWGGBean.DataBean infoBean = new GWGGBean.DataBean();
                            infoBean.setContent(content);
                            infoBean.setTitle(title);
                            infoBean.setId(id);
                            infoBean.setPublisher(publisher);
                            infoBean.setRead(read);
                            infoBean.setThumbnail(thumnai);
                            infoBean.setUrl(url);
                            list.add(infoBean);

                        }
                        if (gwggAdapter==null){
                            gwggAdapter = new GWGGAdapter(getActivity(),list);
                            lv.setAdapter(gwggAdapter);
                        }else {
                            lv.post(new Runnable() {
                                @Override
                                public void run() {
                                    gwggAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState==SCROLL_STATE_IDLE&&lastVisIdnex==gwggAdapter.getCount()){
            //确认滑倒底部 加载更多
            mBar.setVisibility(View.VISIBLE);
            initLianWang();

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastVisIdnex=firstVisibleItem+visibleItemCount-1;
//        if (page.equals(pageCound)){
//            lv.removeFooterView(inflate);
//            ToSi.show(getActivity(),"没有更多数据了");
//        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GWGGBean.DataBean bean = (GWGGBean.DataBean) parent.getAdapter().getItem(position);
        String id1 = bean.getId();
        String url = bean.getUrl();
        Intent intent = new Intent(getActivity(), XinWen_NeiRong.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
