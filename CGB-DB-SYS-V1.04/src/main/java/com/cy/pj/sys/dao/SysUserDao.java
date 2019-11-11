package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	/**
	 * 基于用户id修改用户的密码
	 * @param password
	 * @param salt
	 * @param id
	 * @return
	 */
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);

	/**
	  * 基于用户名查找用户对象
	 * @param username
	 * @return
	 */
	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username);
	
	/**
	  *   根据用户id查询当前用户以及对应的部门信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	
	/**
	  * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	/**
	 * 保存用户自身信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	
	 /**
	  * 禁用和启用
	  * @param id
	  * @param valid
	  * @param modifiedUser
	  * @return
	  */
	 int validById(@Param("id")Integer id,
			 @Param("valid")Integer valid,
			 @Param("modifiedUser")String modifiedUser);
	
	 /**
	  * 基于用户名统计用户记录总数
	  * @param username
	  */
	 int getRowCount(@Param("username")String username);
	 /**
	  * 分页查询用户信息
	  * @param username
	  * @param startIndex
	  * @param pageSize
	  * @return
	  */
	 List<SysUserDeptVo> findPageObjects(
			 @Param("username")String username,
			 @Param("startIndex")Integer startIndex,
			 @Param("pageSize")Integer pageSize);
}
