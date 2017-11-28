package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.ChaXunDD;
import us.mifeng.zhongxingcheng.activity.SHDZGL;
import us.mifeng.zhongxingcheng.activity.ShouCang;
import us.mifeng.zhongxingcheng.activity.ZJZP_WanShan;
import us.mifeng.zhongxingcheng.activity.ZXSC_YouHuiQuan;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by shido on 2017/11/15.
 */

public class ZXSC_WoDeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ZXSC_WoDeFragment";
    private String token;
    private View inflate;
    private TextView nincheng, shoujihao;
    private ImageView img, back;
    private LinearLayout zongdingdan, daifahuo, daishouhuo, daipingjia,
            shouhou, youhuiquan, shoucang, daizhifu,dingdan_shdz,shiminrenzheng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", getActivity());
        inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_wode, null);
        TongMing();
        nincheng = (TextView) inflate.findViewById(R.id.zxsc_wode_nincheng);
        shoujihao = (TextView) inflate.findViewById(R.id.zxsc_wode_shoujihao);
        img = (ImageView) inflate.findViewById(R.id.zxsc_wode_img);
        back = (ImageView) inflate.findViewById(R.id.zxscwode_back);
        zongdingdan = (LinearLayout) inflate.findViewById(R.id.MyDingDan);
        daifahuo = (LinearLayout) inflate.findViewById(R.id.dingdan_daifahuo);
        daishouhuo = (LinearLayout) inflate.findViewById(R.id.dingdan_daishouhuo);
        shouhou = (LinearLayout) inflate.findViewById(R.id.dingdan_shouhou);
        daipingjia = (LinearLayout) inflate.findViewById(R.id.dingdan_daipingjia);
        youhuiquan = (LinearLayout) inflate.findViewById(R.id.zxsc_wode_youhuiquan);
        shoucang = (LinearLayout) inflate.findViewById(R.id.zxsc_wode_shoucang);
        daizhifu = (LinearLayout) inflate.findViewById(R.id.dingdan_daizhifu);
        dingdan_shdz = (LinearLayout) inflate.findViewById(R.id.dingdan_shdz);
        shiminrenzheng = (LinearLayout) inflate.findViewById(R.id.zxsc_wode_smrz);
        zongdingdan.setOnClickListener(this);
        daizhifu.setOnClickListener(this);
        daishouhuo.setOnClickListener(this);
        daifahuo.setOnClickListener(this);
        daipingjia.setOnClickListener(this);
        shouhou.setOnClickListener(this);
        youhuiquan.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        back.setOnClickListener(this);
        dingdan_shdz.setOnClickListener(this);
        shiminrenzheng.setOnClickListener(this);
        initView();
        return inflate;
    }

    private void initView() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        OkUtils.UploadSJ(WangZhi.WODE, map, new Callback() {
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

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String string = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("msg");
                    String nickName = msg1.getString("nickName");
                    String mobile = msg1.getString("mobile");
                    String portrait = msg1.getString("portrait");
                    shoujihao.setText(mobile);
                    nincheng.setText(nickName);
                    Glide.with(getActivity()).load(WangZhi.TUPIAN + portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyDingDan:
                Intent intent = new Intent(getActivity(), ChaXunDD.class);
                intent.putExtra("intent", "0");
                startActivity(intent);
                break;
            case R.id.dingdan_daizhifu:
                Intent intent2 = new Intent(getActivity(), ChaXunDD.class);
                intent2.putExtra("intent", "1");
                startActivity(intent2);
                break;


            case R.id.dingdan_daifahuo:
                Intent intent3 = new Intent(getActivity(), ChaXunDD.class);
                intent3.putExtra("intent", "2");
                startActivity(intent3);
                break;
            case R.id.dingdan_daishouhuo:
                Intent intent4 = new Intent(getActivity(), ChaXunDD.class);
                intent4.putExtra("intent", "3");
                startActivity(intent4);
                break;
            case R.id.dingdan_daipingjia:
                Intent intent5 = new Intent(getActivity(), ChaXunDD.class);
                intent5.putExtra("intent", "4");
                startActivity(intent5);
                break;


            case R.id.zxsc_wode_youhuiquan:
                startActivity(new Intent(getActivity(), ZXSC_YouHuiQuan.class));

                break;
            case R.id.zxsc_wode_shoucang:
                startActivity(new Intent(getActivity(), ShouCang.class));
                break;
            case R.id.zxscwode_back:
                getActivity().finish();
                break;
            case R.id.dingdan_shdz:
                startActivity(new Intent(getActivity(), SHDZGL.class));
                break;
            case R.id.zxsc_wode_smrz:
                startActivity(new Intent(getActivity(), ZJZP_WanShan.class));
                break;

//            case R.id.dingdan_shouhou:
//                //跳转待售后界面
//                Intent intent5 =  new Intent(getActivity(),CXDD.class);
//                intent5.putExtra("tuihuan","售后服务");
//                startActivity(intent5);
//                break;
        }
    }

    //设置状态栏
    public void TongMing() {
        //如果手机有虚拟按键 那么不能添加透明状态栏
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        //   tintManager.setStatusBarTintResource(R.color.zhuangtailan);
        tintManager.setTintColor(Color.parseColor("#000000"));

    }


}
