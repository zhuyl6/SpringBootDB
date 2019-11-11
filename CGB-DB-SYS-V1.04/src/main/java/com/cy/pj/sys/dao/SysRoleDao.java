package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	@Select("select id,name from sys_roles")
	List<CheckBox> findRoles();
	/**
	 * 基于角色id查询角色名称，备注以及角色对应的菜单id
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	
	/**
	 * 更新角色自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	/**
	 * 保存角色自身信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	
	
	@Delete("delete from sys_roles where id=#{id}")
	int deleteObject(Integer id);
	
     /**
           * 按角色名查询记录总数
      * @param name
      * @return
      */
	 int getRowCount(@Param("name")String name);
	 /**
	    * 基于条件进行分页查询
	  * @param name
	  * @param startIndex
	  * @param pageSize
	  * @return
	  */
	 List<SysRole> findPageObjects(
			 @Param("name")String name,//WhereWrapper
			 @Param("startIndex")Integer startIndex,//limitWrapper
			 @Param("pageSize")Integer pageSize);
	 
	 
	 
}
