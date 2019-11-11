package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Spring Boot项目启动类
 * 基于注解@SpringBootApplication描述实现自动化配置
 * 注意:1.一个项目只有一个启动类
 * 	   
 * @author Administrator
 *
 */

@SpringBootApplication
public class CgbSboot01Application {

	public static void main(String[] args) {
		SpringApplication.run(CgbSboot01Application.class, args);
	}

}
