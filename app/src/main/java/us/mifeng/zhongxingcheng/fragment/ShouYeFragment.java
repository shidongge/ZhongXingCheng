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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
import us.mifeng.zhongxingcheng.activity.HYZX;
import us.mifeng.zhongxingcheng.activity.QianDao;
import us.mifeng.zhongxingcheng.activity.SPZX;
import us.mifeng.zhongxingcheng.activity.XWZX;
import us.mifeng.zhongxingcheng.activity.ZXSC_Android;
import us.mifeng.zhongxingcheng.adapter.Home_DianPingAdapter;
import us.mifeng.zhongxingcheng.bean.Home_ShangPingCGBean;
import us.mifeng.zhongxingcheng.bean.Home_ShangPuBean;
import us.mifeng.zhongxingcheng.dianpu.DianPuActivity;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.view.MyGridView;
import us.mifeng.zhongxingcheng.wpxq_ceshi.WPXQ_CeSi;

/**
 * Created by shido on 2017/10/17.
 */

public class ShouYeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private int index = 0;
    private static final String TAG = "ShouYeFragment";
    private View inflate;
    private LinearLayout mrqd, ltzx, kthy, czzx, zxsc, xwzx, spzx, sxy;
    private List<Home_ShangPingCGBean.DataBean.GoodsInfoBean> list;
    private MyGridView mgv;
    private TextView gengduo;
    private ImageView yihao, erhao, sanhao, sihao;
    private List<Home_ShangPuBean.DataBean.ShopsInfoBean> shangpu_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = View.inflate(getActivity(), R.layout.fragment_shouye, null);

        initView();
        initSiGe();
        initMyGV();
        panduanshifouxiaofeiguo();
        return inflate;
    }

    private void panduanshifouxiaofeiguo() {

    }

    private void initSiGe() {
        HashMap<String, String> map = new HashMap<>();
        OkUtils.UploadSJ(WangZhi.SHOUYESIGE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 100;
                hand.sendMessage(mess);

            }
        });
    }

    private void initMyGV() {
        HashMap<String, String> map = new HashMap<>();
        OkUtils.UploadSJ(WangZhi.SHOUYEJIUGE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 200;
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
        yihao = (ImageView) inflate.findViewById(R.id.shouye_yihao);
        erhao = (ImageView) inflate.findViewById(R.id.shouye_erhao);
        sanhao = (ImageView) inflate.findViewById(R.id.shouye_sanhao);
        sihao = (ImageView) inflate.findViewById(R.id.shouye_sihao);


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
        yihao.setOnClickListener(this);
        erhao.setOnClickListener(this);
        sanhao.setOnClickListener(this);
        sihao.setOnClickListener(this);


    }


    //首页点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //商品中心
            case R.id.shouye_spzx:
                Intent intent = new Intent(getActivity(), SPZX.class);

                startActivity(intent);
                break;
            //开通会员
            case R.id.shouye_kthy:
                startActivity(new Intent(getActivity(), HYZX.class));
                break;
            //充值中心
            case R.id.shouye_czzx:
                startActivity(new Intent(getActivity(), CZZX.class));
                break;
            //每日签到
            case R.id.shouye_mrqd:
                startActivity(new Intent(getActivity(), QianDao.class));
                break;
            //中星商成
            case R.id.shouye_zxsc:
                startActivity(new Intent(getActivity(), ZXSC_Android.class));
                break;
            //新闻中心
            case R.id.shouye_xwzx:
                startActivity(new Intent(getActivity(), XWZX.class));
                break;
            //论坛中心
            case R.id.shouye_ltzx:
                break;
            //商学院
            case R.id.shouye_sxy:
                break;
            //更多
            case R.id.shouye_gengduo:
                startActivity(new Intent(getActivity(),ZXSC_Android.class));

                break;
            case R.id.shouye_yihao:
                String id0 = shangpu_list.get(0).getId();
                Intent intent1 = new Intent(getActivity(), DianPuActivity.class);
                intent1.putExtra("dianpu",id0);
                startActivity(intent1);

                break;
            case R.id.shouye_erhao:
                String id1 = shangpu_list.get(1).getId();
                Intent intent2 = new Intent(getActivity(), DianPuActivity.class);
                intent2.putExtra("dianpu",id1);
                startActivity(intent2);

                break;
            case R.id.shouye_sanhao:
                String id2 = shangpu_list.get(2).getId();
                Intent intent3 = new Intent(getActivity(), DianPuActivity.class);
                intent3.putExtra("dianpu",id2);
                startActivity(intent3);

                break;
            case R.id.shouye_sihao:
                String id3 = shangpu_list.get(3).getId();
                Intent intent4 = new Intent(getActivity(), DianPuActivity.class);
                intent4.putExtra("dianpu",id3);
                startActivity(intent4);

                break;
            default:
                break;
        }
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            //四个店铺返回值
            if (msg.what == 100) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String success = jsonObject.getString("success");
                    if (success.equals("true")) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray shopsInfo = data.getJSONArray("shopsInfo");
                        shangpu_list = new ArrayList<>();
                        for (int i = 0; i < shopsInfo.length(); i++) {
                            JSONObject jsonObject1 = shopsInfo.getJSONObject(i);
                            String imgTop = jsonObject1.getString("imgTop");
                            String id = jsonObject1.getString("id");
                            String shopname = jsonObject1.getString("shopName");
                            Home_ShangPuBean.DataBean.ShopsInfoBean dataBean = new Home_ShangPuBean.DataBean.ShopsInfoBean();
                            dataBean.setId(id);
                            shangpu_list.add(dataBean);
                            if (i == 0) {
                                if ("".equals(imgTop)){
                                }else {

                                    Glide.with(getActivity()).load(WangZhi.DIANPU+imgTop).into(yihao);
                                }
                            } else if (i == 1) {


                                if ("".equals(imgTop)){

                                }else {
                                    Glide.with(getActivity()).load(WangZhi.DIANPU+imgTop).into(erhao);
                                }
                            } else if (i == 2) {
                                if ("".equals(imgTop)){

                                }else {

                                    Glide.with(getActivity()).load(WangZhi.DIANPU+imgTop).into(sanhao);
                                }
                            } else if (i == 3) {
                                if ("".equals(imgTop)){

                                }else {

                                    Glide.with(getActivity()).load(WangZhi.DIANPU+imgTop).into(sihao);

                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //九个商品的联网操作
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray msg1 = data.getJSONArray("goodsInfo");
                    list = new ArrayList<>();
                    for (int i = 0; i < msg1.length(); i++) {
                        JSONObject jsonObject1 = msg1.getJSONObject(i);
//                        String desc = jsonObject1.getString("shopName");
                        String id = jsonObject1.getString("id");
                        String goodsMoney1 = jsonObject1.getString("goodsMoney1");//会员价
                        String goodsMoney_old = jsonObject1.getString("goodsMoney_old");//原价
                        String shortDesc = jsonObject1.getString("shortDesc");//商品名称

                        String imgTop = jsonObject1.getString("imgCart");
                        String goodsMoney = jsonObject1.getString("goodsMoney");
                        Home_ShangPingCGBean.DataBean.GoodsInfoBean home_shangPinBean = new Home_ShangPingCGBean.DataBean.GoodsInfoBean();
                        home_shangPinBean.setGoodsMoney1(goodsMoney1);
                        home_shangPinBean.setShortDesc(shortDesc);
                        home_shangPinBean.setImgCart(imgTop);
                        home_shangPinBean.setId(id);
                        list.add(home_shangPinBean);
                    }
                    Home_DianPingAdapter home_dianPingAdapter = new Home_DianPingAdapter(list, getActivity());
                    mgv.setAdapter(home_dianPingAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Home_ShangPingCGBean.DataBean.GoodsInfoBean bean = (Home_ShangPingCGBean.DataBean.GoodsInfoBean) parent.getAdapter().getItem(position);
        String id1 = bean.getId();

        Intent intent = new Intent(getActivity(), WPXQ_CeSi.class);
        intent.putExtra("id", id1);
        startActivity(intent);

    }
}
