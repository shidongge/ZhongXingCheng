package us.mifeng.zhongxingcheng.denlgu;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendAllowType;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.qcloud.presentation.business.LoginBusiness;
import com.tencent.qcloud.presentation.event.MessageEvent;
import com.tencent.qcloud.presentation.presenter.FriendshipManagerPresenter;
import com.tencent.qcloud.presentation.viewfeatures.FriendInfoView;
import com.tencent.qcloud.tlslibrary.service.TlsBusiness;
import com.tencent.qcloud.tlslibrary.utils.SharedUtils;
import com.tencent.qcloud.ui.LineControllerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.GuanYu;
import us.mifeng.zhongxingcheng.liaotian.BlackListActivity;
import us.mifeng.zhongxingcheng.liaotian.EditActivity;
import us.mifeng.zhongxingcheng.liaotian.SplashActivity;
import us.mifeng.zhongxingcheng.liaotian.model.FriendshipInfo;
import us.mifeng.zhongxingcheng.liaotian.model.GroupInfo;
import us.mifeng.zhongxingcheng.liaotian.model.UserInfo;
import us.mifeng.zhongxingcheng.utils.TuiChuEvent;
import us.mifeng.zhongxingcheng.utils.WangZhi;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * 设置页面
 */
public class SettingActivity extends AppCompatActivity implements FriendInfoView, View.OnClickListener {

    private static final String TAG = SettingActivity.class.getSimpleName();
    private FriendshipManagerPresenter friendshipManagerPresenter;
    private TextView id, name;
    private LineControllerView nickName, friendConfirm;
    private final int REQ_CHANGE_NICK = 1000;
    private Map<String, TIMFriendAllowType> allowTypeContent;
    private FragmentManager fm;
    private SharedUtils sharedUtils;
    private ImageView img, back;
    private String tupian,nickName1;
    private LinearLayout hmd;
    private TextView about;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tupian = getIntent().getStringExtra("img");
        nickName1 = getIntent().getStringExtra("nickName");
        sharedUtils = new SharedUtils();
        id = (TextView) findViewById(R.id.idtext);
        name = (TextView) findViewById(R.id.name);
        img = (ImageView) findViewById(R.id.shezhi_img);
        name.setText(nickName1);

        hmd = (LinearLayout) findViewById(R.id.blackList);
        back = (ImageView) findViewById(R.id.sz_back);

        back.setOnClickListener(this);
        friendshipManagerPresenter = new FriendshipManagerPresenter(this);
        if ("".equals(tupian)) {
            img.setImageResource(R.mipmap.tx);
        } else {

            Glide.with(this).load(WangZhi.TUPIAN + tupian).apply(bitmapTransform(new CropCircleTransformation())).into(img);
        }
        friendshipManagerPresenter.getMyProfile();
        TextView logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBusiness.logout(new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        if (this != null) {
                            Toast.makeText(SettingActivity.this, getResources().getString(R.string.setting_logout_fail), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        if (this != null) {
                            sharedUtils.saveShared("id", "", SettingActivity.this);
                            logout();
                            removeCookie(SettingActivity.this);
                            SharedUtils sharedUtils = new SharedUtils();
                            sharedUtils.saveToken("token", "", SettingActivity.this);
                            sharedUtils.saveToken("session", "", SettingActivity.this);
                        }
                    }
                });
            }
        });
        about = (TextView) findViewById(R.id.about);
        about.setOnClickListener(this);

        this.nickName = (LineControllerView) findViewById(R.id.nickName);
        this.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.navToEdit(SettingActivity.this, getResources().getString(R.string.setting_nick_name_change), name.getText().toString(), REQ_CHANGE_NICK, new EditActivity.EditInterface() {
                    @Override
                    public void onEdit(String text, TIMCallBack callBack) {
                        FriendshipManagerPresenter.setMyNick(text, callBack);
                    }
                }, 20);
            }
        });
        //黑名单界面
        LineControllerView blackList = (LineControllerView) findViewById(R.id.blackList);
        blackList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, BlackListActivity.class);
                startActivity(intent);
//                    Intent intent = new Intent(getActivity(), TCVideoRecordActivity.class);
//                    startActivity(intent);
            }
        });
        allowTypeContent = new HashMap<>();
        allowTypeContent.put(getString(R.string.friend_allow_all), TIMFriendAllowType.TIM_FRIEND_ALLOW_ANY);
        allowTypeContent.put(getString(R.string.friend_need_confirm), TIMFriendAllowType.TIM_FRIEND_NEED_CONFIRM);
        allowTypeContent.put(getString(R.string.friend_refuse_all), TIMFriendAllowType.TIM_FRIEND_DENY_ANY);
        final String[] stringList = allowTypeContent.keySet().toArray(new String[allowTypeContent.size()]);
//        friendConfirm = (LineControllerView) findViewById(R.id.friendConfirm);
//        friendConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new ListPickerDialog().show(stringList, fm, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, final int which) {
//                        FriendshipManagerPresenter.setFriendAllowType(allowTypeContent.get(stringList[which]), new TIMCallBack() {
//                            @Override
//                            public void onError(int i, String s) {
//                                Toast.makeText(SettingActivity.this, getString(R.string.setting_friend_confirm_change_err), Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onSuccess() {
//                                friendConfirm.setContent(stringList[which]);
//                            }
//                        });
//                    }
//                });
//            }
//        });

    }


    public void logout() {
        EventBus.getDefault().post(new TuiChuEvent("这是要发送的内容"));

        TlsBusiness.logout(UserInfo.getInstance().getId());
        UserInfo.getInstance().setId(null);
        MessageEvent.getInstance().clear();
        FriendshipInfo.getInstance().clear();
        GroupInfo.getInstance().clear();
        Intent intent = new Intent(this, SplashActivity.class);
        this.finish();
        startActivity(intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CHANGE_NICK) {
            if (resultCode == this.RESULT_OK) {
//                setNickName(data.getStringExtra(EditActivity.RETURN_EXTRA));
            }
        }
    }

//    private void setNickName(String name) {
//        if (name == null) return;
//        this.name.setText(nickName1);
//        nickName.setContent(name);
//    }


    /**
     * 清除Cookie
     *
     * @param context
     */
    public static void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }


    /**
     * 显示用户信息
     *
     * @param users 资料列表
     */
    @Override
    public void showUserInfo(List<TIMUserProfile> users) {
        id.setText(users.get(0).getIdentifier());
//        setNickName(users.get(0).getNickName());
        for (String item : allowTypeContent.keySet()) {
            if (allowTypeContent.get(item) == users.get(0).getAllowType()) {
//                friendConfirm.setContent(item);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sz_back:
                finish();
                break;
            case R.id.about:
                startActivity(new Intent(SettingActivity.this, GuanYu.class));
                break;
            default:
                break;
        }
    }
}
