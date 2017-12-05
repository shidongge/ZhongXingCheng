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

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.app.MyApplicaiton;
import us.mifeng.zhongxingcheng.utils.FileSizeUtil;
import us.mifeng.zhongxingcheng.utils.JiaMi;
import us.mifeng.zhongxingcheng.utils.OkUtils;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.utils.WangZhi;
import us.mifeng.zhongxingcheng.zhaopian.SelectDialog;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by shido on 2017/10/30.
 */

/***
 * 个人信息界面
 */
public class GRXX extends Activity implements View.OnClickListener {
    private static final String TAG = "GRXX";
    private TextView nc, js, phone, zsxm, diqu, zhiye, shouru, aihao, zxjf;
    private TextView bt;
    private String grzx;
    private String token;
    private ImageView img, back, sznc;
    private ImagePicker imagePicker;
    private int maxImgCount = 1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImageItem item;
    private ArrayList<ImageItem> selImageList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grxx);
        grzx = getIntent().getStringExtra("grzx");
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", GRXX.this);
        imagePicker = MyApplicaiton.getImagePicker();
        initView();
        initLianWang();
    }

    private void initLianWang() {
        String s = JiaMi.jdkBase64Encoder("mobile,userExp,realName,nickName,province,city,job,income,hobby,portrait");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("field", s);
        OkUtils.UploadSJ(WangZhi.GRXX, map, new Callback() {
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

    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("userInfo");
                    String realName = msg1.getString("realName");
                    String mobile = msg1.getString("mobile");
                    String city = msg1.getString("city");
                    String job = msg1.getString("job");
                    String income = msg1.getString("income");
                    String hobby = msg1.getString("hobby");
                    String nickName = msg1.getString("nickName");
                    String userExp = msg1.getString("userExp");
                    String portrait = msg1.getString("portrait");
                    String province = msg1.getString("province");
                    //TODO 隐藏手机号中间四位
                    String mobile2 = mobile;
                    String maskNumber = mobile.substring(0, 3) + "****" + mobile2.substring(7, mobile2.length());
                    phone.setText(maskNumber);
                    if ("".equals(job)) {
                        zhiye.setText("未设置");
                    } else {
                        zhiye.setText(job);
                    }
                    if ("".equals(realName)) {
                        zsxm.setText("未设置");
                    } else {
                        zsxm.setText(realName);
                    }
                    if ("null".equals(portrait)) {
                        diqu.setText("未设置");
                    } else {
                        if ("null".equals(city)) {
                            diqu.setText("未设置");
                        } else {
                            diqu.setText(province + city);
                        }
                    }
                    if ("".equals(income)) {
                        shouru.setText("未设置");
                    } else {

                        shouru.setText(income);
                    }
                    if ("".equals(hobby)) {
                        aihao.setText("未设置");
                    } else {

                        aihao.setText(hobby);
                    }
                    if ("".equals(nickName)) {
                        nc.setText("未设置");
                    } else {

                        nc.setText(nickName);
                    }
                    if ("".equals(userExp)) {
                        zxjf.setText("未设置");
                    } else {
                        zxjf.setText(userExp);
                    }
                    if ("".equals(portrait)) {
                        img.setImageResource(R.mipmap.tx);
                    } else {
                        Glide.with(GRXX.this).load(WangZhi.TUPIAN + portrait).apply(bitmapTransform(new CropCircleTransformation())).into(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (msg.what==100){
               String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String success = jsonObject.getString("success");
                    if ("true".equals(success)){

                        JSONObject data = jsonObject.getJSONObject("data");
                        String msg1 = data.getString("msg");
                        if ("0".equals(msg1)){
                            ToSi.show(GRXX.this,"上传成功");
                            ImagePicker.getInstance().getImageLoader().displayImage(GRXX.this, item.path, img, 0, 0);
                        }
                    }else {
                        ToSi.show(GRXX.this,"上传失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private void initView() {
        nc = (TextView) findViewById(R.id.grxx_nc);
        js = (TextView) findViewById(R.id.grxx_js);
        phone = (TextView) findViewById(R.id.grxx_phone);
        zsxm = (TextView) findViewById(R.id.grxx_zsxm);
        diqu = (TextView) findViewById(R.id.grxx_diqu);
        zhiye = (TextView) findViewById(R.id.grxx_zhiye);
        shouru = (TextView) findViewById(R.id.grxx_shouru);
        aihao = (TextView) findViewById(R.id.grxx_aihao);
        bt = (TextView) findViewById(R.id.grzx_bt);
        img = (ImageView) findViewById(R.id.grxx_img);
        back = (ImageView) findViewById(R.id.grxx_back);
        zxjf = (TextView) findViewById(R.id.grxx_zxjf);
//        nc.setOnClickListener(this);
        sznc = (ImageView) findViewById(R.id.grxx_sznc);
        js.setOnClickListener(this);
        bt.setOnClickListener(this);
        back.setOnClickListener(this);
        sznc.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grxx_sznc:
                Intent intent1 = new Intent(GRXX.this, NinChen.class);
                intent1.putExtra("tag", "1");
                startActivity(intent1);
                break;
            case R.id.grxx_js:
                startActivity(new Intent(GRXX.this, JieShao.class));
                break;
            case R.id.grzx_bt:
                Intent intent = new Intent(GRXX.this, GRZL.class);
                intent.putExtra("grxx", "个人资料");
                startActivity(intent);
                break;
            case R.id.grxx_back:
                finish();
                break;
            case R.id.grxx_img:
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
                                Intent intent = new Intent(GRXX.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(GRXX.this, ImageGridActivity.class);
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
            default:
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
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    item = images.get(0);
                    // TODO 把图片设置到控件上
//                    ImagePicker.getInstance().getImageLoader().displayImage(this, item.path, img, 0, 0);
                    //TODO 判断图片是否过大
                    double fileOrFilesSize = FileSizeUtil.getFileOrFilesSize(item.path, FileSizeUtil.SIZETYPE_B);
                    if (fileOrFilesSize > 5242880) {
                        ToSi.show(GRXX.this, "图片过大");
                    } else {
                        //TODO 图片上传
                        HashMap<String, String> map = new HashMap<>();
                        map.put("token", token);
                        OkUtils.UploadFileCS(WangZhi.GXJRXX, "portrait", item.path, map, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
//                                Log.e(TAG, "onResponse: "+response.body().string() );
                                String string = response.body().string();
                                Message message = hand.obtainMessage();
                                message.what = 100;
                                message.obj = string;
                                hand.sendMessage(message);
                            }
                        });
                    }
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

    @Override
    protected void onRestart() {
        initLianWang();
        super.onRestart();
    }
}
