package com.zhuoxun.service.Impl;

import java.util.Date;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuoxun.common.Tools;
import com.zhuoxun.entity.Dept;
import com.zhuoxun.mapper.DeptMapper;
import com.zhuoxun.service.DeptService;

/**
 * 部门业务层实现类
 * 
 * @author lzx
 *
 */
@Service
@CacheConfig(cacheNames="userCache") // 本类内方法指定使用缓存时，默认的名称就是userCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class DempServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

	/**
	 * 部门模糊查询
	 */
	@Override
	public Page<Dept> search(int num, int size, String name, Date timkselect) {

		EntityWrapper<Dept> ew = new EntityWrapper<Dept>();
		if (!Tools.empty(name)) {
			ew.like("name", name);
		}

		if (timkselect != null) {
			ew.gt("timkselect", timkselect);
		}

		Page<Dept> page = new Page<Dept>();
		// 当前页数
		page.setCurrent(num);
		// 当前页显示条数
		page.setSize(size);
		page.setTotal(this.selectCount(ew));
		page.setRecords(this.selectList(ew));
		return page;
	}

}
