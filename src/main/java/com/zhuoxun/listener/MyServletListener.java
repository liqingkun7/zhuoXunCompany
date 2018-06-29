package com.zhuoxun.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zhuoxun.common.Constants;

/**
 * 监听器
 * 
 * @author lzx
 *
 */
@WebListener
public class MyServletListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化方法
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String path = arg0.getServletContext().getContextPath();
		System.out.println("=======监听器启动=========");
		arg0.getServletContext().setAttribute(Constants.KEY_APPLICATION_PATH, path);
		String image_path = arg0.getServletContext().getRealPath("/images");
		// 文件上传路径
		System.out.println("===================image_path= " + path+"/images");
		arg0.getServletContext().setAttribute(Constants.FILE_UPLOAD, image_path);
	}

}
