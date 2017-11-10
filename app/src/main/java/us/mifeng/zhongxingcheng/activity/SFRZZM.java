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
import android.widget.Button;
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
 * Created by shido on 2017/11/3.
 */
//身份认证正面
public class SFRZZM extends Activity implements View.OnClickListener {
    private static final String TAG = "SFRZZM";
    private String sfrzfm;
    private ImageView img;
    private int maxImgCount = 1;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> selImageList;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImageItem item;
    private String token;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfrzzm);
        imagePicker = MyApplicaiton.getImagePicker();
        SharedUtils sharedUtils = new SharedUtils();
        token = sharedUtils.getShared("token", SFRZZM.this);
        sfrzfm = getIntent().getStringExtra("sfrzzm");
        initView();
        initLianWang();
    }

    private void initLianWang() {
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        OkUtils.UploadSJ(WangZhi.WODE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message mes = hand.obtainMessage();
                mes.obj=string;
                mes.what=300;
                hand.sendMessage(mes);
            }
        });
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.title_back);
        TextView title = (TextView) findViewById(R.id.title_text);
        img = (ImageView) findViewById(R.id.sfrzzm_img);
        btn = (Button) findViewById(R.id.sfrzzm_mbtn);
        title.setText(sfrzfm);
        back.setOnClickListener(this);
        img.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
            case R.id.sfrzzm_img:
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
                                Intent intent = new Intent(SFRZZM.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                imagePicker.setSelectLimit(maxImgCount);
                                Intent intent1 = new Intent(SFRZZM.this, ImageGridActivity.class);
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
            case R.id.sfrzzm_mbtn:
                if (img.getDrawable()==null){
                    ToSi.show(SFRZZM.this,"请上传图片");
                }else {
                    HashMap<String,String> map =new HashMap<>();
                    map.put("token",token);
                    Log.e(TAG, "onClick: "+token );
                    String path = item.path;
                    Log.e(TAG, "onClick: "+path );
                    OkUtils.UploadFileCS(WangZhi.ZHAOPIAN,"identityFace",item.path, map, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "onFailure: "+e.getLocalizedMessage() );
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Message message = hand.obtainMessage();
                            message.what=200;
                            message.obj=string;
                            hand.sendMessage(message);
                        }
                    });
                }
                break;
        }
    }
    private String portrait;
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //上传图片
            if (msg.what==200){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Log.e(TAG, "handleMessage: "+jsonObject );
                    String data = jsonObject.getString("success");
                    if (data.equals("true")){
//                        JSONObject msg1 = jsonObject.getJSONObject("msg");
//                        portrait = msg1.getString("portrait");
                        ToSi.show(SFRZZM.this,"上传成功");

                    }else {
//                        JSONObject data1 = jsonObject.getJSONObject("data");
//                        String msg1 = data1.getString("msg");
                        ToSi.show(SFRZZM.this,"上传失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if (msg.what==300){
                String str = (String) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject msg1 = data.getJSONObject("msg");
                    String identityFace = msg1.getString("identityFace");
                    if (identityFace.equals("")){
                        btn.setVisibility(View.VISIBLE);
                    }else {
                        btn.setVisibility(View.GONE);
                        Glide.with(SFRZZM.this).load(WangZhi.TUPIAN+identityFace).into(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };


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
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    item = images.get(0);
                    // TODO 把图片设置到控件上
//                    ImagePicker.getInstance().getImageLoader().displayImage(this, item.path, img, 0, 0);
                    ImagePicker.getInstance().getImageLoader().displayImage(SFRZZM.this, item.path, img, 0, 0);

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