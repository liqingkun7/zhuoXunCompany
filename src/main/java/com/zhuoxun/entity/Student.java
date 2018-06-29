package com.zhuoxun.entity;

import java.io.Serializable;
/**
 * 学生类
 * @author lzx
 *
 */
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuppressWarnings("unused")
public class Student   implements Serializable{
	
	private static final long serialVersionUID = 5936807223234309L;
	//学生ID
	private Integer  id;
	//姓名
	private  String name;
	//年龄
	private Integer  age;
	//性别
	private Boolean gender;
	//出生日期
	private Date  birthday;
	//备注
	private String memo;

}
