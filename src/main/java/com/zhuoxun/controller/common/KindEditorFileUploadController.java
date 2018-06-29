package com.zhuoxun.controller.common;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.zhuoxun.common.WebUtils;

/**
 * 负责kindeditor文件上传的处理器
 */
@Controller
public class KindEditorFileUploadController {
	
	private String succ_txt = "{\"error\" : 0, \"url\" : \"%1$s\"}";
	private String error_txt = "{\"error\" : 1, \"message\" : \"%1$s\"}";

	//两个参数
	//imgFile: 文件form名称
	//dir: 上传类型，分别为image、flash、media、file
	@RequestMapping("/mng/ke_upload")
	@ResponseBody
	public String upload(@RequestParam("imgFile") CommonsMultipartFile src, 
			@RequestParam("dir") String type,
			HttpServletRequest request) throws Exception{
		
		if(!src.isEmpty()){
			String fileName = src.getOriginalFilename();
			System.out.println("fileName：" + fileName);
	        String destFileName = WebUtils.generateUUIDFileName(fileName);
	        
	        String absRoot = request.getServletContext().getRealPath("/");
	        String destPath = WebUtils.getImageParentPath() + destFileName;
	        
	        File dest = new File(absRoot + destPath);
	        if (!dest.exists()) {
	        	dest.mkdirs();
			}
	        src.transferTo(dest);
	        
	        return String.format(succ_txt, request.getContextPath() + destPath);
		}
		
		return String.format(error_txt, "上传失败！");
	}
}
