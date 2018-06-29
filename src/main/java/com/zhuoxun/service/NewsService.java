package com.zhuoxun.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhuoxun.entity.News;

/**
 * 新闻业务层接口
 * @author lzx
 *
 */
public interface NewsService  extends IService<News>{

	Page<News> findByPager(int num, int size);

}
