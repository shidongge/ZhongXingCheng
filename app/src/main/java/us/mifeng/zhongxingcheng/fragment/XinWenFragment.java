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
import android.widget.AdapterView;
import android.widget.ListView;

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
import us.mifeng.zhongxingcheng.adapter.XWAdapter;
import us.mifeng.zhongxingcheng.bean.XWBean;
import us.mifeng.zhongxingcheng.utils.OkUtils;

/**
 * Created by shido on 2017/10/17.
 */

public class XinWenFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<XWBean> list;
    private View view;
    private int index = 0;
    private XWAdapter xwAdapter;
    private ListView lv;
    private static final String TAG = "XinWenFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_xinwen, null);
        lv = (ListView) view.findViewById(R.id.xinwen_lv);
        initLianWang();
        lv.setOnItemClickListener(this);
        return view;

    }

    private void initLianWang() {
        list = new ArrayList<>();
        index++;
        HashMap<String,String> map = new HashMap<>();
        map.put("key","b77f82f109f35ed582a6596f93e27df9");
        map.put("page",index+"");
        map.put("pagesize","10");
        map.put("sort","asc");
        map.put("time","1418816972");
        OkUtils.UploadSJ("https://japi.juhe.cn/joke/content/list.from", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: "+response.body().string());
//                String string = response.body().string();
//                Message message = hand.obtainMessage();
//                message.obj=string;
//                message.what=200;
//                hand.sendMessage(message);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        parent.getAdapter().getItem(position);
        startActivity(new Intent(getActivity(), XinWen_NeiRong.class));
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        JSONObject jsonObject1 = data.getJSONObject(i);
                        XWBean xwBean = new XWBean();
                        String content = jsonObject1.getString("content");
                        String updatetime = jsonObject1.getString("updatetime");
                        xwBean.setNeirong(content);
                        xwBean.setTime(updatetime);
                        list.add(xwBean);
                    }
                    xwAdapter = new XWAdapter(getActivity(), list);
                    lv.setAdapter(xwAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
