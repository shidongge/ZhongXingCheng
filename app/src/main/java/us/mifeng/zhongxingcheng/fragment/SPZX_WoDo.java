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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.SPZX_SHDZGL;
import us.mifeng.zhongxingcheng.activity.SQTX;
import us.mifeng.zhongxingcheng.activity.TXJL;
import us.mifeng.zhongxingcheng.activity.WDHB;
import us.mifeng.zhongxingcheng.activity.WDJX;
import us.mifeng.zhongxingcheng.activity.WDYJ;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by shido on 2017/12/6.
 */

public class SPZX_WoDo extends Fragment implements View.OnClickListener {
    private static final String TAG = "SPZX_WoDo";
    private View inflate;
    private TextView nincheng,xiaoshoue_text,yeji_text,ll_huoban_text,dengji;
    private ImageView huoban_img,jixiao_img,yeji_img,img;
    private LinearLayout huoban,yeji,jixiao,huoban_gone,yeji_gone,jixiao_gone;
    private LinearLayout geren_qingtong,geren_baiyin,geren_huangjin,geren_bojin,geren_zuanshi;
    private TextView qingtong_num,baiyin_num,huangjin_num,bojin_num,zuanshi_num;
    private  boolean huoban_panduan = false;
    private boolean jixiao_panduan = false;
    private boolean yeji_panduan = false;
    private LinearLayout geren_sqtx,geren_txjl,geren_shdz;
    private LinearLayout geren_wfk,geren_yfk,geren_yfh,geren_ywc;
    private LinearLayout geren_djs,geren_ytx,geren_ktx,geren_bbh,geren_sqz,geren_ddk;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = View.inflate(getActivity(), R.layout.fragment_spzx_wode, null);
        token = new SharedUtils().getShared("token", getActivity());
        initView();
        initLianWang();
        return inflate;
    }

    private void initLianWang() {
        String s = JiaMi.jdkBase64Encoder("nickName,portrait");
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("field",s);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mess = hand.obtainMessage();
                mess.what=100;
                mess.obj=string;
                hand.sendMessage(mess);
            }
        });

    }

    private void initView() {
        nincheng = (TextView) inflate.findViewById(R.id.geren_nincheng);
        xiaoshoue_text = (TextView) inflate.findViewById(R.id.geren_xse_text);
        yeji_text = (TextView) inflate.findViewById(R.id.geren_yeji_text);
        ll_huoban_text = (TextView) inflate.findViewById(R.id.geren_huoban_ll_text);
        dengji = (TextView) inflate.findViewById(R.id.geren_dengji);
        img = (ImageView) inflate.findViewById(R.id.geren_img);
        huoban_img = (ImageView) inflate.findViewById(R.id.geren_huoban_ll_img);
        jixiao_img = (ImageView) inflate.findViewById(R.id.geren_jixiao_ll_img);
        yeji_img = (ImageView) inflate.findViewById(R.id.geren_yeji_ll_img);

        huoban = (LinearLayout) inflate.findViewById(R.id.geren_huoban_ll);
        yeji = (LinearLayout) inflate.findViewById(R.id.geren_yeji_ll);
        jixiao = (LinearLayout) inflate.findViewById(R.id.geren_jixiao_ll);
        huoban_gone = (LinearLayout) inflate.findViewById(R.id.geren_huoban_gone);
        yeji_gone = (LinearLayout) inflate.findViewById(R.id.wode_yeji_gone);
        jixiao_gone = (LinearLayout) inflate.findViewById(R.id.geren_yeji_gone);

        geren_qingtong = (LinearLayout) inflate.findViewById(R.id.geren_qingtong);
        geren_baiyin = (LinearLayout) inflate.findViewById(R.id.geren_baiyin);
        geren_huangjin = (LinearLayout) inflate.findViewById(R.id.geren_huangjin);
        geren_bojin = (LinearLayout) inflate.findViewById(R.id.geren_bojin);
        geren_zuanshi = (LinearLayout) inflate.findViewById(R.id.geren_zuanshi);

        qingtong_num = (TextView) inflate.findViewById(R.id.geren_qingtong_num);
        baiyin_num = (TextView) inflate.findViewById(R.id.geren_baiyin_num);
        huangjin_num = (TextView) inflate.findViewById(R.id.geren_huangjin_num);
        bojin_num = (TextView) inflate.findViewById(R.id.geren_bojin_num);
        zuanshi_num = (TextView) inflate.findViewById(R.id.geren_zuanshi_num);

        geren_wfk = (LinearLayout) inflate.findViewById(R.id.geren_wfk);
        geren_yfk = (LinearLayout) inflate.findViewById(R.id.geren_yfk);
        geren_yfh = (LinearLayout) inflate.findViewById(R.id.geren_yfh);
        geren_ywc = (LinearLayout) inflate.findViewById(R.id.geren_ywc);



        geren_sqtx = (LinearLayout) inflate.findViewById(R.id.geren_sqtx);
        geren_txjl = (LinearLayout) inflate.findViewById(R.id.geren_txjl);
        geren_shdz = (LinearLayout) inflate.findViewById(R.id.geren_shdz);

        geren_djs = (LinearLayout) inflate.findViewById(R.id.geren_djs);
        geren_ytx = (LinearLayout) inflate.findViewById(R.id.geren_ytx);
        geren_ktx = (LinearLayout) inflate.findViewById(R.id.geren_ktx);
        geren_bbh = (LinearLayout) inflate.findViewById(R.id.geren_bbh);
        geren_sqz = (LinearLayout) inflate.findViewById(R.id.geren_sqz);
        geren_ddk = (LinearLayout) inflate.findViewById(R.id.geren_ddk);


//        huoban_img.setOnClickListener(this);
//        jixiao_img.setOnClickListener(this);
//        yeji_img.setOnClickListener(this);
        huoban.setOnClickListener(this);
        yeji.setOnClickListener(this);
        jixiao.setOnClickListener(this);

        geren_qingtong.setOnClickListener(this);
        geren_baiyin.setOnClickListener(this);
        geren_huangjin.setOnClickListener(this);
        geren_bojin.setOnClickListener(this);
        geren_zuanshi.setOnClickListener(this);

        geren_sqtx.setOnClickListener(this);
        geren_txjl.setOnClickListener(this);
        geren_shdz.setOnClickListener(this);

        geren_wfk.setOnClickListener(this);
        geren_yfk.setOnClickListener(this);
        geren_yfh.setOnClickListener(this);
        geren_ywc.setOnClickListener(this);

        geren_djs.setOnClickListener(this);
        geren_ytx.setOnClickListener(this);
        geren_ktx.setOnClickListener(this);
        geren_bbh.setOnClickListener(this);
        geren_sqz.setOnClickListener(this);
        geren_ddk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.geren_huoban_ll:
                if (huoban_panduan==false){
                    huoban_gone.setVisibility(View.VISIBLE);
                    huoban_img.setImageResource(R.mipmap.fhxl);
                    huoban_panduan=true;
                }else {
                    huoban_img.setImageResource(R.mipmap.fh1);
                    huoban_gone.setVisibility(View.GONE);
                    huoban_panduan=false;
                }
                break;
            case R.id.geren_jixiao_ll:
                if (jixiao_panduan==false){
                    jixiao_img.setImageResource(R.mipmap.fhxl);
                    jixiao_gone.setVisibility(View.VISIBLE);
                    jixiao_panduan=true;
                }else {
                    jixiao_img.setImageResource(R.mipmap.fh1);
                    jixiao_gone.setVisibility(View.GONE);
                    jixiao_panduan=false;
                }
                break;
            case R.id.geren_yeji_ll:
                if (yeji_panduan==false){
                    yeji_img.setImageResource(R.mipmap.fhxl);
                    yeji_gone.setVisibility(View.VISIBLE);
                    yeji_panduan=true;
                }else {
                    yeji_img.setImageResource(R.mipmap.fh1);
                    yeji_gone.setVisibility(View.GONE);
                    yeji_panduan=false;
                }
                break;
            case R.id.geren_txjl:
                startActivity(new Intent(getActivity(), TXJL.class));
                break;


            case R.id.geren_qingtong:
                Intent intent1 = new Intent(getActivity(), WDHB.class);
                intent1.putExtra("tag","1");
                startActivity(intent1);
                break;
            case R.id.geren_baiyin:
                Intent intent2 = new Intent(getActivity(), WDHB.class);
                intent2.putExtra("tag","2");
                startActivity(intent2);
                break;
            case R.id.geren_huangjin:
                Intent intent3 = new Intent(getActivity(), WDHB.class);
                intent3.putExtra("tag","3");
                startActivity(intent3);
                break;
            case R.id.geren_bojin:
                Intent intent4 = new Intent(getActivity(), WDHB.class);
                intent4.putExtra("tag","4");
                startActivity(intent4);
                break;
            case R.id.geren_zuanshi:
                Intent intent5 = new Intent(getActivity(), WDHB.class);
                intent5.putExtra("tag","5");
                startActivity(intent5);
                break;
            case R.id.geren_sqtx:
                Intent geren_sqtx = new Intent(getActivity(), SQTX.class);
                startActivity(geren_sqtx);
                break;
            case R.id.geren_shdz:
                startActivity(new Intent(getActivity(), SPZX_SHDZGL.class));
                break;


            case R.id.geren_wfk:
                Intent wdjx_wfk = new Intent(getActivity(), WDJX.class);
                startActivity(wdjx_wfk);
                break;
            case R.id.geren_yfk:
                Intent wdjx_yfk = new Intent(getActivity(), WDJX.class);
                startActivity(wdjx_yfk);
                break;
            case R.id.geren_yfh:
                Intent wdjx_yfh = new Intent(getActivity(), WDJX.class);
                startActivity(wdjx_yfh);
                break;
            case R.id.geren_ywc:
                Intent wdjx_ywc = new Intent(getActivity(), WDJX.class);
                startActivity(wdjx_ywc);
                break;


            case R.id.geren_djs:
                Intent geren_djs = new Intent(getActivity(), WDYJ.class);
                startActivity(geren_djs);
                break;
            case R.id.geren_ytx:
                Intent geren_ytx = new Intent(getActivity(), WDYJ.class);
                startActivity(geren_ytx);
                break;
            case R.id.geren_ktx:
                Intent geren_ktx = new Intent(getActivity(), WDYJ.class);
                startActivity(geren_ktx);
                break;
            case R.id.geren_bbh:
                Intent geren_bbh = new Intent(getActivity(), WDYJ.class);
                startActivity(geren_bbh);
                break;
            case R.id.geren_sqz:
                Intent geren_sqz = new Intent(getActivity(), WDYJ.class);
                startActivity(geren_sqz);
                break;
            case R.id.geren_ddk:
                Intent geren_ddk = new Intent(getActivity(), WDYJ.class);
                startActivity(geren_ddk);
                break;

            default:
                break;
        }
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
                    JSONObject userInfo = data.getJSONObject("userInfo");
                    String nickName = userInfo.getString("nickName");
                    String portrait = userInfo.getString("portrait");
                    Log.e(TAG, "handleMessage: "+portrait );
                    if ("".equals(nickName)){
                        nincheng.setText("未设置");
                    }else {
                        nincheng.setText(nickName);
                    }
                    if ("".equals(portrait)){
                        img.setImageResource(R.mipmap.tx);
                    }else {
                        Glide.with(getActivity()).load(WangZhi.TUPIAN+ portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
