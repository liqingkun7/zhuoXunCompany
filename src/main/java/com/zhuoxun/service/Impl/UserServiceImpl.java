package com.zhuoxun.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuoxun.common.Tools;
import com.zhuoxun.entity.User;
import com.zhuoxun.mapper.UserMapper;
import com.zhuoxun.service.UserService;

/**
 * User业务层实现类
 * 
 * @author lzx
 *
 */
@Service
@CacheConfig(cacheNames="userCache") // 本类内方法指定使用缓存时，默认的名称就是userCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户组合查询
	 */
	@Override
	public Page<User> search(int num, int size, String name, String tel, String idcard, Date timkselect) {
		EntityWrapper<User> ew = new EntityWrapper<>();
		if (!Tools.empty(name)) {
			ew.like("name", name);
		}
		if (!Tools.empty(tel)) {
			ew.like("tel", tel);
		}
		if (!Tools.empty(idcard)) {
			ew.eq("idcard", idcard);
		}
		if (timkselect != null) {
			ew.eq("registerdate", timkselect);
		}
		System.out.println(ew.toString() + "  ====拼后的SQL");
		Page<User> page = new Page<>();
		page.setCurrent(num);
		page.setSize(size);
		page.setRecords(userMapper.selectPage(new Page<>(num, size), ew));
		page.setTotal(userMapper.selectCount(ew));
		return page;
	}

}
