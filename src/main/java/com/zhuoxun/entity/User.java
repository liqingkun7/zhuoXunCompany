package com.zhuoxun.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体类
 * 
 * @author lzx
 *
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class User  implements Serializable{
	private static final long serialVersionUID = 6527737891168277948L;
	// 用户编号
	private Integer id;
	// 用户名称
	private String name;
	// 用户年龄
	private int age;
	// 头像
	private String title;
	// 个人简介
	private String introduction;
	// 个人详情描述
	private String description;
	// 联系方式
	private String tel;
	// 地址
	private String address;
	// 入职日期
	private Date registerdate;
	// 用户工牌号
	private String idcard;
	// 用户性别
	private Boolean gender;
	// 密码
	private String password;
	//部门编号
	private Integer deptid;
	
	

}
