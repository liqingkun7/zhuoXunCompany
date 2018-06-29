package com.zhuoxun.service;

import java.util.Date;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhuoxun.entity.Dept;

/**
 * 部门业务层
 * 
 * @author lzx
 *
 */

public interface DeptService extends IService<Dept> {

	public Page<Dept> search(int num, int size, String name, Date timkselect);

}
