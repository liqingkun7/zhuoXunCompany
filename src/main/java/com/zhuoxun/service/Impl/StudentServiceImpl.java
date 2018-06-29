package com.zhuoxun.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuoxun.common.Tools;
import com.zhuoxun.entity.Student;
import com.zhuoxun.mapper.StudentMapper;
import com.zhuoxun.service.StudentService;

/**
 * 学生类业务层实现
 * @author lzx
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>  implements  StudentService {

	@Autowired
	private  StudentMapper studentMapper;

	@Override
	public Page<Student> search(int num, int size, String name, String id, Date birthday) {
		
		EntityWrapper<Student>  ew = new EntityWrapper<>();
		//姓名是否为空
		if(!Tools.empty(name)) {
			ew.like("name", name);
		}
		//id是否为空
		if(!Tools.empty(id+"")) {
			ew.like("id", id);
		}
		//日期是否为空
		if(birthday!=null) {
			ew.eq("birthday", birthday);
		}
		Page<Student>  page = new Page<>();
		page.setCurrent(num);
		page.setSize(size);
		page.setRecords(studentMapper.selectPage(new Page<>(num, size), ew));
		page.setTotal(studentMapper.selectCount(new EntityWrapper<>()));
		return page;
	}
	
	
}
