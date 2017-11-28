package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.qcloud.presentation.business.LoginBusiness;
import com.tencent.qcloud.presentation.event.MessageEvent;
import com.tencent.qcloud.tlslibrary.activity.IndependentLoginActivity;
import com.tencent.qcloud.tlslibrary.service.TlsBusiness;

import org.greenrobot.eventbus.EventBus;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.liaotian.model.FriendshipInfo;
import us.mifeng.zhongxingcheng.liaotian.model.GroupInfo;
import us.mifeng.zhongxingcheng.liaotian.model.UserInfo;
import us.mifeng.zhongxingcheng.utils.FirstEvent;
import us.mifeng.zhongxingcheng.utils.SharedUtils;

/**
 * Created by shido on 2017/11/1.
 */

/**
 * 未用设置界面
 */
public class SheZhi extends Activity implements View.OnClickListener {

    private SharedUtils sharedUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedUtils = new SharedUtils();

        setContentView(R.layout.activity_shezhi);
        initView();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.shezhi_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shezhi_button:
                LoginBusiness.logout(new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        if (this != null) {
                            Toast.makeText(SheZhi.this, getResources().getString(R.string.setting_logout_fail), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        if (this != null) {
                            sharedUtils.saveShared("id","", SheZhi.this);
                            logout();
                            removeCookie(SheZhi.this);
                            sharedUtils.saveToken("token","",SheZhi.this);
                            sharedUtils.saveToken("session","",SheZhi.this);
                            sharedUtils.saveShared("token","",SheZhi.this);
                            sharedUtils.saveShared("session","",SheZhi.this);
                        }
                    }
                });

//                SharedUtils shared = new SharedUtils();
//                shared.saveShared("id","",SheZhi.this);
//                shared.saveShared("token","",SheZhi.this);
//                shared.saveShared("session","",SheZhi.this);
//                startActivity(new Intent(SheZhi.this, IndependentLoginActivity.class));
//                finish();
                break;
        }
    }
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
    public void logout() {

        EventBus.getDefault().post(new FirstEvent("这是要发送的内容"));

        TlsBusiness.logout(UserInfo.getInstance().getId());
        UserInfo.getInstance().setId(null);
        MessageEvent.getInstance().clear();
        FriendshipInfo.getInstance().clear();
        GroupInfo.getInstance().clear();
        Intent intent = new Intent(SheZhi.this, IndependentLoginActivity.class);
        finish();
        startActivity(intent);

    }
}
