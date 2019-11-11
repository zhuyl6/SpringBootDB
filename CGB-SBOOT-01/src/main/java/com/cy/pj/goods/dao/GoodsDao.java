package com.cy.pj.goods.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsDao {
	 @Delete("delete from tb_goods where id=#{id}")
	 int deleteObject(Integer id);

	int deleteObjects(@Param("ids")Integer...ids);
	
	
}
