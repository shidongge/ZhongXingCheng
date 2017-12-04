package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.NinChengEvent;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/10/30.
 */

/**
 * 昵称界面
 */
public class NinChen extends Activity {
    private static final String TAG = "NinChen";
    private TextView bc;
    private EditText nc;
    private String tag;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninchen);
        tag = getIntent().getStringExtra("tag");
        Log.e(TAG, "onCreate: "+tag );
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", NinChen.this);
        initView();
    }

    private void initView() {
        bc = (TextView) findViewById(R.id.nc_bc);
        nc = (EditText) findViewById(R.id.nc_nc);
        ImageView back = (ImageView) findViewById(R.id.nincheng_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = nc.getText().toString().trim();
                if (trim.equals("")){
                    ToSi.show(NinChen.this,"请输入昵称");
                }else {
                    if ("1".equals(tag)){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("nickName",trim);
                        JSONObject jsonObject = new JSONObject(map);
                        String string = jsonObject.toString();
                        String s = JiaMi.jdkBase64Encoder(string);
                        HashMap<String, String> map1 = new HashMap<>();
                        map1.put("token",token);
                        map1.put("field",s);
                        OkUtils.UploadSJ(WangZhi.GXJRXX, map1, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string1 = response.body().string();
                                Message mess = hand.obtainMessage();
                                mess.obj=string1;
                                mess.what=100;
                                hand.sendMessage(mess);
                            }
                        });
                    }else if ("2".equals(tag)){

                        EventBus.getDefault().post(new NinChengEvent(trim));
                        finish();
                    }
                }
            }
        });
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
                    String msg1 = data.getString("msg");
                    if ("0".equals(msg1)){
                        ToSi.show(NinChen.this,"修改成功");
                        finish();
                    }else {
                        ToSi.show(NinChen.this,"修改失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
