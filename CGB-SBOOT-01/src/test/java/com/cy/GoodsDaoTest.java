package com.cy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.goods.dao.GoodsDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsDaoTest {

	@Autowired
	private GoodsDao goodsDao;

	@Test
	public void deleteGoods(){
		int rows = goodsDao.deleteObject(100);
		System.out.println(rows);
	}

	@Test
	public void testDeleteObjects() {
		int rows=goodsDao.deleteObjects(17,18);
		System.out.println(rows);
	}




}
