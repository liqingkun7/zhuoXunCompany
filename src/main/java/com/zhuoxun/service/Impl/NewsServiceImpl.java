package com.zhuoxun.service.Impl;
/**
 * News业务实现类
 * @author lzx
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuoxun.entity.News;
import com.zhuoxun.mapper.NewsMapper;
import com.zhuoxun.service.NewsService;

@Service
@CacheConfig(cacheNames="userCache") // 本类内方法指定使用缓存时，默认的名称就是userCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class NewsServiceImpl  extends ServiceImpl<NewsMapper, News>  implements NewsService{

	@Autowired
	private NewsMapper  newsMapper;
	/**
	 * 分页查询数据
	 */
	@Override
	public Page<News> findByPager(int num, int size) {
		
		Page<News>  page = new Page<>();
		page.setCurrent(num);
		page.setSize(size);
		page.setTotal(newsMapper.selectCount(new EntityWrapper<News>()));
		page.setRecords(newsMapper.selectPage(new Page<>(num, size), new EntityWrapper<News>()));
		return page;
	}

}
