package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import us.mifeng.zhongxingcheng.adapter.XWAdapter;
import us.mifeng.zhongxingcheng.bean.XWBean;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;


/**
 * Created by shido on 2017/10/17.
 */

public class XinWenFragment extends Fragment implements AbsListView.OnScrollListener {
    private List<XWBean.DataBean.InfoBean> list;
    private View view;
    private int index = 0;
    private XWAdapter xwAdapter;
    private ListView lv;
    private static final String TAG = "XinWenFragment";
    private View inflate;
    private ProgressBar mBar;
    private String page;
    private String pageCound;
    private int lastVisIdnex;
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

        return view;

    }



    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",""+index++);
        OkUtils.UploadSJ(WangZhi.XINWEN, map, new Callback() {
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
                    JSONObject data = jsonObject.getJSONObject("data");
                    pageCound = data.getString("pageCount");
                    page = data.getString("page");
                    if (page.equals(pageCound)){
                        mBar.setVisibility(View.GONE);
                        ToSi.show(getActivity(),"没有更多数据了");
                    }else {
                        JSONArray info = data.getJSONArray("info");
                        Log.e(TAG, "handleMessage: "+info.length() );
                        for (int i = 0;i<info.length();i++){
                            JSONObject jsonObject1 = (JSONObject) info.get(i);
                            String id = jsonObject1.getString("id");
                            String title = jsonObject1.getString("title");
                            String thumb = jsonObject1.getString("thumb");
                            String hits = jsonObject1.getString("hits");
                            String updateTime = jsonObject1.getString("updateTime");
                            String commentNum = jsonObject1.getString("commentNum");
                            String praiseNum = jsonObject1.getString("praiseNum");
                            XWBean.DataBean.InfoBean infoBean = new XWBean.DataBean.InfoBean();
                            infoBean.setHits(hits);
                            infoBean.setId(id);
                            infoBean.setTitle(title);
                            infoBean.setUpdateTime(updateTime);
                            infoBean.setThumb(thumb);
                            infoBean.setCommentNum(commentNum);
                            infoBean.setPraiseNum(praiseNum);
                            list.add(infoBean);

                        }
                        if (xwAdapter==null){
                            xwAdapter = new XWAdapter(getActivity(),list);
                            lv.setAdapter(xwAdapter);
                        }else {
                            lv.post(new Runnable() {
                                @Override
                                public void run() {
                                    xwAdapter.notifyDataSetChanged();
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
        if(scrollState==SCROLL_STATE_IDLE&&lastVisIdnex==xwAdapter.getCount()){
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
}
