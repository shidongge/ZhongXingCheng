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
import us.mifeng.zhongxingcheng.adapter.SHDZGLAdapter;
import us.mifeng.zhongxingcheng.base.SHDZBean;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SCSHDZEvent;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 收货地址管理界面
 */
public class SHDZGL extends Activity implements View.OnClickListener {
    private static final String TAG = "SHDZGL";
    private List<SHDZBean.DataBean> list;
    private TextView xz;
    private ImageView back;
    private ListView lv;
    private String token,zxcid,substring;
    private SHDZGLAdapter shdzglAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shdzgl);
        initView();
        EventBus.getDefault().register(this);
        SharedUtils sharedUtils = new SharedUtils();
        String id = sharedUtils.getShared("id", SHDZGL.this);
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", SHDZGL.this);
        zxcid = sharedUtils.getShared("zxcid", SHDZGL.this);
        list = new ArrayList<>();
        initLianWang();
    }

    private void initLianWang() {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", zxcid);
        map.put("user_token", token);
        map.put("user_mobile", substring);

        JSONObject jsonObject = new JSONObject(map);
        String string1 = jsonObject.toString();
        String s = JiaMi.jdkBase64Encoder(string1);
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("secret",s);
        OkUtils.UploadSJ(WangZhi.SHDZLB, map1, new Callback() {
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
                mess.what = 200;
                hand.sendMessage(mess);
            }
        });

    }

    private void initView() {
        xz = (TextView) findViewById(R.id.shdzgl_xz);
        back = (ImageView) findViewById(R.id.shdzgl_back);
        lv = (ListView) findViewById(R.id.shdzgl_lv);
        xz.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shdzgl_xz:
                Intent intent = new Intent(SHDZGL.this, XZSHDZ.class);
                intent.putExtra("xzshdz", "新增收货地址");
                startActivity(intent);
                break;
            case R.id.shdzgl_back:
                finish();
                break;
            default:
                break;
        }
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String status = jsonObject.getString("status");
                    String info = jsonObject.getString("info");
                    if ("0".equals(status)) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject1 = data.getJSONObject(i);
                            String id1 = jsonObject1.getString("id");

                            String userName = jsonObject1.getString("userName");

                            String mobile = jsonObject1.getString("mobile");

                            String province = jsonObject1.getString("province");

                            String city = jsonObject1.getString("city");
                            String district = jsonObject1.getString("district");

                            String address = jsonObject1.getString("address");
                            String zip_code = jsonObject1.getString("zip_code");
                            String isDefault = jsonObject1.getString("isDefault");
                            SHDZBean.DataBean dataBean = new SHDZBean.DataBean();
                            dataBean.setAddress(address);
                            dataBean.setCity(city);
                            dataBean.setDistrict(district);
                            dataBean.setId(id1);
                            dataBean.setZip_code(zip_code);
                            dataBean.setMobile(mobile);
                            dataBean.setProvince(province);
                            dataBean.setIsDefault(isDefault);
                            dataBean.setUserName(userName);
                            list.add(dataBean);
                        }

                        if (shdzglAdapter == null) {
                            shdzglAdapter = new SHDZGLAdapter(SHDZGL.this, list);
                            lv.setAdapter(shdzglAdapter);
                        } else {
                            shdzglAdapter.notifyDataSetChanged();
                        }

                    }
                    else {
                        ToSi.show(SHDZGL.this,info);
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
            list.clear();
        initLianWang();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEvent(SCSHDZEvent event) {
        list.clear();
        initLianWang();

    }
}
