package com.zhuoxun.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhuoxun.controller.common.BaseController;
import com.zhuoxun.entity.Student;
import com.zhuoxun.service.StudentService;

/**
 * 学生控制层
 * @author lzx
 *
 */
@Controller
@RequestMapping("/mng/stu")
public class StudentController  extends BaseController{
	
	@Autowired
	private StudentService  studentService;
	
	/**
	 * 查询全部
	 * @param mav
	 * @return
	 */
	@GetMapping("/findAll")
	public  ModelAndView  findAll(ModelAndView  mav) {
		//查询全部方法
		List<Student>  list = studentService.selectList(new EntityWrapper<Student>());
		mav.addObject("list", list);
		mav.setViewName("backend/mng/student/list");
		return  mav;
	}
	
	
	/**
	 * 添加或更新
	 * @param student
	 * @return
	 */
	@PostMapping("/addorupdate")
	public  String  insertOrUpdate(Student  student) {
		
		studentService.insertOrUpdate(student);
		
		return "redirect:/mng/stu/findAll";
	}
	
	/**
	 * 根据ID进行删除
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id:\\d+}")
	public  String  delete(@PathVariable  int id) {
		studentService.deleteById(id);
		return "redirect:/mng/stu/findAll";
	}
	
	/**
	 * 批量删除
	 * 1,2,3,4,5
	 * @return
	 */
	@GetMapping("/deletebath")
	public String bathDelete(@RequestParam(name="ids",required=true) String ids) {
		
		List<Integer>  list =  new ArrayList<>();
		String[]  str = ids.split(",");
		for (String string : str) {
			list.add(Integer.parseInt(string));
		}
		//批量删除
		studentService.deleteBatchIds(list);
		return "redirect:/mng/stu/findAll";
	}
    /**
     * 根据ID，获取某条数据	
     * @return
     * @throws JsonProcessingException 
     */
	@GetMapping("/detail/{id:\\d+}")
	public  String  detail(@PathVariable int id) throws JsonProcessingException {
		Student  stu  =studentService.selectById(id);
		//json数据
		String s = mapper.writeValueAsString(stu);
		System.out.println("json数量：  "+s);
		return  s; 
	}
	/**
	 * 分页查询
	 * @return
	 */
	@GetMapping("/findByPager")
	public ModelAndView   findByPager(@RequestParam(defaultValue="1",required=false,name="num")int num,@RequestParam(defaultValue="10",required=false,name="size")int size,ModelAndView mav) {
		
		Page<Student>  page  =studentService.selectPage(new Page<>(num, size));
		System.out.println(page.toString());
		mav.addObject("page", page);
		mav.setViewName("backend/mng/student/listfindpager");
		return mav;
	}
	
	/**
	 * 模糊查询
	 * @return
	 */
	@PostMapping("/search")
	public  ModelAndView  search(@RequestParam(defaultValue="1",required=false,name="num")int num,@RequestParam(defaultValue="10",required=false,name="size")int size,ModelAndView mav,String name,String id,Date birthday) {
		
		Page<Student>  page  = studentService.search(num,size,name,id,birthday);
		mav.addObject("page", page);
		mav.setViewName("backend/mng/student/listfindpager");
		return mav;
	}
	
	

}
