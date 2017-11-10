package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/8.
 */

public class ZJZP_WanShan extends Activity {
    private SharedUtils utils;
    private String token;
    private static final String TAG = "ZJZP_WanShan";
    private ImageView zm;
    private ImageView fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjzp_wanshan);
        utils = new SharedUtils();
        token = utils.getShared("token", ZJZP_WanShan.this);

        initView();
        initLianWang();
    }

    private void initLianWang() {
        HashMap<String ,String> map = new HashMap<>();
        map.put("token",token);
        OkUtils.UploadSJ(WangZhi.WODE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = hand.obtainMessage();
                message.obj=string;
                message.what=200;
                hand.sendMessage(message);
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
                    JSONObject msg1 = data.getJSONObject("msg");
                    String identityFace = msg1.getString("identityFace");
                    String identityBack = msg1.getString("identityBack");
                    Glide.with(ZJZP_WanShan.this).load(WangZhi.TUPIAN+identityBack).into(fm);
                    Glide.with(ZJZP_WanShan.this).load(WangZhi.TUPIAN+identityFace).into(zm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.title_back);
        TextView title = (TextView) findViewById(R.id.title_text);
        zm = (ImageView) findViewById(R.id.sfz_zm);
        fm = (ImageView) findViewById(R.id.sfz_fm);

        title.setText("身份认证");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
