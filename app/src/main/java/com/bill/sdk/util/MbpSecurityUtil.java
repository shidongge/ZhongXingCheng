package com.bill.sdk.util;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author jame.cen
 * project mbpBBPOS
 * create_time 2012-3-16
 * update_time 2012-3-16
 */
public class MbpSecurityUtil {

	final public static String DEFAULT_KEYSTORE_ALGORITHM = "PKCS12";

	final public static String DEFAULT_SIGN_ALGORITHM = "SHA1withRSA";

	// private static String DEFAULT_CHARSET_NAME = "utf-8";

	static {
		Security.addProvider(new BouncyCastleProvider());
	}


	/**
	 * 从KeyStore文件中生成KeyStore对象
	 *
	 * @param keyStoreFile			keyStore文件
	 * @param storeAlgorithm		keyStore算法。缺省为：PKCS12
	 * @param storePassword		KeyStore密码
	 * @return					KeyStore实例
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @author: 胡磊 - lei.hu@99bill.com
	 * @date: Created on 2016年12月29日 上午10:11:31
	 */
	public static KeyStore getKeyStore(final InputStream keyStoreFile, final String storeAlgorithm,
			final String storePassword)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String storeAlgorithm1 = storeAlgorithm;
		if (storeAlgorithm1 == null) {
			storeAlgorithm1 = DEFAULT_KEYSTORE_ALGORITHM;
		}
		KeyStore ks = KeyStore.getInstance(storeAlgorithm);
		String pwd = StringUtils.defaultString(storePassword);
		ks.load(keyStoreFile, pwd.toCharArray());
		keyStoreFile.close();
		return ks;
	}

	/**
	 * 公钥或私钥加密
	 * @param data
	 * @param key
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key, String algorithm) throws Exception {
		Cipher cipher;
		if (algorithm == null) {
			cipher = Cipher.getInstance(key.getAlgorithm(), BouncyCastleProvider.PROVIDER_NAME);
		} else {
			cipher = Cipher.getInstance(algorithm, BouncyCastleProvider.PROVIDER_NAME);
		}
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// 获得加密块大小，如:加密前数据为128个byte，而key_size=1024
		// 加密块大小为127byte,加密后为128个byte;
		// 因此共有2个加密块，第一个127byte;第二个为1个byte
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(data.length); // ��ü��ܿ���ܺ���С
		int leavedSize = data.length % blockSize;
		int blocksSize = (leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize);
		byte[] raw = new byte[outputSize * blocksSize];
		int i = 0;
 		while (data.length - i * blockSize > 0) {
			// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中
			// 而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
				if (data.length - i * blockSize > blockSize) {
				cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);

			} else {
				cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
			}

			i++;
		}

		return raw;
	}
	public static void main(String[] args) throws Exception {
		PrivateKey key = null;
	    key = MbpSecurityUtil.convert2PrivateKey("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANxuggJLcmEi33AINVhc9l12Mu/FRpn7nq6AG0oUZJsstN4YWlexZWeE59QnmrxF8EJh9i5YOoUvThFJxYVp0iz6ZKAtiD4/hw2rORSvRcWro5SLRyp9E1zEx4eYYszwXMAn9tmKKV9MuYel6rGwjhCSmD7Bcm7ynAbVKWl6UdknAgMBAAECgYEAlbfVQ/u8n29RNcXNfcv+ZbKfnC1w66t0tu86GdHKF3+BBYyi6AcMaghicb3IonIaYuwoiofQj592tdVWfGxInJMety3Dk5c4BLBE6Ww6pZwMfVK7jtM0wki2beOMewMEBqWdN0pF3vQGGL0Udx+9LRPmmDPj3j9BVmzBGKXxBVECQQD4RnGcAsojYnlnBJS8urMM6Tjm0+19fhbIE3qPzdpicvyFqo7I9+ccbDlT/gz4/tzNAdtdXqL/hR0lanQzOEd/AkEA40pJHOa81BD08+eEVyojbTRU3z0mxYiZ0BftZBSFhA40jcABWaYkLrtMMmPIWwiTOobsQOWzWe2GF/ttzc4CWQJBAKcF9P4onkYpxFR6r3k7/KKN75rSLe4Dul3FROmjaexbQOjHciFfLRpg8DxCKAYGpxI/0ozA0tUlfxMhx6SBkw8CQDFWZLyDUaXu8F0pek65dOs1mt7ClXH3jvve1ccNtVXPB5VlgmWFbQE/4vBlPI6WM+9Wbl4//kidCKiYCWPNPjECQQDlKrczh/eYkiC0cIvuOb34a6EeKuYzRQcSSEj0pLnqG0lzZcD7B5wbYsyf8SU78Wu/VHjz4imzXWeuFWYtxwN1");
	    String data="B34B7097B3D1F40D839EFCF7426BA5DB";
	    String sign =encrypt(data, key);
	    System.out.println("ssssss"+sign);
	}
	/**
	 * 公钥或私钥加密
	 * @param data			待加密的字符串，明文
	 * @param key			密钥
	 * @param algorithm
	 * @return				返回Base64编码的密文
	 * @throws Exception
	 */
	public static String encrypt(String data, Key key, String algorithm) throws Exception {
		if (data == null) {
			return null;
		}
		// byte[] bytes = encrypt(data.getBytes(DEFAULT_CHARSET_NAME), key, algorithm);
		// byte[] bytes = encrypt(Utils.hexStringToByteArray(data), key, algorithm);
		byte[] bytes = encrypt(data.getBytes(), key, algorithm);

		// return Utils.toHexString(bytes);
		return new String(Base64.encode(bytes));
	}

	/**
	 * 公钥或私钥加密，默认采用算法："RSA/None/PKCS1Padding"
	 * @param data			待加密的字符串，明文
	 * @param key			密钥
	 * @return				返回Base64编码的密文
	 * @throws Exception
	 */
	public static String encrypt(String data, Key key) throws Exception {
		if (data == null) {
			return null;
		}
		byte[] bytes = encrypt(data.getBytes(), key, "RSA/None/PKCS1Padding");
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return new String(encoder.encode(bytes));
	}

	/**
	 * 公钥或私钥解密
	 * @param data
	 * @param key
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key, String algorithm) throws Exception {
		Cipher cipher;
		if (algorithm == null) {
			cipher = Cipher.getInstance(key.getAlgorithm(), BouncyCastleProvider.PROVIDER_NAME);
		} else {
			cipher = Cipher.getInstance(algorithm, BouncyCastleProvider.PROVIDER_NAME);
		}
		cipher.init(Cipher.DECRYPT_MODE, key);

		int blockSize = cipher.getBlockSize();
		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream(64);
			int j = 0;
			while (data.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(data, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} finally {
			if (bout != null) {
				IOUtils.closeQuietly(bout);
			}
		}
	}

	/**
	 * 公钥或私钥解密
	 * @param data			待解密的Base64密文
	 * @param key			密钥
	 * @param algorithm
	 * @return	返回明文
	 * @throws Exception
	 */
	public static String decrypt(String data, Key key, String algorithm) throws Exception {
		if (data == null) {
			return null;
		}
		// byte[] src = Utils.hexStringToByteArray(data);
		byte[] src = Base64.decode(data);
		byte[] dst = decrypt(src, key, algorithm);
		// String result = new String(dst, DEFAULT_CHARSET_NAME);

		// return Utils.toHexString(dst);
		return new String(dst);
	}

	/**
	 * 公钥或私钥解密。默认采用"RSA/None/PKCS1Padding"算法
	 *
	 * @param data	Base64加密的字符串
	 * @param key
	 * @return		返回明文
	 * @throws Exception
	 * @author: 胡磊 - lei.hu@99bill.com
	 * @date: Created on 2017年1月9日 上午10:04:04
	 */
	public static String decrypt(String data, Key key) throws Exception {
		if (data == null) {
			return null;
		}
		byte[] src = Base64.decode(data);
		byte[] dst = decrypt(src, key, "RSA/None/PKCS1Padding");

		return new String(dst);
	}

	/**
	 * 产生RSA密钥对
	 * @return
	 * @throws Exception
	 */
	public static KeyPair generateRSAKeyPair() throws Exception {
		KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
		kpGen.initialize(1024, new SecureRandom());

		return kpGen.generateKeyPair();
	}

	/**
	 * 签名
	 * @param key 私钥
	 * @param src 原文
	 * @param algorithm 签名算法(常用：md5withrsa, SHA1withRSA)，为空时默认为：SHA1withRSA
	 * @return 签名
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws Exception
	 */
	public static byte[] sign(final PrivateKey key, byte[] src, final String algorithm)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		String algorithm1 = algorithm;
		if (algorithm1 == null) {
			algorithm1 = DEFAULT_SIGN_ALGORITHM;
		}
		Signature sign = Signature.getInstance(algorithm1, BouncyCastleProvider.PROVIDER_NAME);

		sign.initSign(key);
		sign.update(src);

		return sign.sign();
	}

	/**
	 * 签名
	 * @param key 				私钥
	 * @param src 				原文，Base64
	 * @param algorithm 		签名算法(常用：md5withrsa, SHA1withRSA)，为空时默认为：SHA1withRSA
	 * @return 						签名 使用Base64编码
	 * @throws UnsupportedEncodingException
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static String sign(final PrivateKey key, String src, final String algorithm)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
			SignatureException {
		// byte[] srcBytes = src.getBytes(DEFAULT_CHARSET_NAME);
		// byte[] srcBytes = Utils.hexStringToByteArray(src);
		byte[] srcBytes = Base64.decode(src);
		byte[] resultBytes = sign(key, srcBytes, algorithm);

		// return Utils.toHexString(resultBytes);
		return new String(Base64.encode(resultBytes));
	}


	/**
	 * 校验签名
	 * @param key 				公钥
	 * @param crypt 			签名
	 * @param src 				原文
	 * @param algorithm 		签名算法(常用：md5withrsa, SHA1withRSA)，为空时默认为：SHA1withRSA
	 * @return
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static boolean verifySign(final PublicKey key, byte[] crypt, byte[] src, final String algorithm)
			throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
		String algorithm1 = algorithm;
		if (algorithm1 == null) {
			algorithm1 = DEFAULT_SIGN_ALGORITHM;
		}
		Signature sign = Signature.getInstance(algorithm1, BouncyCastleProvider.PROVIDER_NAME);
		sign.initVerify(key);
		sign.update(src);

		return sign.verify(crypt);
	}

	/**
	 * 校验签名
	 * @param key 			公钥
	 * @param crypt 		签名， 以Base64编码
	 * @param src 			原文， 以Base64编码
	 * @param algorithm 签名算法(常用：md5withrsa, SHA1withRSA)，为空时默认为：SHA1withRSA
	 * @return
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static boolean verifySign(final PublicKey key, String crypt, String src, final String algorithm)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException,
			UnsupportedEncodingException {
		// byte[] cryptBytes = Utils.hexStringToByteArray(crypt);
		// byte[] srcBytes = Utils.hexStringToByteArray(src);
		byte[] cryptBytes = Base64.decode(crypt);
		byte[] srcBytes = Base64.decode(src);

		return verifySign(key, cryptBytes, srcBytes, algorithm);
	}

	/**
	 * 将公钥或私钥使用16进制编码，返回字符串
	 *
	 * @param key
	 * @return
	 * @author: 胡磊 - lei.hu@99bill.com
	 * @date: Created on 2016年12月29日 上午10:21:59
	 */
	public static String convert2String(Key key) {
		byte[] bytePublicyKey = key.getEncoded();
		// String publicKeyStr = Utils.toHexString(bytePublicyKey);
		String publicKeyStr = new String(Base64.encode(bytePublicyKey));

		return publicKeyStr;
	}

	/**
	 * 根据BASE64编码的公钥字符串，转换成功公钥对象
	 *
	 * @param publicKeyStr
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @author: 胡磊 - lei.hu@99bill.com
	 * @date: Created on 2016年12月29日 上午10:22:22
	 */
	public static PublicKey convert2PublicKey(String publicKeyStr)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		// byte[] publicKeyBytes = Utils.hexStringToByteArray(publicKeyStr);
		byte[] publicKeyBytes = Base64.decode(publicKeyStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		return publicKey;
	}

	/**
	 * 根据16进制编码的私钥字符串，转换成功私钥对象
	 *
	 * @param privateKeyStr
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @author: 胡磊 - lei.hu@99bill.com
	 * @date: Created on 2016年12月29日 上午10:23:00
	 */
	public static PrivateKey convert2PrivateKey(String privateKeyStr)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		// byte[] privateKeyBytes = Utils.hexStringToByteArray(privateKeyStr);
		byte[] privateKeyBytes = Base64.decode(privateKeyStr);
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

		return privateKey;
	}
}
