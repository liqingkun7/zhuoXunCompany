package com.zhuoxun.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuoxun.entity.User;

/**
 * 用户Mapper,继承自mybatisplus中的BaseMapper
 * 
 * @author lzx
 *
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
