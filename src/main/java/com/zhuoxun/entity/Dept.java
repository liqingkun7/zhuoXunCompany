package com.zhuoxun.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门实体类
 * 
 * @author lzx
 *
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
public class Dept   implements Serializable{
	private static final long serialVersionUID = 7127781086772117292L;
	//部门编号
	private Integer id;
	//部门名称
	private String name;
	//部门注册时间 
	private Date registertime;
	//备注
	private String description;
	
}
