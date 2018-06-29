package com.zhuoxun.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 */
public class MD5Encoder {

	public static String md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用MD5算法加密字符串
	 * 
	 * @param 源字符串
	 * @return 加密后的字符串
	 */
	public static String encode(final String src) {
		String dest = new String(src);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 进行加密,获取字节数组
			byte[] b = md.digest(dest.getBytes());
			dest = byte2hexString(b);
		} catch (Exception ex) {
		}
		return dest;
	}

	/**
	 * 方法说明:把字节数组转换成字符串.
	 * 
	 * @param bytes
	 * @return 字符串
	 */
	protected static final String byte2hexString(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Integer.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		System.out.println("admin" + "字符串经过加密后成了:" + MD5Encoder.encode("000000"));
		System.out.println("admin" + "字符串经过加密后成了:" + MD5Encoder.md5("admin"));
	}
}
