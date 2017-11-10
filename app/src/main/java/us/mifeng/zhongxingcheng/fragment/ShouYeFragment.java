package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
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
import android.widget.LinearLayout;
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
import us.mifeng.zhongxingcheng.activity.CZZX;
import us.mifeng.zhongxingcheng.activity.KTHY;
import us.mifeng.zhongxingcheng.activity.QianDao;
import us.mifeng.zhongxingcheng.activity.XWZX;
import us.mifeng.zhongxingcheng.activity.ZXSC;
import us.mifeng.zhongxingcheng.adapter.Home_DianPuAdapter;
import us.mifeng.zhongxingcheng.bean.Home_ShangPuBean;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.view.MyGridView;


/**
 * Created by shido on 2017/10/17.
 */

public class ShouYeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private int index=0;
    private static final String TAG = "ShouYeFragment";
    private View inflate;
    private LinearLayout mrqd,ltzx,kthy,czzx,zxsc,xwzx,spzx,sxy;
    private List<Home_ShangPuBean> list;
    private MyGridView mgv;
    private TextView gengduo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = View.inflate(getActivity(), R.layout.fragment_shouye, null);
        initView();
        initMyGV();
        return inflate;
    }

    private void initMyGV() {
        HashMap<String,String> map = new HashMap<>();
        OkUtils.UploadSJ(WangZhi.HOME_DIANPU, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj=string;
                mess.what=200;
                hand.sendMessage(mess);
            }
        });
    }

    //    初始化布局
    private void initView() {
        gengduo = (TextView) inflate.findViewById(R.id.shouye_gengduo);
        mrqd = (LinearLayout) inflate.findViewById(R.id.shouye_mrqd);
        ltzx = (LinearLayout) inflate.findViewById(R.id.shouye_ltzx);
        spzx = (LinearLayout) inflate.findViewById(R.id.shouye_spzx);
        sxy = (LinearLayout) inflate.findViewById(R.id.shouye_sxy);
        kthy = (LinearLayout) inflate.findViewById(R.id.shouye_kthy);
        czzx = (LinearLayout) inflate.findViewById(R.id.shouye_czzx);
        xwzx = (LinearLayout) inflate.findViewById(R.id.shouye_xwzx);
        zxsc = (LinearLayout) inflate.findViewById(R.id.shouye_zxsc);
        mgv = (MyGridView) inflate.findViewById(R.id.shouye_mGv);
        mrqd.setOnClickListener(this);
        mgv.setOnItemClickListener(this);
        kthy.setOnClickListener(this);
        czzx.setOnClickListener(this);
        xwzx.setOnClickListener(this);
        zxsc.setOnClickListener(this);
        ltzx.setOnClickListener(this);
        spzx.setOnClickListener(this);
        sxy.setOnClickListener(this);
        gengduo.setOnClickListener(this);
    }


    //首页点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //商品中心
            case R.id.shouye_spzx:
//                startActivity(new Intent(getActivity(), YuShou.class));
                break;
            //开通会员
            case R.id.shouye_kthy:
                startActivity(new Intent(getActivity(),KTHY.class));
                break;
            //充值中心
            case R.id.shouye_czzx:
                startActivity(new Intent(getActivity(),CZZX.class));
                break;
            //每日签到
            case R.id.shouye_mrqd:
                startActivity(new Intent(getActivity(), QianDao.class));
                break;
            //中星商成
            case R.id.shouye_zxsc:
                startActivity(new Intent(getActivity(), ZXSC.class));
                break;
            //新闻中心
            case R.id.shouye_xwzx:
                startActivity(new Intent(getActivity(),XWZX.class));
                break;
            //论坛中心
            case R.id.shouye_ltzx:
                break;
            //商学院
            case R.id.shouye_sxy:
                break;
            //更多
            case R.id.shouye_gengduo:
                break;

        }
    }

    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray msg1 = data.getJSONArray("msg");
                    list = new ArrayList<>();
                    for (int i =0;i<msg1.length();i++){
                        JSONObject jsonObject1 = msg1.getJSONObject(i);
                        String desc = jsonObject1.getString("shopName");
                        String imgTop = jsonObject1.getString("imgTop");
                        String imgIcon = jsonObject1.getString("imgIcon");
                        Home_ShangPuBean home_shangPuBean = new Home_ShangPuBean();
                        home_shangPuBean.setImgTop(imgTop);
                        home_shangPuBean.setShopName(desc);
                        home_shangPuBean.setImgIcon(imgIcon);
                        list.add(home_shangPuBean);
                    }
                    Home_DianPuAdapter home_dianPuAdapter = new Home_DianPuAdapter(list,getActivity());
                    mgv.setAdapter(home_dianPuAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Home_ShangPuBean bean = (Home_ShangPuBean) parent.getAdapter().getItem(position);
    }
}
