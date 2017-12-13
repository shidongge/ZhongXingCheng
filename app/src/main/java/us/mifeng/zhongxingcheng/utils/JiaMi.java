package us.mifeng.zhongxingcheng.utils;

import android.text.TextUtils;

import sun.misc.BASE64Encoder;

/**
 * Created by shido on 2017/11/30.
 */

public class JiaMi {
    public static String jdkBase64Encoder(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode1 = encoder.encode(str.getBytes());
        String encode2 = encoder.encode(encode1.getBytes());
        String encode3 = encoder.encode(encode2.getBytes());
        return encode3;

    }
    public static boolean isMobileNO(String mobiles) {

        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }
        else return mobiles.matches(telRegex);
    }
}
