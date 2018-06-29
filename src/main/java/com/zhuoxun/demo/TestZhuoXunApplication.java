package com.zhuoxun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.zhuoxun.mapper")
@ComponentScan(basePackages = { "com.zhuoxun.service", "com.zhuoxun.service.Impl", "com.zhuoxun.controller",
		"com.zhuoxun.entity", "com.zhuoxun.converter","com.zhuoxun.controller.common" })
@ServletComponentScan("com.zhuoxun.listener")
@SpringBootApplication
public class TestZhuoXunApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestZhuoXunApplication.class, args);
	}
}
