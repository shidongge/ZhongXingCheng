package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import us.mifeng.zhongxingcheng.activity.ShangXinPinPai;
import us.mifeng.zhongxingcheng.adapter.FenLeiRightAdapter;
import us.mifeng.zhongxingcheng.bean.FenLieBean;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/16.
 */

public class ZXSC_FenLeiFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ZXSC_FenLeiFragment";
    private TextView sousuo;
    private View inflate;
    private ListView mLv;
    private GridView mGv;
    private List<FenLieBean.DataBean> dataBeanList;
    private List<FenLieBean.DataBean.ChildListBean> childListBeanList;
    private FenLeiRightAdapter fenLeiRightAdapter;
    private FenLeiLeftAdapter fenLeiLeftAdapter;
    private JSONArray data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_fenlei, null);
        initView();
        dataBeanList = new ArrayList<>();
        childListBeanList = new ArrayList<>();
        initLianWang();
        setListView();
        return inflate;
    }

    private void setListView() {
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                childListBeanList.clear();
                fenLeiLeftAdapter.setSelectedPosition(position);
                fenLeiLeftAdapter.notifyDataSetChanged();
                try {
                    JSONObject jsonObject = data.getJSONObject(position);
                    JSONArray child_list = jsonObject.getJSONArray("child_list");
                    for (int i = 0;i<child_list.length();i++){
                        JSONObject jsonObject1 = child_list.getJSONObject(i);
                        String id1 = jsonObject1.getString("id");
                        String name = jsonObject1.getString("name");
                        String level = jsonObject1.getString("level");
                        FenLieBean.DataBean.ChildListBean childListBean = new FenLieBean.DataBean.ChildListBean();
                        childListBean.setLevel(level);
                        childListBean.setId(id1);
                        childListBean.setName(name);
                        childListBeanList.add(childListBean);
                    }
                    if (fenLeiRightAdapter==null){
                        fenLeiRightAdapter = new FenLeiRightAdapter(getActivity(),childListBeanList);
                        mGv.setAdapter(fenLeiRightAdapter);
                    }else {
                        fenLeiRightAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FenLieBean.DataBean.ChildListBean childListBean = (FenLieBean.DataBean.ChildListBean) parent.getAdapter().getItem(position);
                ToSi.show(getActivity(),childListBean.getName());//点击右边的item
            }
        });
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();

        OkUtils.UploadSJ(WangZhi.SPFL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: "+response.body().string() );
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 100;
                hand.sendMessage(mess);
            }
        });
    }

    private void initView() {
        sousuo = (TextView) inflate.findViewById(R.id.zxsc_fenlei_sousuo);
        mLv = (ListView) inflate.findViewById(R.id.zxsc_fenlei_mLv);
        mGv = (GridView) inflate.findViewById(R.id.zxsc_fenlei__mGv);
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_fenlei_back);
        back.setOnClickListener(this);
        sousuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxsc_fenlei_sousuo:
                startActivity(new Intent(getActivity(), ShangXinPinPai.class));
                break;
            case R.id.zxsc_home_back:
                getActivity().finish();
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
                    String status = jsonObject.getString("status");
                    if ("0".equals(status)) {
                        data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject1 = data.getJSONObject(i);
                            String id = jsonObject1.getString("id");

                            String level = jsonObject1.getString("level");

                            String name = jsonObject1.getString("name");

                            FenLieBean.DataBean dataBean = new FenLieBean.DataBean();
                            dataBean.setId(id);
                            dataBean.setLevel(level);
                            dataBean.setName(name);
                            dataBeanList.add(dataBean);
                        }
                        JSONObject jsonObject1 = data.getJSONObject(0);
                        JSONArray child_list = jsonObject1.getJSONArray("child_list");
                        for (int j = 0;j<child_list.length();j++){
                            FenLieBean.DataBean.ChildListBean childListBean = new FenLieBean.DataBean.ChildListBean();
                            JSONObject jsonObject2 = child_list.getJSONObject(j);
                            String id = jsonObject2.getString("id");
                            String level = jsonObject2.getString("level");
                            String name = jsonObject2.getString("name");
                            childListBean.setId(id);
                            childListBean.setLevel(level);
                            childListBean.setName(name);
                            childListBeanList.add(childListBean);
                        }
                        if (fenLeiRightAdapter==null){
                            fenLeiRightAdapter = new FenLeiRightAdapter(getActivity(),childListBeanList);
                            mGv.setAdapter(fenLeiRightAdapter);
                        }else {
                            fenLeiRightAdapter.notifyDataSetChanged();
                        }
                    }
                    if (fenLeiLeftAdapter==null){
                        fenLeiLeftAdapter = new FenLeiLeftAdapter(dataBeanList);
                        mLv.setAdapter(fenLeiLeftAdapter);
                    }else {
                        fenLeiLeftAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    class FenLeiLeftAdapter extends BaseAdapter{
        private List<FenLieBean.DataBean> dataBeanList2;
        private int position2 =0;
        public FenLeiLeftAdapter(List<FenLieBean.DataBean> dataBeanList2){
            this.dataBeanList2 = dataBeanList2;
        }
        @Override
        public int getCount() {
            if (dataBeanList2.size()!=0){
                return dataBeanList2.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return dataBeanList2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setSelectedPosition(int position) {
            this.position2 = position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHorder vh;
            if (convertView==null){
                vh = new MyViewHorder();
                convertView = View.inflate(getActivity(),R.layout.item_fenleileft,null);
                vh.name = (TextView) convertView.findViewById(R.id.lv_left_mTv);
                convertView.setTag(vh);
            }else {
                vh = (MyViewHorder) convertView.getTag();
            }
            vh.name.setText(dataBeanList2.get(position).getName());
            if (position==position2){
                vh.name.setBackgroundColor(Color.WHITE);
            }else {
                vh.name.setBackgroundColor(0xEEEEEE);
            }
            return convertView;
        }
        class MyViewHorder{
            TextView name;
        }
    }
}
