package com.zhuoxun.common;

/**
 * 工具类方法
 * 
 * @author lzx
 *
 */
public class Tools {

	/**
	 * 判断字符串是否为空
	 * 
	 * @return
	 */
	public static Boolean empty(String str) {

		if (str == null || str.length() == 0 || str.isEmpty()) {
			return true;
		}
		return false;

	}

}
