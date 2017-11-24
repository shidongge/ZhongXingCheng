package us.mifeng.zhongxingcheng.activity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.bill.sdk.util.MbpSecurityUtil;
import com.bill.sdk.util.SignUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SignUtils2 {

	/**
	 * <p>isv接口加签字符串拼接</p>
	 *
	 * @param jsonStr 入参json字符串
	 * @return 拼接后的字符串
	 * @author: 余浩 - hao.yu@99bill.com
	 * @date: Created on 2017-8-22 上午11:11:22
	 */
	public static String formatIsvData(String jsonStr) {
		// 转成json对象，剔除sign
		JSONObject jsonObject = JSONObject.parseObject(jsonStr, Feature.OrderedField);
		if (jsonObject.containsKey("sign")) {
			jsonObject.remove("sign");
		}

		// key排序，升序
		ArrayList<String> keys = new ArrayList<String>();
		Iterator<String> iterator = jsonObject.keySet().iterator();
		while (iterator.hasNext()) {
			String sb = iterator.next();
			if (StringUtils.isNotBlank(sb)) {
				keys.add(sb);
			}
		}
		Collections.sort(keys);

		// 组装字符串
		StringBuilder builder = new StringBuilder();
		for (String key : keys) {
			String value = jsonObject.getString(key);
			builder.append(key).append("=").append(value).append("&");
		}
		String result = builder.toString();
		if (result.length() > 0) {
			result = result.substring(0, builder.length() - 1);
		}
		
		return digestString(result.getBytes());
	}
	
	private static String digestString(byte[] data) {
		return toHexString(digest(data));
	}

	private static String toHexString(byte[] byteStr) {
		StringBuilder result = new StringBuilder(byteStr.length * 2);
		for (int i = 0; i < byteStr.length; i++) {
			result.append("0123456789ABCDEF".charAt((byteStr[i] & 0xF0) >> 4));
			result.append("0123456789ABCDEF".charAt((byteStr[i] & 0x0F) >> 0));
		}
		return result.toString();
	}

	private static byte[] digest(byte[] data) {
		byte[] result = null;

		try {
			MessageDigest var3 = MessageDigest.getInstance("MD5");
			var3.update(data);
			result = var3.digest();
		} catch (NoSuchAlgorithmException var31) {
			var31.printStackTrace();
		}

		return result;
	}

	/**
	 * <p>加签</p>
	 *
	 * @param obj 请求对象
	 * @param merchantPrivateKey 商户私钥
	 * @return 签值
	 * @throws Exception
	 * @author: 余浩 - hao.yu@99bill.com
	 * @date: Created on 2017-8-30 下午3:51:35
	 */
	public static String addSign(String  signData, String merchantPrivateKey) throws Exception {
		//String signData = formatIsvData(JSON.toJSONString(obj));
		byte[] originalData = signData.getBytes();
		// 用私钥加签
		PrivateKey key = null;
		key = MbpSecurityUtil.convert2PrivateKey(merchantPrivateKey);
		byte[] resultBytes = MbpSecurityUtil.sign(key, originalData, null);

		return new String(Base64.encodeBase64(resultBytes));
	}
	public static void main(String[] args) {


			String signMsg="inputCharset=1&bgUrl=http://www.baidu.com&version=mobile1.0&language=1&signType=4&merchantAcctId=1020996764101&payerIdType=3&payerId=18994659500&orderId=20171117172953&orderAmount=1000&orderTime=20171117172953&payType=00";
			String sign = SignUtils2.signMsg(signMsg);

	}
	public static String signMsg(String signMsg){
		System.out.println("signMsg="+signMsg);
		String result;
		try {
			String pki="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKv+9u3j0kFVVZRtglikVTDX8sz6OVJDw0hSZHedO5+fMAPHgn7LEq0qk4MzWtPVfauba256JXuA2DpomtOFrRqLuxJEvW1gzOSTLgVHAttRfYhJ7KF4S/0/fn72SL6HINHv7xqMSqQC4AtfrijgbjRwfS3W1NZlQgpoE/AN6aEFAgMBAAECgYEAhKv628vlrsHdbs3+QtQYZsHdJ6JIvx1IMKxllSLzEBDiH7gxAA8zS+JQwV7GzCqdctdDSofsC9V6dauk6k7uFA1FGSGVB8kapYFSw13RUj50dVGi3F/eA4lN/RpEA4Xm1+TsQeWC/WmnW9PQbi+Rxmozc6mN0ojCm6Rc74zo/A0CQQDSYXBin2QYT2ucqY7JevAx5sDrJSyVQwGO8Y8Hqe2o9B9MESm17RPk37TJUuC5sffWkgj1QWCYC6fgHb+gTV+TAkEA0Uq7aEwPn2LT6Ef8M769rNm9uQyHhhLLjgbSBdvDGWvkdK4Oe69lv6KOQ9pKtWiN6KjCyVo8kgtF6NkeAt5sBwJAZoSU/3osjKwnBHCb5BLEeYy49d1nnFTKrZ2I1XM5HNvZZHf4m26sAxwAPRrl55eR7j27n8f8Chuj8tKMTtFlgQJATR9yy84re5pZaCEOqKrDDmz2YrhhQGCwrdeJkSsYS8fcWbrCD4XkwqdOMWbBXPP4RyHZFYWxCEgrLNDFJF1+BQJAPJ65F0yWOisn9VC2jxnyKc8TuITuDSv7dSCy3QInhn+MR2/02a3BDx284WvXPC9ZjV2pfROMs+KepVOP0nvxEQ==";
			result=SignUtils.addSign(signMsg, pki);
			System.out.println("sign="+result);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("signMsg不正确");
		}
		return null;
		
	}
	/**
	 * <p>验签</p>
	 *
	 * @param responseJsonString 返回的json报文
	 * @param sign 签值
	 * @param kuaiqianPublicKey 快钱公钥
	 * @return true表示验签通过，false表示不通过
	 * @throws Exception
	 * @author: 余浩 - hao.yu@99bill.com
	 * @date: Created on 2017-8-30 下午3:52:14
	 */
	public static boolean checkSign(String responseJsonString, String sign, String kuaiqianPublicKey) throws Exception {
		String signData = formatIsvData(responseJsonString);
		byte[] originalData = signData.getBytes();

		boolean tag = false;
		tag = MbpSecurityUtil.verifySign(MbpSecurityUtil.convert2PublicKey(kuaiqianPublicKey),
				Base64.decodeBase64(sign.getBytes()), originalData, null);
		return tag;
	}
}
