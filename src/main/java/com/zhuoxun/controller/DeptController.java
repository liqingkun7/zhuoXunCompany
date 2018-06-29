package com.zhuoxun.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuoxun.controller.common.BaseController;
import com.zhuoxun.entity.Dept;
import com.zhuoxun.service.DeptService;

/**
 * 部门控制层
 * 
 * @author lzx
 *
 */
@Controller
@RequestMapping("/mng/dept")
public class DeptController extends BaseController {
	@Autowired
	private DeptService deptService;
	private int totalpage = 0; // 总页数
	ObjectMapper  objectMapper  = new ObjectMapper();

	/**
	 * 查询所有部门信息
	 * 
	 * @return
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/findAll")
	@ResponseBody
	public String findAll() throws JsonProcessingException {
		List<Dept> list = deptService.selectList(new EntityWrapper<Dept>());
		String v  = objectMapper.writeValueAsString(list);
		System.out.println("所有部门信息：  "+v);
		return v;
	}

	/**
	 * 分页查找
	 * 
	 * @param mav
	 * @param num
	 *            ：页数
	 * @param size：条数
	 * @return
	 */
	@GetMapping("/findByPager")
	public ModelAndView findByPager(ModelAndView mav, @RequestParam(defaultValue = "1", name = "num") int num,
			@RequestParam(defaultValue = "10", name = "size") int size, HttpServletRequest request) {
		Page<Dept> page = deptService.selectPage(new Page<>(num, size));
		for (Dept  dept : page.getRecords()) {
		   System.out.println(dept+"   === ");	
		}
		// 查询总条数
		int count = deptService.selectCount(new EntityWrapper<>());
		// 计算总页数
		if (count % size == 0) {
			totalpage = (int) (count / size);
		} else {
			totalpage = (int) (count / size) + 1;
		}
		System.out.println("总页数：  " + totalpage);
		mav.addObject("totalpage", totalpage);
		mav.addObject("page", page);
		mav.setViewName("backend/mng/dept/list");
		return mav;
	}

	/**
	 * 分页查询
	 * 
	 * @param mav
	 * @param num
	 *            页数
	 * @param size
	 *            每页显示的条数
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView findByPager(ModelAndView mav,
			@RequestParam(defaultValue = "1", name = "num", required = false) int num,
			@RequestParam(defaultValue = "10", name = "size", required = false) int size) {
		Page<Dept> page = deptService.selectPage(new Page<>(num, size), new EntityWrapper<Dept>());
		mav.addObject("page", page);
		mav.setViewName("/mng/dept/list");
		return mav;
	}

	/**
	 * 保存部门信息
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/save")
	public String save(Dept dept) {
		deptService.insert(dept);
		return "redirect:/mng/dept/findByPager";
	}

	/**
	 * 根据ID进行删除
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id:\\d+}")
	public String delete(@PathVariable int id) {
		deptService.deleteById(id);
		return "redirect:/mng/dept/findByPager";
	}

	/**
	 * 查询部门详情信息
	 * 
	 * @param mav
	 * @param id
	 * @return
	 */
	@GetMapping("/detail/{id:\\d+}")
	public ModelAndView detail(ModelAndView mav, @PathVariable int id) {
		Dept dept = deptService.selectById(id);
		mav.addObject("dept", dept);
		mav.setViewName("/mng/dept/detail");
		return mav;
	}

	/**
	 * 更新部门信息
	 * 
	 * @return
	 */
	@PostMapping("/update/{id:\\d+}")
	public String update(@PathVariable int id, Dept dept) {
		deptService.updateById(dept);
		return "redirect:/mng/dept/findByPager";
	}

	/**
	 * 模糊查询
	 */
	@GetMapping("/search")
	public ModelAndView search(String name, Date timkselect, ModelAndView mav,
			@RequestParam(defaultValue = "1", name = "num", required = false) int num,
			@RequestParam(defaultValue = "10", name = "size", required = false) int size) {
		// 查询总条数
		Page<Dept> page = deptService.search(num, size, name, timkselect);
		mav.addObject("page", page);
		mav.setViewName("backend/mng/dept/list");
		return mav;
	}

}
