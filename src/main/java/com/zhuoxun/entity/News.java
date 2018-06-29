package com.zhuoxun.entity;
/**
 * 新闻实体类
 * @author lzx
 *
 */

import java.io.Serializable;
import java.util.Date;

import javax.annotation.processing.SupportedAnnotationTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
public class News  implements Serializable{

	private static final long serialVersionUID = -1703686279907167648L;
	//新闻编号
	private Integer  id;
	//新闻作者
	private String author;
	//新闻标题
	private String title;
	//发布人编号
	private Integer userid;
	//发布日期 
	private Date pubdate;
	//备注
	private String desc;
}
