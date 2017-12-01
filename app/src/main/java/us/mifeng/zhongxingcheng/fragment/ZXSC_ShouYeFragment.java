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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

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
import us.mifeng.zhongxingcheng.adapter.ZXSC_ShouYeAdapter;
import us.mifeng.zhongxingcheng.bean.Home_ShangPinBean;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.view.MyListView;

/**
 * Created by shido on 2017/11/15.
 */

/**
 * 中星商城首页fragment
 */
public class ZXSC_ShouYeFragment extends Fragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private static final String TAG = "ZXSC_ShouYeFragment";
    private MyListView listView;
    private View inflate;
    private List<Home_ShangPinBean> list;
    private LinearLayout sxpp;
    private SliderLayout slider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_shouye, null);
        listView = (MyListView) inflate.findViewById(R.id.zxsc_home_list);
        initView();
        initSlider();
        initLianWang();
        return inflate;

    }

    private void initSlider() {
        HashMap<String,String> url_maps = new HashMap<>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        for(String name : url_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            // initialize a SliderLayout
            defaultSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information 点击图片时可用到
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra",name);

            slider.addSlider(defaultSliderView);
        }
        // 设置默认过渡效果(可在xml中设置)
        slider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        // 设置默认指示器位置(默认指示器白色,位置在sliderlayout底部)
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        // 设置TextView自定义动画
        slider.setCustomAnimation(new DescriptionAnimation());
        // 设置持续时间
        slider.setDuration(2000);
        slider.addOnPageChangeListener(this);
    }

    private void initLianWang() {
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
                        Home_ShangPinBean home_shangPinBean = new Home_ShangPinBean();
                        home_shangPinBean.setImgTop(imgTop);
                        home_shangPinBean.setShopName(desc);
                        home_shangPinBean.setImgIcon(imgIcon);
                        list.add(home_shangPinBean);
                    }
                    ZXSC_ShouYeAdapter home_dianPuAdapter = new ZXSC_ShouYeAdapter(list,getActivity());
                    listView.setAdapter(home_dianPuAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private void initView() {
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_home_back);
        sxpp = (LinearLayout) inflate.findViewById(R.id.fragment_zxsc_shouye_sxpp);
        slider = (SliderLayout) inflate.findViewById(R.id.slider);
        sxpp.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_zxsc_shouye_sxpp:
                startActivity(new Intent(getActivity(), ShangXinPinPai.class));
                break;
            case R.id.zxsc_home_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
