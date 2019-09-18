package com.tedu.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
@Component
class A{
	public static void name() {
		System.out.println("aaaaaaaaa");
	}
	
}
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootTests {
	@Autowired
	private ApplicationContext ctx;
	@Test
	public void testSpringBoot() {
		A obj = ctx.getBean(A.class);
		obj.name();
	}
}
	