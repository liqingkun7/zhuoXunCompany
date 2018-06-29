package com.zhuoxun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhuoxun.controller.common.BaseController;
import com.zhuoxun.entity.News;
import com.zhuoxun.service.NewsService;
/**
 * News控制器
 * @author lzx
 *
 */
@Controller
@RequestMapping("/mng/news")
public class NewsController  extends BaseController{

	@Autowired
	private NewsService  newsService;
	private int totalpage = 0; // 总页数
	
	/**
	 * 按分页查询数据
	 * @param mav
	 * @param num  页数
	 * @param size 条数
	 * @return
	 */
	@RequestMapping("/findByPager")
	public  ModelAndView  findByPager(ModelAndView  mav,@RequestParam(defaultValue="1",name="num",required=false)int num,@RequestParam(defaultValue="10",required=false,name="size")int size) {
		Page<News> page = newsService.selectPage(new Page<News>(num, size));
		// 查询总条数
		int count = newsService.selectCount(new EntityWrapper<>());
		// 计算总页数
		if (count % size == 0) {
			totalpage = (int) (count / size);
		} else {
			totalpage = (int) (count / size) + 1;
		}
		System.out.println("总页数：  " + totalpage);
		mav.addObject("totalpage", totalpage);
		mav.addObject("page", page);
		mav.setViewName("backend/mng/news/list");
		return  mav;
	}
	
	/**
	 * 保存新闻信息
	 * @param news
	 * @return
	 */
	@RequestMapping("/save")
	public String save(News  news) {
		newsService.insert(news);
		return "redirect:/mng/news/findByPager";
	}
	
	/**
	 * 根据ID，获取用户详情
	 * @param mav
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail/{id:\\d+}")
	public ModelAndView  detail(ModelAndView  mav,@PathVariable int id) {
		News  news  = newsService.selectById(id);
		mav.addObject("news", news);
		mav.setViewName("backend/mng/dept/list");
		return mav;
	}
	
	/**
	 * 根据ID删除用户信息
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id:\\d+}")
	public String delete(@PathVariable  int  id) {
		newsService.deleteById(id);
		return "redirect:/mng/news/findByPager";
	}
	
	
}
