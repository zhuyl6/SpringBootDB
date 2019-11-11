package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysLog;

@Mapper
public interface SysLogDao {
	 /**
	    * 写入日志
	  * @param entity
	  * @return
	  */
	 int insertObject(SysLog entity);
	 
	 int deleteObjects(@Param("ids")Integer...ids);//array
	 /**
	  * 依据条件查询日志记录总数
	  * @param username 查询条件
	  * @return 查询到记录总数
	  */
	 int getRowCount(@Param("username")String username);
	 
	 /**
	  * 分页查询当前页日志信息
	  * @param username 查询条件
	  * @param startIndex 当前页起始位置
	  * @param pageSize 当前页面大小
	  * @return 当前页查询到记录
	  */
	 List<SysLog> findPageObjects(
			      @Param("username")String username,
			      @Param("startIndex")Integer startIndex,
			      @Param("pageSize")Integer pageSize);
}
