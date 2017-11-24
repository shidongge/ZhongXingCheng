package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.app.MyApplicaiton;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.SharedUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.zhaopian.SelectDialog;


/**
 * Created by shido on 2017/10/30.
 */

/**
 * 身份认证界面
 */
public class SFRZ extends Activity implements View.OnClickListener {
    protected static Uri tempUri;
    private EditText sfz,xingming;
    private String sfrz;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private ImageView img;
    private ImagePicker imagePicker;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private int maxImgCount = 1;
    private ArrayList<ImageItem> selImageList;
    private String token;
    private static final String TAG = "SFRZ";
    private ImageItem item;
    int tag;
    private TextView renzhen;
    private TextView zjzp_text;
    private String identityBack;
    private String identityFace;
    private String realStatus;
    private ImageView zjzp_zhengmian, zjzp_fanmian, chizhao_zhengmian, chizhao_fanmian;
    private String trim;
    private TextView ssqy,nianyueri,xingbie,baocun;
    private ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfrz);
        sfrz = getIntent().getStringExtra("sfrz");
        imagePicker = MyApplicaiton.getImagePicker();
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", SFRZ.this);
        Log.e(TAG, "onCreate: "+token );
        TongMing();
        initView();
       // initLianWang();

    }

    private void initLianWang() {
        Map<String, String> map = new HashMap<>();
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
                mess.what = 100;
                hand.sendMessage(mess);
            }
        });

    }

    private void initView() {

        TextView biaoti = (TextView) findViewById(R.id.title_text);
        zjzp_zhengmian = (ImageView) findViewById(R.id.zjzp_zhengmian);
        zjzp_fanmian = (ImageView) findViewById(R.id.zjzp_fanmian);
        chizhao_zhengmian = (ImageView) findViewById(R.id.zjzp_chizhao_zhenmian);
        chizhao_fanmian = (ImageView) findViewById(R.id.zjzp_chizhao_fanmian);
        sfz = (EditText) findViewById(R.id.jbxx_sfz);
        xingming = (EditText) findViewById(R.id.jbxx_name);
        back = (ImageView) findViewById(R.id.sfrz_back);
        ssqy = (TextView) findViewById(R.id.jbxx_ssdq);
        nianyueri = (TextView) findViewById(R.id.jbxx_nianyueri);
        xingbie = (TextView) findViewById(R.id.jbxx_xingbie);
        baocun = (TextView) findViewById(R.id.sfrz_baocun);

        baocun.setOnClickListener(this);
//        zjzp_text = (TextView) findViewById(R.id.sfrz_zjzp_text);
//        img = (ImageView) findViewById(R.id.sfrz_img);
//        renzhen = (TextView) findViewById(R.id.sfrz_text);
//        biaoti.setText(sfrz);
//        img.setOnClickListener(this);
//        zjzp.setOnClickListener(this);
//        back.setOnClickListener(this);
//        jbxx.setOnClickListener(this);
        //Glide.with(SFRZ.this).load("http://192.168.1.111:1003/uploads/portrait/"+portrait).load(img);
        zjzp_zhengmian.setOnClickListener(this);
        zjzp_fanmian.setOnClickListener(this);
        chizhao_zhengmian.setOnClickListener(this);
        chizhao_fanmian.setOnClickListener(this);
        back.setOnClickListener(this);
        sfz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==18){
                    trim = sfz.getText().toString().trim();
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
                            mess.what = 500;
                            hand.sendMessage(mess);

                        }
                    });
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.sfrz_zjzp:
//                if (realStatus.equals("1")){
//                    startActivity(new Intent(SFRZ.this,ZJZP_WanShan.class));
//                }else {
//
//                    Intent intent = new Intent(SFRZ.this, ZJZP.class);
//                    intent.putExtra("zjzp","证件照片");
//                    startActivity(new Intent(intent));
//                }
//
//                break;
            case R.id.sfrz_back:
                finish();
                break;
            case R.id.sfrz_baocun:
                String string = xingming.getText().toString();
                if (string.equals("")){
                    ToSi.show(SFRZ.this,"请输入姓名");
                }else if ("".equals(xingbie.getText().toString().trim())||"".equals(nianyueri.getText().toString().trim())||"".equals(ssqy.getText().toString().trim())){
                    ToSi.show(SFRZ.this,"请输入身份证");
                }else if (sfz.getText().length()<18){
                    ToSi.show(SFRZ.this,"身份证位数不足");
                }else if (zjzp_zhengmian.getDrawable().getCurrent().getConstantState()==getResources().getDrawable(R.mipmap.sfzm).getConstantState()){
                    ToSi.show(SFRZ.this,"请上传身份证正面照");
                }else if (zjzp_fanmian.getDrawable().getCurrent().getConstantState()==getResources().getDrawable(R.mipmap.sfzfm).getConstantState()){
                    ToSi.show(SFRZ.this,"请上传身份证反面照");
                }



                else {
                    ToSi.show(SFRZ.this,"正确");
                }



                break;
            case R.id.zjzp_zhengmian:
                tag = 1;
                List<String> name1 = new ArrayList<>();
                name1.add("拍照");
                name1.add("相册");
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
                                Intent intent = new Intent(SFRZ.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(SFRZ.this, ImageGridActivity.class);
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
                }, name1);
                break;
            case R.id.zjzp_fanmian:
                tag = 2;
                List<String> name2 = new ArrayList<>();
                name2.add("拍照");
                name2.add("相册");
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
                                Intent intent = new Intent(SFRZ.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(SFRZ.this, ImageGridActivity.class);
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
                }, name2);
                break;
            case R.id.zjzp_chizhao_zhenmian:
                tag= 3;
                List<String> name3 = new ArrayList<>();
                name3.add("拍照");
                name3.add("相册");
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
                                Intent intent = new Intent(SFRZ.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(SFRZ.this, ImageGridActivity.class);
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
                }, name3);
                break;
            case R.id.zjzp_chizhao_fanmian:
                tag= 4;
                List<String> name4 = new ArrayList<>();
                name4.add("拍照");
                name4.add("相册");
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
                                Intent intent = new Intent(SFRZ.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(SFRZ.this, ImageGridActivity.class);
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
                }, name4);
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
                        Log.e(TAG, "onActivityResult: map===11111"+map );
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

                        Log.e(TAG, "onActivityResult: " + item.path);
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
        if (tag ==2) {
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
                        Log.e(TAG, "onActivityResult: map===22222"+map );
                        OkUtils.UploadFileCS(WangZhi.ZHAOPIAN, "identityBack", item.path, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
//                            Log.e(TAG, "onResponse: "+response.body().string() );
                                String string = response.body().string();
                                Message message = hand.obtainMessage();
                                message.what = 200;
                                message.obj = string;
                                hand.sendMessage(message);
                            }
                        });

                        Log.e(TAG, "onActivityResult: " + item.path);
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
        if (tag == 3) {
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
                        Log.e(TAG, "onActivityResult: map==="+map );
                        OkUtils.UploadFileCS(WangZhi.ZHAOPIAN, "portrait", item.path, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
//                            Log.e(TAG, "onResponse: "+response.body().string() );
                                String string = response.body().string();
                                Message message = hand.obtainMessage();
                                message.what = 300;
                                message.obj = string;
                                hand.sendMessage(message);
                            }
                        });

                        Log.e(TAG, "onActivityResult: " + item.path);
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
        if (tag == 4) {
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
                        OkUtils.UploadFileCS(WangZhi.ZHAOPIAN, "portrait", item.path, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                                Message message = hand.obtainMessage();
                                message.what = 400;
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
            //身份证验证
            if (msg.what == 500) {
                String obj = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(obj);
                    String msg1 = jsonObject.getString("msg");
                    String error = jsonObject.getString("error");
                    Log.e(TAG, "handleMessage: sssssss" + error);
                    if (msg1.equals("没有信息")){
                        ToSi.show(SFRZ.this,"身份证不正确");
                    } else if (error.equals("0")) {
                        String replace = msg1.replace("所属区域: ", "");
                        ssqy.setText(replace);
                        String substring2 = trim.substring(6, 14);
                        nianyueri.setText(substring2);
                        String sCardNum = trim.substring(16, 17);
                        if (Integer.parseInt(sCardNum) % 2 != 0) {
                            xingbie.setText("男");//男
                        } else {
                            xingbie.setText("女");//女
                        }
                    } else {
                        ToSi.show(SFRZ.this, "请输入有效身份证");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //手持身份证照片上传反面
            if (msg.what == 400) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage:手持身份证反面 " + jsonObject);
                    String data = jsonObject.getString("success");
                    if (data.equals("true")) {
                        JSONObject msg1 = jsonObject.getJSONObject("msg");
                        ToSi.show(SFRZ.this, "上传成功");
                        ImagePicker.getInstance().getImageLoader().displayImage(SFRZ.this, item.path, chizhao_fanmian, 0, 0);
                    } else {
                        JSONObject data1 = jsonObject.getJSONObject("data");
                        String msg1 = data1.getString("msg");
                        ToSi.show(SFRZ.this, msg1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //手持身份证图片上传正面
            if (msg.what == 300) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage: 手持身份证正面" + jsonObject);
                    String data = jsonObject.getString("success");
                    if (data.equals("true")) {
                        JSONObject msg1 = jsonObject.getJSONObject("msg");
                        ToSi.show(SFRZ.this, "上传成功");
                        ImagePicker.getInstance().getImageLoader().displayImage(SFRZ.this, item.path,chizhao_zhengmian , 0, 0);
                    } else {
                        JSONObject data1 = jsonObject.getJSONObject("data");
                        String msg1 = data1.getString("msg");
                        ToSi.show(SFRZ.this, msg1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //图片上传证件照片反面
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage: 身份证反面" + jsonObject);
                    String data = jsonObject.getString("success");
                    if (data.equals("true")) {
                        JSONObject msg1 = jsonObject.getJSONObject("msg");
                        ToSi.show(SFRZ.this, "上传成功");
                        ImagePicker.getInstance().getImageLoader().displayImage(SFRZ.this, item.path, zjzp_fanmian, 0, 0);
                    } else {
                        JSONObject data1 = jsonObject.getJSONObject("data");
                        String msg1 = data1.getString("msg");
                        ToSi.show(SFRZ.this, msg1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //证件照片上传正面
            if (msg.what == 100) {

                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage: 身份证正面" + jsonObject);
                    String data = jsonObject.getString("success");
                    if (data.equals("true")) {
                        JSONObject msg1 = jsonObject.getJSONObject("msg");
                        ToSi.show(SFRZ.this, "上传成功");
                        ImagePicker.getInstance().getImageLoader().displayImage(SFRZ.this, item.path, zjzp_zhengmian, 0, 0);
                    } else {
                        JSONObject data1 = jsonObject.getJSONObject("data");
                        String msg1 = data1.getString("msg");
                        ToSi.show(SFRZ.this, msg1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                String str = (String) msg.obj;
//                try {
//                    JSONObject jsonObject = new JSONObject(str);
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    JSONObject msg1 = data.getJSONObject("msg");
//                    realStatus = msg1.getString("realStatus");
//                    String portrait = msg1.getString("portrait");
//                    identityBack = msg1.getString("identityBack");
//                    identityFace = msg1.getString("identityFace");
//                    Log.e(TAG, "handleMessage: fanmian" + identityBack);
//                    Log.e(TAG, "handleMessage: zhengmian" + identityFace);
//
////                    //TODO 判断是否有照片并且是否实名认证
////                    if ((identityBack.equals("null")&& identityFace.equals("null"))||realStatus.equals("0")){
////                        zjzp_text.setText("未完善");
////                    }else {
////                        zjzp_text.setText("已完善");
////                    }
////                    if (realStatus.equals("0")){
////                        renzhen.setText("未实名认证");
////                    }else if (realStatus.equals("1")){
////                        renzhen.setText("已实名认证");
////                    }
////                    Glide.with(SFRZ.this).load(WangZhi.TUPIAN+portrait).apply(RequestOptions.bitmapTransform(new CropCircleTransformation())).into(img);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }
    };

    //设置状态栏
    public void TongMing() {
        //如果手机有虚拟按键 那么不能添加透明状态栏
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //   tintManager.setStatusBarTintResource(R.color.zhuangtailan);
        tintManager.setTintColor(Color.parseColor("#000000"));

    }
}