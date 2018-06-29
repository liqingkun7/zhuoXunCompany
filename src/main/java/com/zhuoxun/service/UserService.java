package com.zhuoxun.service;

import java.util.Date;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhuoxun.entity.User;

/**
 * User业务层接口 <br/>
 * IService:继承自mybatisplus包中的IService方法
 * 
 * @author lzx
 *
 */
public interface UserService extends IService<User> {

	Page<User> search(int num, int size, String name, String tel, String idcard, Date timkselect);

}
