package com.cy;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.common.cache.DefaultCache;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateSourceTest {
		
	@Autowired
	private DataSource dataSource; 
	@Test
	public void testDefaultCache() throws Exception {
		System.out.println(dataSource.getClass());
		System.err.println(dataSource.getConnection());
	}
	
}
