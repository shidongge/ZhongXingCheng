package com.bill.sdk.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SignUtils {

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
		// ��˽Կ��ǩ
		PrivateKey key = null;
		key = MbpSecurityUtil.convert2PrivateKey(merchantPrivateKey);
		byte[] resultBytes = MbpSecurityUtil.sign(key, originalData, null);

		return new String(Base64.encodeBase64(resultBytes));
	}
	public static void main(String[] args) {
		try {
			
			String sign =SignUtils.addSign("882D4FD37BA11DE835C6184C5259FE70", "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANxuggJLcmEi33AINVhc9l12Mu/FRpn7nq6AG0oUZJsstN4YWlexZWeE59QnmrxF8EJh9i5YOoUvThFJxYVp0iz6ZKAtiD4/hw2rORSvRcWro5SLRyp9E1zEx4eYYszwXMAn9tmKKV9MuYel6rGwjhCSmD7Bcm7ynAbVKWl6UdknAgMBAAECgYEAlbfVQ/u8n29RNcXNfcv+ZbKfnC1w66t0tu86GdHKF3+BBYyi6AcMaghicb3IonIaYuwoiofQj592tdVWfGxInJMety3Dk5c4BLBE6Ww6pZwMfVK7jtM0wki2beOMewMEBqWdN0pF3vQGGL0Udx+9LRPmmDPj3j9BVmzBGKXxBVECQQD4RnGcAsojYnlnBJS8urMM6Tjm0+19fhbIE3qPzdpicvyFqo7I9+ccbDlT/gz4/tzNAdtdXqL/hR0lanQzOEd/AkEA40pJHOa81BD08+eEVyojbTRU3z0mxYiZ0BftZBSFhA40jcABWaYkLrtMMmPIWwiTOobsQOWzWe2GF/ttzc4CWQJBAKcF9P4onkYpxFR6r3k7/KKN75rSLe4Dul3FROmjaexbQOjHciFfLRpg8DxCKAYGpxI/0ozA0tUlfxMhx6SBkw8CQDFWZLyDUaXu8F0pek65dOs1mt7ClXH3jvve1ccNtVXPB5VlgmWFbQE/4vBlPI6WM+9Wbl4//kidCKiYCWPNPjECQQDlKrczh/eYkiC0cIvuOb34a6EeKuYzRQcSSEj0pLnqG0lzZcD7B5wbYsyf8SU78Wu/VHjz4imzXWeuFWYtxwN1");
			System.out.println("hahhaa"+sign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
