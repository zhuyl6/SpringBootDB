package com.cy;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.common.cache.DefaultCache;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCacheTest {
		
	@Autowired
	private DefaultCache defaultCache; 
	@Test
	public void testDefaultCache() {
		System.out.println(defaultCache);
	}
	
}
