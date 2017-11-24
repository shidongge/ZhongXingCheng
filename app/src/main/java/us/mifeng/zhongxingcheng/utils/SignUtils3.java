package us.mifeng.zhongxingcheng.utils;

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

import static com.xiaomi.push.service.z.z;

public class SignUtils3 {

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

		String xxx = "inputCharset=1&bgUrl=http://www.baidu.com&version=mobile1.0&language=1&signType=4&merchantAcctId=1020996764101&payerIdType=3&payerId=18994659500&orderId=20171117172953&orderAmount=1000&orderTime=20171117172953&payType=21-1";
			String sign = SignUtils3.signMsg(xxx);
			System.out.print("123456"+sign);
	}
	public static String signMsg(String signMsg){
		System.out.println("signMsg="+signMsg);
		String result;
		try {
			String pki="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMnSK1b215Q97xczpguuk6pRXPynJ+eUSnAXFeTN1rORpmgXlNfAVaYI7Uolxh8ciKjvL9HKYQGZGjWXhc+4YIIjJgUSrBw1/VwczDQKc075xYUxZjgNTvr90jsC/jLKWjuSZxm0wApg+dbAj9uGPnvIkdCth+p7Gr1nGcvYmj45AgMBAAECgYEAgsYeszErq7EeFeivtW4f3QLvv/8ISnS6bztmQmZmsuCz9UsidVL5rKaoK52ibVJt8xlZ4RTEGub8JrHSUTDlxwW84jdsN43omKFCyzkFJtrnQdn8BLcfCnYdPFTUHy5sxRtE9gpA2u+WDQvzIQlk06CHXs334D5OiP7uhiHnQWkCQQD7BFhq2mtedKjfEwioDHD17FsLje8zIym3jBW/xehTL9XedQxKLyyqVNxdbL+2TlL0f+SD8CJP/55aAoEAQqSvAkEAzdPQAQMRtGlyKO3RdF0y0lGVG4BBHfSWlmqvzkd4X8ahgQFqtCeUawfpXPmgrIpby8TcLeSeNb9QTlUsSnNVlwJBANpvD5n8dA6AKivk3lWObgYzJY9KderkIHtO9eYqDF/Hcq7g4E71+kJHEnRiG/pC8kE9wddmchV3dY8SOrvFQpUCQAqjJr3WpcSYKoE5ATktU+IQu08HitX8fQfbDajwgqUYg+JB63zSCtaZOxNGB2rDX8khLGvmw9JtR740oqxaV5MCQQDdVPRb22xx4M/T+PYhT5TbizEekrZ1eAiGAzmy6SRoR4FvADyfeHJhVL6e51NXhbW27LJRbWVNUNPgAgL+nDv6";
			result=SignUtils.addSign(signMsg, pki);
			System.out.println("sign="+result);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("signMsg不正确ȷ");
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
