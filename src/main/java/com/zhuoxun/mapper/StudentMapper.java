package com.zhuoxun.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuoxun.entity.Student;

/**
 * 学生Dao层
 * @author lzx
 *
 */

@Repository
public interface StudentMapper  extends  BaseMapper<Student> {

}
