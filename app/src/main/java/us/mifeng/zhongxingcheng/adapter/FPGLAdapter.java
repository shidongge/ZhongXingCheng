package us.mifeng.zhongxingcheng.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.XGFPTT;
import us.mifeng.zhongxingcheng.bean.FPGLBean;
import us.mifeng.zhongxingcheng.utils.FFGLEvent;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/12/14.
 */

public class FPGLAdapter extends BaseAdapter {
    private static final String TAG = "FPGLAdapter";
    private List<FPGLBean.DataBean> list;
    private Context context;
    private String token;
    private String zxcid;
    private String substring;
    public FPGLAdapter(List<FPGLBean.DataBean> list, Context context){
        this.list=list;
        this.context=context;
        SharedUtils sharedUtils = new SharedUtils();
        String id = sharedUtils.getShared("id", context);
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", context);
        zxcid = sharedUtils.getShared("zxcid", context);
    }
    @Override
    public int getCount() {
        if (list.size()!=0){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHorder vh;
        if (convertView==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_fpgl,null );
            vh.bj = (LinearLayout) convertView.findViewById(R.id.fpgl_bj);
            vh.sc = (LinearLayout) convertView.findViewById(R.id.fpgl_sc);
            vh.daima = (TextView) convertView.findViewById(R.id.fpgl_daima);
            vh.name = (TextView) convertView.findViewById(R.id.fpgl_name);
            vh.moren = (ImageView) convertView.findViewById(R.id.fpgl_mr);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.daima.setText(list.get(position).getId_code());
        vh.name.setText(list.get(position).getTitle());
        final String is_default = list.get(position).getIs_default();
        if ("0".equals(is_default)){
            vh.moren.setVisibility(View.GONE);
        }else {
            vh.moren.setVisibility(View.VISIBLE);
        }


        vh.bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String is_default1 = list.get(position).getIs_default();
                String id = list.get(position).getId();
                String title = list.get(position).getTitle();
                String id_code = list.get(position).getId_code();

                Intent intent = new Intent(context, XGFPTT.class);
                intent.putExtra("is_default",is_default1);
                intent.putExtra("fpid",id);
                intent.putExtra("title",title);
                intent.putExtra("id_code",id_code);
                context.startActivity(intent);
            }
        });
        vh.sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这个发票移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = list.get(position).getId();
                                Log.e(TAG, "onClick: id是"+id );
                                HashMap<String, String> map = new HashMap<>();
                                map.put("user_id", zxcid);
                                map.put("user_token", token);
                                map.put("user_mobile", substring);
                                map.put("id", id);
                                JSONObject jsonObject = new JSONObject(map);
                                String string = jsonObject.toString();
                                String s = JiaMi.jdkBase64Encoder(string);
                                HashMap<String, String> map1 = new HashMap<>();
                                map1.put("secret", s);
                                OkUtils.UploadSJ(WangZhi.SCTTFP, map1, new Callback() {
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
                        });
                alert.show();
            }
        });

        return convertView;
    }
    class MyViewHorder{
        TextView name,daima;
        ImageView moren;
        LinearLayout bj,sc;
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String info = jsonObject.getString("info");
                    String status = jsonObject.getString("status");
                    Log.e(TAG, "handleMessage: "+status );
                    if ("0".equals(status)){
                        ToSi.show(context,"删除成功");
                        EventBus.getDefault().post(new FFGLEvent("这是要发送的内容"));
                    }else {
                        ToSi.show(context,info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
