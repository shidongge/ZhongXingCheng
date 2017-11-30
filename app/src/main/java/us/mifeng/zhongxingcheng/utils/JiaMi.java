package us.mifeng.zhongxingcheng.utils;

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
}
