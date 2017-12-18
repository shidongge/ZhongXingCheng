package us.mifeng.zhongxingcheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.BZZX;
import us.mifeng.zhongxingcheng.activity.GRZX;
import us.mifeng.zhongxingcheng.activity.HYZX;
import us.mifeng.zhongxingcheng.activity.ZZC;
import us.mifeng.zhongxingcheng.activity.ZhangDan;
import us.mifeng.zhongxingcheng.denlgu.SettingActivity;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by shido on 2017/10/17.
 */

public class WoDeFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rela;
    private View inflate;
    private static final String TAG = "WoDeFragment";
    private LinearLayout bzzx,zzc,yue,yinghangka;
    private TextView phone,nincheng,shezhi;
    private String id;
    private String token;
    private ImageView img;
    private String portrait;
    private LinearLayout zhangdan,hyzx;
    private String mobile;
    private SharedUtils sharedUtils;
    private String nickName;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_wode, null);
        sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", getActivity());
        mobile = sharedUtils.getShared("mobile", getActivity());
        String sessionId = sharedUtils.getShared("sessionId", getActivity());
        initView();
        initCGGRXX();
//        initLianWang();
        return inflate;
    }

    private void initCGGRXX() {
        String realStatus = JiaMi.jdkBase64Encoder("mobile,nickName,portrait");
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("field",realStatus);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj=string;
                mess.what=100;
                hand.sendMessage(mess);

            }
        });
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        OkUtils.UploadSJ(WangZhi.WODE, map, new Callback() {
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

    private void initView() {
        zhangdan = (LinearLayout) inflate.findViewById(R.id.wode_zhangdan);
        zzc = (LinearLayout) inflate.findViewById(R.id.wode_zzc);
        rela = (RelativeLayout) inflate.findViewById(R.id.wode_rela);
        shezhi = (TextView) inflate.findViewById(R.id.wode_shezhi);
        bzzx = (LinearLayout) inflate.findViewById(R.id.wode_bzzx);
        phone = (TextView) inflate.findViewById(R.id.wode_phone);
        nincheng = (TextView) inflate.findViewById(R.id.wode_nincheng);
        img = (ImageView) inflate.findViewById(R.id.fragment_wode_img);
        yue = (LinearLayout) inflate.findViewById(R.id.wode_yue);
        yinghangka = (LinearLayout) inflate.findViewById(R.id.wode_yinghangka);
        hyzx = (LinearLayout) inflate.findViewById(R.id.wode_hyzx);
        shezhi.setOnClickListener(this);
        rela.setOnClickListener(this);
        bzzx.setOnClickListener(this);
        yue.setOnClickListener(this);
        yinghangka.setOnClickListener(this);
        zzc.setOnClickListener(this);
        zhangdan.setOnClickListener(this);
        hyzx.setOnClickListener(this);
        //TODO 隐藏手机号中间四位
        if (!TextUtils.isEmpty(mobile)) {
            String mobile2 = mobile;
            String maskNumber = mobile.substring(0, 3) + "****" + mobile2.substring(7, mobile2.length());
            phone.setText(maskNumber);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wode_rela:
                startActivity(new Intent(getActivity(), GRZX.class));
                break;
            case R.id.wode_shezhi:
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                intent1.putExtra("img",portrait);
                intent1.putExtra("nickName",nickName);
                startActivity(intent1);
                break;
            case R.id.wode_bzzx:
                Intent intent = new Intent(getActivity(), BZZX.class);
                intent.putExtra("bangzhu","帮助中心");
                startActivity(intent);
                break;
            case R.id.wode_zzc:
                Intent intent2 = new Intent(getActivity(), ZZC.class);
                intent2.putExtra("zzc",portrait);
                startActivity(intent2);
                break;
            case R.id.wode_zhangdan:
                startActivity(new Intent(getActivity(), ZhangDan.class));
                break;
            case R.id.wode_yue:
                ToSi.show(getActivity(),"暂未开发");
                break;
            case R.id.wode_yinghangka:
                ToSi.show(getActivity(),"暂未开发");
                break;
            case R.id.wode_hyzx:
                startActivity(new Intent(getActivity(), HYZX.class));
                break;
            default:
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
                    JSONObject msg1 = data.getJSONObject("msg");
                    nickName = msg1.getString("nickName");

                    //图片地址
                    portrait = msg1.getString("portrait");
                    SharedUtils sharedUtils = new SharedUtils();
                    sharedUtils.saveShared("portrait", portrait,getActivity());
                    String mobile = msg1.getString("mobile");

                    //TODO 隐藏手机号中间四位
                    String mobile2 = mobile;
                    String maskNumber = mobile.substring(0,3)+"****"+mobile2.substring(7,mobile2.length());
                    sharedUtils.saveShared("yicangshoujihao",maskNumber,getActivity());

                    phone.setText(maskNumber);
                    Log.e(TAG, "handleMessage: "+ nickName);
                    if ("".equals(nickName)){
                        nincheng.setText("未设置昵称");
                    }else {
                        nincheng.setText(nickName);
                    }
                    Log.e(TAG, "handleMessage: "+ portrait);
                    if ("".equals(portrait)){
                        img.setImageResource(R.mipmap.tx);
                    }else {

                        Glide.with(getActivity()).load(WangZhi.TUPIAN+ portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what==100){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject userInfo = data.getJSONObject("userInfo");
                    String mobile = userInfo.getString("mobile");
                    nickName = userInfo.getString("nickName");
                    portrait = userInfo.getString("portrait");
                    Log.e(TAG, "handleMessage:我是图片地址 "+portrait );
                    if ("".equals(nickName)){
                        nincheng.setText("未设置");
                    }else {
                        nincheng.setText(nickName);
                    }
                    if ("".equals(portrait)){
                        img.setImageResource(R.mipmap.tx);
                    }
                    else {
                        Glide.with(getActivity()).load(WangZhi.TUPIAN+portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        initCGGRXX();
    }
}
