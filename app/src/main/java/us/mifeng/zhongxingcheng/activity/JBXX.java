package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/7.
 */

public class JBXX extends Activity implements View.OnClickListener {
    private static final String TAG = "5";
    private String jbxx;
    private EditText name, sfz;
    private String token;
    private TextView ssdq,xingbie;
    private TextView nianyueri;
    private String trim;
    //身份证查询地址：http://api.46644.com/idcard?idcard=429001&appkey=1307ee261de8bbcf83830de89caae73f
    //idcard是身份证前六位

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbxx);
        jbxx = getIntent().getStringExtra("jbxx");
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", JBXX.this);
        initView();
    }

    //TODO 获取个人资料一些基本信息
    private void initLianWang() {
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
                Message message = hand.obtainMessage();
                message.obj = string;
                message.what = 200;
                hand.sendMessage(message);

            }
        });
    }
    //获取个人资料的handler
//    Handler hand = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what==200){
//                String str = (String) msg.obj;
//                try {
//                    JSONObject jsonObject = new JSONObject(str);
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    JSONObject msg1 = data.getJSONObject("msg");
//                    String identityCard = msg1.getString("identityCard");
//                    String realName = msg1.getString("realName");
//                    String gender = msg1.getString("gender");
//                    sfz.setText(identityCard);
//                    name.setText(realName);
//                    if (gender.equals("1")){
//                        //xingbie.setText("男");
//                    }else if (gender.equals("2")){
//                        //xingbie.setText("女");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };

    private void initView() {
        TextView text = (TextView) findViewById(R.id.title_text);
        ImageView back = (ImageView) findViewById(R.id.title_back);
        name = (EditText) findViewById(R.id.jbxx_name);
        nianyueri = (TextView) findViewById(R.id.jbxx_nianyueri);
        xingbie = (TextView) findViewById(R.id.jbxx_xingbie);
        sfz = (EditText) findViewById(R.id.jbxx_sfz);
        Button jiaoyan = (Button) findViewById(R.id.jbxx_jiaoyan);
        Button tijiao = (Button) findViewById(R.id.jbxx_tijiao);
        ssdq = (TextView) findViewById(R.id.jbxx_ssdq);
        jiaoyan.setOnClickListener(this);
        tijiao.setOnClickListener(this);
        text.setText(jbxx);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.jbxx_jiaoyan:
                trim = sfz.getText().toString().trim();
                if (trim.length() < 18) {
                    ToSi.show(JBXX.this, "身份证输入位数不足");
                } else {
                    String substring = trim.substring(0, 6);
                    HashMap<String, String> map = new HashMap<>();
                    //map.put("idcard",substring);
                    Log.e(TAG, "onClick: 身份证6位 " + substring);
                    // map.put("appkey","1307ee261de8bbcf83830de89caae73f");
                    OkUtils.UploadSJ("http://api.46644.com/idcard?idcard=" + substring + "&appkey=1307ee261de8bbcf83830de89caae73f", map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            // Log.e(TAG, "onResponse: "+ response.body().string());
                            String string = response.body().string();
                            Message mess = hand.obtainMessage();
                            mess.obj = string;
                            mess.what = 300;
                            hand.sendMessage(mess);

                        }
                    });
                }


                break;
            case R.id.jbxx_tijiao:
                String trim1 = name.getText().toString().trim();
                if ("".equals(trim1)){
                    ToSi.show(JBXX.this,"请输入名字");
                } else if (("".equals(xingbie.getText().toString().trim())||("").equals(nianyueri.getText().toString().trim())||("").equals(ssdq.getText().toString().trim()))) {
                    ToSi.show(JBXX.this,"请输入身份证");
                }else {
                    if (sfz.getText().length()<18){
                        ToSi.show(JBXX.this,"身份证位数不足");
                    }else {

                        HashMap<String, String> map = new HashMap<>();
                        map.put("realName",trim1);
                        map.put("identityCard",sfz+"");
                        map.put("gender",xingbie.getText().toString());
                        map.put("birthDate",nianyueri.getText().toString());
                        map.put("city",ssdq.getText().toString());
//                        OkUtils.UploadSJ(WangZhi.);
                    }
                }
                break;
        }
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 300) {
                String obj = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(obj);
                    String msg1 = jsonObject.getString("msg");
                    String error = jsonObject.getString("error");
                    Log.e(TAG, "handleMessage: sssssss" + error);
                    if ("没有信息".equals(msg1)){
                        ToSi.show(JBXX.this,"身份证不正确");
                    } else if ("0".equals(error)) {
                        String replace = msg1.replace("所属区域: ", "");
                        ssdq.setText(replace);
                        String substring2 = trim.substring(6, 14);
                        nianyueri.setText(substring2);

                        String sCardNum = trim.substring(16, 17);
                        if (Integer.parseInt(sCardNum) % 2 != 0) {
                            xingbie.setText("男");//男
                        } else {
                            xingbie.setText("女");//女
                        }
                    } else {
                        ToSi.show(JBXX.this, "请输入有效身份证");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
