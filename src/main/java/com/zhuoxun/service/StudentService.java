package com.zhuoxun.service;

import java.util.Date;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhuoxun.entity.Student;

/**
 * 學生業務層接口
 * 
 * @author lzx
 *
 */
public interface StudentService extends IService<Student> {
	/**
	 * 模糊查询
	 * @param num
	 * @param size
	 * @param name
	 * @param id
	 * @param birthday
	 * @return
	 */

	Page<Student> search(int num, int size, String name, String id, Date birthday);

}
