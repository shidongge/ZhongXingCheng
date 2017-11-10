package us.mifeng.zhongxingcheng.upload;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by user on 2017/10/16.
 */

public class PermissionUtils {
    public int PERMISSION_REQUEST_CODE;
    public IPermissionSix iPermissionSix;

    public PermissionUtils(IPermissionSix iPermissionSix) {
        this.iPermissionSix = iPermissionSix;
    }

    public void requestPermission(Activity activity, String permissionStr, int code) {
        int permission = ContextCompat.checkSelfPermission(activity, permissionStr);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            //权限开启
            iPermissionSix.onPermissionListener();
        } else {
            //..申请授权
            PERMISSION_REQUEST_CODE = code;
            ActivityCompat.requestPermissions(activity,
                    new String[]{permissionStr},
                    PERMISSION_REQUEST_CODE);

        }
    }
}
