package com.zhuoxun.controller.common;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 所有Controller的基类，用于编写一些公用的方法
 * 
 * @author zxkj
 */
public abstract class BaseController {

	/** jackson提供的json操作工具类 */
	protected ObjectMapper mapper = new ObjectMapper();

	/**
	 * 获取客户端的真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserIP(HttpServletRequest request) {
		String strUserIp = "127.0.0.1";

		strUserIp = request.getHeader("X-Forwarded-For");
		if ((strUserIp == null) || (strUserIp.length() == 0) || ("unknown".equalsIgnoreCase(strUserIp))) {
			strUserIp = request.getHeader("Proxy-Client-IP");
		}
		if ((strUserIp == null) || (strUserIp.length() == 0) || ("unknown".equalsIgnoreCase(strUserIp))) {
			strUserIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((strUserIp == null) || (strUserIp.length() == 0) || ("unknown".equalsIgnoreCase(strUserIp))) {
			strUserIp = request.getRemoteAddr();
		}
		if (strUserIp != null) {
			strUserIp = strUserIp.split(",")[0];
		} else {
			strUserIp = "127.0.0.1";
		}
		if (strUserIp.length() > 16) {
			strUserIp = "127.0.0.1";
		}
		return strUserIp;
	}

	/**
	 * 获取客户端请求时的服务器端地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		StringBuilder basePath = new StringBuilder();
		basePath.append(request.getScheme()).append("://");
		basePath.append(request.getServerName());

		int port = request.getServerPort();
		if (port != 80) {
			basePath.append(":").append(port);
		}

		if (path != null && !"".equals(path)) {
			basePath.append(path);
		}
		basePath.append("/");

		return basePath.toString();
	}

}
