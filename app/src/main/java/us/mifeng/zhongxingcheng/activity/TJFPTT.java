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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/8.
 */
//添加发票抬头
public class TJFPTT extends Activity implements View.OnClickListener {

    private EditText name;
    private EditText shibiema;
    private String token, substring, zxcid;
    private static final String TAG = "TJFPTT";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjfptt);
        SharedUtils sharedUtils = new SharedUtils();
        String id = sharedUtils.getShared("id", TJFPTT.this);
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", TJFPTT.this);
        zxcid = sharedUtils.getShared("zxcid", TJFPTT.this);
        initView();
    }

    private void initView() {
        TextView xinzeng = (TextView) findViewById(R.id.tjttfp_baocun);
        ImageView back = (ImageView) findViewById(R.id.tjttfp_back);
        name = (EditText) findViewById(R.id.tjttfp_name);
        shibiema = (EditText) findViewById(R.id.tjttfp_shibiema);
        back.setOnClickListener(this);
        xinzeng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tjttfp_back:
                finish();
                break;
            case R.id.tjttfp_baocun:
                String trim = name.getText().toString().trim();
                String trim1 = shibiema.getText().toString().trim();
                if (trim.equals("") || trim1.equals("")) {
                    ToSi.show(TJFPTT.this, "信息填写不完整，请重新检查输入框");
                } else {
                    final HashMap<String, String> map = new HashMap<>();
                    map.put("user_id", zxcid);
                    map.put("user_token",token);
                    map.put("user_mobile",substring);
                    map.put("title",trim);
                    map.put("id_code",trim1);
                    JSONObject jsonObject = new JSONObject(map);
                    final String string = jsonObject.toString();
                    final String s = JiaMi.jdkBase64Encoder(string);
                    HashMap<String, String> map1 = new HashMap<>();
                    map1.put("secret",s);
                    OkUtils.UploadSJ(WangZhi.TJFFTT, map1, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: "+e.getLocalizedMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string1 = response.body().string();
                            Message mess = handler.obtainMessage();
                            mess.what=200;
                            mess.obj=string1;
                            handler.sendMessage(mess);

                        }
                    });
                }
                break;
            default:
                break;
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String status = jsonObject.getString("status");
                    String info = jsonObject.getString("info");
                    if ("0".equals(status)){
                        ToSi.show(TJFPTT.this,info);
                        finish();
                    }else {
                        ToSi.show(TJFPTT.this,info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
