package com.zhuoxun.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class WebUtils {

	/** 上传的图片的基本路径 */
	public static final String PATH_UPLOAD_IMAGE = "/uploads/images/";

	public String getRealPath(HttpServletRequest request, String path) {
		return request.getServletContext().getRealPath(path);
	}

	/**
	 * 获取上传的图片的基准路径（从项目根目录下开始的路径）
	 * 
	 * @return
	 */
	public static String getImageParentPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		return PATH_UPLOAD_IMAGE + ymd + "/";
	}

	/**
	 * 根据原文件名创建一个UUID值的文件名
	 * 
	 * @param srcName
	 * @return
	 */
	public static String generateUUIDFileName(String srcName) {
		String destFileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(srcName);
		return destFileName;
	}
}
