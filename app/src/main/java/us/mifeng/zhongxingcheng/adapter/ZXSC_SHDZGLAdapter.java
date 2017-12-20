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
import us.mifeng.zhongxingcheng.activity.XGSHDZ;
import us.mifeng.zhongxingcheng.base.SHDZBean;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SCSHDZEvent;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/12/13.
 */

public class ZXSC_SHDZGLAdapter extends BaseAdapter {
    private static final String TAG = "SHDZGLAdapter";
    private List<SHDZBean.DataBean> list;
    private Context context;
    private String token;
    private String zxcid;
    private String substring;
    private String isDefault;

    public ZXSC_SHDZGLAdapter(Context context, List<SHDZBean.DataBean> list) {
        this.context = context;
        this.list = list;
        SharedUtils sharedUtils = new SharedUtils();
        String id = sharedUtils.getShared("id", context);
        String newid = id;
        substring = newid.substring(0, 11);
        token = sharedUtils.getShared("token", context);
        zxcid = sharedUtils.getShared("zxcid", context);
    }

    @Override
    public int getCount() {
        if (list.size() != 0) {
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
        final MyViewHorder vh;
        if (convertView == null) {
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_zxsc_shdzgl, null);
            vh.moren = (LinearLayout) convertView.findViewById(R.id.item_shdzgl_moren);
            vh.bianji = (LinearLayout) convertView.findViewById(R.id.item_shdzgl_bianji);
            vh.shanchu = (LinearLayout) convertView.findViewById(R.id.item_shdzgl_shanchu);
            vh.xingming = (TextView) convertView.findViewById(R.id.item_shdzgl_xingming);
            vh.shouji = (TextView) convertView.findViewById(R.id.item_shdzgl_shouji);
            vh.sheng = (TextView) convertView.findViewById(R.id.item_shdzgl_sheng);
            vh.shi = (TextView) convertView.findViewById(R.id.item_shdzgl_shi);
            vh.qu = (TextView) convertView.findViewById(R.id.item_shdzgl_qu);
            vh.moren_img = (ImageView) convertView.findViewById(R.id.item_shdzgl_moren_img);
            vh.xiangxi = (TextView) convertView.findViewById(R.id.item_shdzgl_xiangxi);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.sheng.setText(list.get(position).getProvince());
        vh.shi.setText(list.get(position).getCity());
        vh.qu.setText(list.get(position).getDistrict());
        vh.xiangxi.setText(list.get(position).getAddress());
        vh.xingming.setText(list.get(position).getUserName());
        vh.shouji.setText(list.get(position).getMobile());
        isDefault = list.get(position).getIsDefault();
        if ("0".equals(isDefault)) {
            vh.moren_img.setImageResource(R.mipmap.shdzwg);
        } else {
            vh.moren_img.setImageResource(R.mipmap.shdzyg);
        }
        vh.bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(position).getId();
                String address = list.get(position).getAddress();
                String city = list.get(position).getCity();
                String district = list.get(position).getDistrict();
                String mobile = list.get(position).getMobile();
                isDefault = list.get(position).getIsDefault();
                String province = list.get(position).getProvince();
                String zip_code = list.get(position).getZip_code();
                String userName = list.get(position).getUserName();
                Intent intent = new Intent(context, XGSHDZ.class);
                intent.putExtra("dizhiid", id);
                intent.putExtra("city1", city);
                intent.putExtra("districe1", district);
                intent.putExtra("mobile1", mobile);
                intent.putExtra("isDefault1", isDefault);
                intent.putExtra("province1", province);
                intent.putExtra("zip_code1", zip_code);
                intent.putExtra("username1", userName);
                intent.putExtra("address1", address);
                context.startActivity(intent);
            }
        });
        vh.shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这个收货地址移除吗？");
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
                                final HashMap<String, String> map = new HashMap<>();
                                map.put("user_id", zxcid);
                                map.put("user_token", token);
                                map.put("user_mobile", substring);
                                map.put("id", id);
                                JSONObject jsonObject = new JSONObject(map);
                                String string = jsonObject.toString();
                                String s = JiaMi.jdkBase64Encoder(string);
                                HashMap<String, String> map1 = new HashMap<>();
                                map1.put("secret", s);
                                OkUtils.UploadSJ(WangZhi.SCHHDZ, map1, new Callback() {
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

        vh.moren_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(position).getId();
                isDefault = list.get(position).getIsDefault();
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
                if ("0".equals(isDefault)) {
                    vh.moren_img.setImageResource(R.mipmap.shdzyg);
                } else {
                    vh.moren_img.setImageResource(R.mipmap.shdzwg);
                }
                OkUtils.UploadSJ(WangZhi.SFMR, map1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.e(TAG, "onResponse: " + response.body().string());
                        String string1 = response.body().string();
                        Message message = hand.obtainMessage();
                        message.obj = string1;
                        message.what = 100;
                        hand.sendMessage(message);
                    }
                });
            }
        });
        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    class MyViewHorder {
        LinearLayout moren, bianji, shanchu;
        TextView xingming, shouji, sheng, shi, qu, xiangxi;
        ImageView moren_img;
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String info = jsonObject.getString("info");
                    String status = jsonObject.getString("status");
                    if ("0".equals(status)) {
                        ToSi.show(context, info);
                        EventBus.getDefault().post(new SCSHDZEvent("这是要发送的内容"));
                        notifyDataSetChanged();
                    } else {
                        ToSi.show(context, info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String info = jsonObject.getString("info");
                    String status = jsonObject.getString("status");
                    if ("0".equals(status)) {
                        ToSi.show(context, info);
                        EventBus.getDefault().post(new SCSHDZEvent("这是要发送的内容"));
                    } else {
                        ToSi.show(context, info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
