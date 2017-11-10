package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;

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
import us.mifeng.zhongxingcheng.app.MyApplicaiton;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.zhaopian.SelectDialog;

/**
 * Created by shido on 2017/10/31.
 */

public class ZJZP extends Activity implements View.OnClickListener {

    private TextView biaoti;
    private String zjzp;
    private ImageView back, zhengmian, fanmian;
    private ImagePicker imagePicker;
    private int maxImgCount = 1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ArrayList<ImageItem> selImageList;
    private String token;
    private ImageItem item;
    private static final String TAG = "ZJZP";
    private int tag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjzp);
        zjzp = getIntent().getStringExtra("zjzp");
        imagePicker = MyApplicaiton.getImagePicker();
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", ZJZP.this);
        initView();
        initLianWang();

    }

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
                Message mess = hand.obtainMessage();
                mess.obj = string;
                mess.what = 400;
                hand.sendMessage(mess);
            }
        });
    }

    private void initView() {
        biaoti = (TextView) findViewById(R.id.title_text);
        zhengmian = (ImageView) findViewById(R.id.zjzp_zhengmian);
        fanmian = (ImageView) findViewById(R.id.zjzp_fanmian);
        back = (ImageView) findViewById(R.id.title_back);
        biaoti.setText(zjzp);
        back.setOnClickListener(this);
        zhengmian.setOnClickListener(this);
        fanmian.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.zjzp_zhengmian:
                tag = 1;
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent = new Intent(ZJZP.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(ZJZP.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }
                    }
                }, names);
                break;
            case R.id.zjzp_fanmian:
                tag = 2;
                List<String> names2 = new ArrayList<>();
                names2.add("拍照");
                names2.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent = new Intent(ZJZP.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(ZJZP.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }
                    }
                }, names2);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (tag == 1) {
            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                //添加图片返回
                if (data != null && requestCode == REQUEST_CODE_SELECT) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images != null) {
                        item = images.get(0);
                        // TODO 把图片设置到控件上
//                    ImagePicker.getInstance().getImageLoader().displayImage(this, item.path, img, 0, 0);
                        //TODO 图片上传
                        HashMap<String, String> map = new HashMap<>();
                        map.put("token", token);
                        OkUtils.UploadFileCS(WangZhi.ZHAOPIAN, "identityFace", item.path, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
//                            Log.e(TAG, "onResponse: "+response.body().string() );
                                String string = response.body().string();
                                Message message = hand.obtainMessage();
                                message.what = 100;
                                message.obj = string;
                                hand.sendMessage(message);
                            }
                        });
                    }
                }
            } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
                //预览图片返回
                if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    if (images != null) {
                        selImageList.clear();
                        selImageList.addAll(images);
                    }
                }
            }
        }
        if (tag == 2) {
            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                //添加图片返回
                if (data != null && requestCode == REQUEST_CODE_SELECT) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images != null) {
                        item = images.get(0);
                        // TODO 把图片设置到控件上
//                    ImagePicker.getInstance().getImageLoader().displayImage(this, item.path, img, 0, 0);
                        //TODO 图片上传
                        HashMap<String, String> map = new HashMap<>();
                        map.put("token", token);
                        OkUtils.UploadFileCS(WangZhi.ZHAOPIAN, "identityBack", item.path, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                                Message message = hand.obtainMessage();
                                message.what = 200;
                                message.obj = string;
                                hand.sendMessage(message);
                            }
                        });
                    }
                }
            } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
                //预览图片返回
                if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                    images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    if (images != null) {
                        selImageList.clear();
                        selImageList.addAll(images);
                    }
                }
            }

        }
    }

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage: 200" + jsonObject);
                    String success = jsonObject.getString("success");
                    if (success.equals("true")) {
                        ToSi.show(ZJZP.this, "上传成功");
                        ImagePicker.getInstance().getImageLoader().displayImage(ZJZP.this, item.path, zhengmian, 0, 0);
                    } else if (success.equals("false")) {
                        String msg1 = jsonObject.getString("msg");
                        ToSi.show(ZJZP.this, msg1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage: 300" + jsonObject);
                    String success = jsonObject.getString("success");
                    if (success.equals("true")) {
                        ToSi.show(ZJZP.this, "上传成功");
                        ImagePicker.getInstance().getImageLoader().displayImage(ZJZP.this, item.path, fanmian, 0, 0);
                    } else if (success.equals("false")) {
                        String msg1 = jsonObject.getString("msg");
                        ToSi.show(ZJZP.this, msg1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what == 400) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("msg");
                    String identityFace = msg1.getString("identityFace");
                    String identityBack = msg1.getString("identityBack");
                    if (identityBack.equals("")) {

                    } else {
                        Glide.with(ZJZP.this).load(WangZhi.TUPIAN + identityBack).into(fanmian);
                    }
                    if (identityFace.equals("")) {
                    } else {
                        Glide.with(ZJZP.this).load(WangZhi.TUPIAN + identityFace).into(zhengmian);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
