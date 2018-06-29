package com.zhuoxun.controller.common;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuoxun.common.WebUtils;

/**
 * kindeditor文件浏览功能的处理器
 * 
 */
@SuppressWarnings("unchecked")
@Controller
public class KindEditorFileManagerController {

	@RequestMapping(value = "/mng/ke_manager", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String upload(@RequestParam(name = "dir", defaultValue = "image") String type,
			@RequestParam(name = "path", defaultValue = "") String path,
			// 排序形式，name or size or type
			@RequestParam(name = "order", defaultValue = "NAME") String order, HttpServletRequest request)
			throws Exception {

		String rootPath = request.getServletContext().getRealPath("/") + WebUtils.PATH_UPLOAD_IMAGE;
		String rootUrl = request.getContextPath() + WebUtils.PATH_UPLOAD_IMAGE;
		// 根据path参数，设置各路径和URL
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (path.length() > 0) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}
		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			return "Access is not allowed.";
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			return "Parameter is not valid.";
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			return "Directory does not exist.";
		}
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		// 遍历目录取的文件信息
		List<HashMap<String, Object>> fileList = new ArrayList<HashMap<String, Object>>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				HashMap<String, Object> hash = new HashMap<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
					hash.put("lastModified", 0L);
				} else if (file.isFile()) {
					String fileExt = StringUtils.getFilenameExtension(fileName).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
					hash.put("lastModified", file.lastModified());
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("SIZE".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("TYPE".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new LastModifiedComparator());
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(result);
	}

	public class SizeComparator implements Comparator<Object> {
		public int compare(Object x, Object y) {
			HashMap<String, Object> a = (HashMap<String, Object>) x;
			HashMap<String, Object> b = (HashMap<String, Object>) y;
			Boolean aa = (Boolean) a.get("is_dir");
			Boolean bb = (Boolean) b.get("is_dir");

			if (aa && !bb) {
				return -1;
			} else if (!aa && bb) {
				return 1;
			} else {
				Long sizeA = (Long) a.get("filesize");
				Long sizeB = (Long) b.get("filesize");
				if (sizeA > sizeB) {
					return 1;
				} else if (sizeA < sizeB) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator<Object> {
		public int compare(Object x, Object y) {
			HashMap<String, Object> a = (HashMap<String, Object>) x;
			HashMap<String, Object> b = (HashMap<String, Object>) y;
			Boolean aa = (Boolean) a.get("is_dir");
			Boolean bb = (Boolean) b.get("is_dir");

			if (aa && !bb) {
				return -1;
			} else if (!aa && bb) {
				return 1;
			} else {
				return ((String) a.get("filetype")).compareTo((String) b.get("filetype"));
			}
		}
	}

	public class LastModifiedComparator implements Comparator<Object> {
		public int compare(Object x, Object y) {
			HashMap<String, Object> a = (HashMap<String, Object>) x;
			HashMap<String, Object> b = (HashMap<String, Object>) y;

			Boolean aa = (Boolean) a.get("is_dir");
			Boolean bb = (Boolean) b.get("is_dir");

			if (aa && !bb) {
				return -1;
			} else if (!aa && bb) {
				return 1;
			} else {
				Long sizeA = (Long) a.get("lastModified");
				Long sizeB = (Long) b.get("lastModified");
				if (sizeA > sizeB) {
					return -1;
				} else if (sizeA < sizeB) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

}
