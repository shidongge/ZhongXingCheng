package us.mifeng.zhongxingcheng.denlgu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.MainActivity;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/10/18.
 */

public class Activity_denglu extends Activity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private static final String TAG = "Activity_denglu";
    private String user;
    private String pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_denglu);
        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button denglu = (Button) findViewById(R.id.denglu);
        denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.denglu:
                user = username.getText().toString().trim();
                pass = password.getText().toString().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile", user);
                map.put("pwd", pass);
                if (user.equals("")|| pass.equals("")){
                    ToSi.show(Activity_denglu.this,"用户名或密码不能为空");
                }else {
                    OkUtils.UploadSJ(WangZhi.DENGLU, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
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
                    Log.e(TAG, "jsonObject: " + jsonObject);
                    JSONObject data = jsonObject.getJSONObject("data");
                    String msg1 = data.getString("msg");
                    if (msg1.equals("0")){
                        String sessionId = data.getString("sessionId");
                        String token = data.getString("token");
                        SharedUtils sharedUtils = new SharedUtils();
                        sharedUtils.saveShared("sessionId","ci_session="+sessionId,Activity_denglu.this);
                        sharedUtils.saveShared("token",token,Activity_denglu.this);
                        startActivity(new Intent(Activity_denglu.this, MainActivity.class));
                   }else if (msg1.equals("1")){
                        ToSi.show(Activity_denglu.this, "用户不存在");
                    }else if (msg1.equals("2")){
                        ToSi.show(Activity_denglu.this, "账号或密码不正确");
                    }else if (msg1.equals("3")){
                        ToSi.show(Activity_denglu.this, "用户已被禁止登陆");
                    }else {
                        ToSi.show(Activity_denglu.this, "参数不齐");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };




}
