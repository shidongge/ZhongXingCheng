package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
import us.mifeng.zhongxingcheng.adapter.FPGLAdapter;
import us.mifeng.zhongxingcheng.bean.FPGLBean;
import us.mifeng.zhongxingcheng.utils.FFGLEvent;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/10.
 */
//发票管理界面
public class FPGL extends Activity implements View.OnClickListener {
    private static final String TAG = "FPGL";
    private ListView mlv;
    private TextView xinzeng;
    private ImageView back,wu;
    private String token,zxcid,substring;
    private List<FPGLBean.DataBean> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpgl);
        EventBus.getDefault().register(this);
        SharedUtils sharedUtils = new SharedUtils();
        String id = sharedUtils.getShared("id", FPGL.this);
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", FPGL.this);
        zxcid = sharedUtils.getShared("zxcid", FPGL.this);
        list = new ArrayList<>();
        initView();
        initLianWang();
    }

    private void initView() {

        mlv = (ListView) findViewById(R.id.fpgl_lv);
        xinzeng = (TextView) findViewById(R.id.fpgl_xinzeng);
        back = (ImageView) findViewById(R.id.fpgl_back);
        wu = (ImageView) findViewById(R.id.fpgl_wu);
        back.setOnClickListener(this);
        xinzeng.setOnClickListener(this);

    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",zxcid);
        map.put("user_token",token);
        map.put("user_mobile",substring);
        JSONObject jsonObject = new JSONObject(map);
        final String string = jsonObject.toString();
        final String s = JiaMi.jdkBase64Encoder(string);
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("secret",s);
        OkUtils.UploadSJ(WangZhi.FPGLLB, map1, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: "+response.body().string() );
                String string1 = response.body().string();
                Message mess = hand.obtainMessage();
                mess.obj=string1;
                mess.what=200;
                hand.sendMessage(mess);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fpgl_xinzeng:
                startActivity(new Intent(FPGL.this, TJFPTT.class));
                break;
            case R.id.fpgl_back:
                finish();
                break;
            default:
                break;
        }
    }
    private FPGLAdapter fpglAdapter;
    Handler hand = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String total = jsonObject.getString("total");
                    if ("0".equals(total)){
                        wu.setVisibility(View.VISIBLE);
                        mlv.setVisibility(View.GONE);
                    }else {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i<data.length();i++){
                            JSONObject jsonObject1 = data.getJSONObject(i);
                            String id = jsonObject1.getString("id");
                            String title = jsonObject1.getString("title");
                            String id_code = jsonObject1.getString("id_code");
                            String is_default = jsonObject1.getString("is_default");
                            FPGLBean.DataBean dataBean = new FPGLBean.DataBean();
                            dataBean.setId(id);
                            dataBean.setId_code(id_code);
                            dataBean.setTitle(title);
                            dataBean.setIs_default(is_default);
                            list.add(dataBean);
                        }
                        if (fpglAdapter==null){
                            fpglAdapter = new FPGLAdapter(list, FPGL.this);
                            mlv.setAdapter(fpglAdapter);
                        }else {
                            fpglAdapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        wu.setVisibility(View.GONE);
        mlv.setVisibility(View.VISIBLE);
        list.clear();
        initLianWang();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(FFGLEvent event) {
        list.clear();
        initLianWang();

    }
}
