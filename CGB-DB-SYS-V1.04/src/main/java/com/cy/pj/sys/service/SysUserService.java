package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	/**
	 * 修改密码
	 * @param password 原密码
	 * @param newPassword 新密码
	 * @param cfgPassword 密码确认
	 * @return
	 */
	int updatePassword(String password,
	           String newPassword,
	           String cfgPassword);

	/**
	  * 基于用户id查询用户信息以及用户对应的关系数据
	 * @param id
	 * @return
	 */
	Map<String,Object> findObjectById(Integer id);
	
	/**
	 * 更新用户以及用于与角色的关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateObject(SysUser entity,Integer[] roleIds);
	/**
	 * 保存用户以及用于与角色的关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysUser entity,Integer[] roleIds);
	/**
	 * 禁用或启用用户对象
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(Integer id,Integer valid,String modifiedUser);
     /**
      * 分页查询用户以及用户对应的部门信息
      * @param username
      * @param pageCurrent
      * @return
      */
	 PageObject<SysUserDeptVo> findPageObjects(
			 String username,
			 Integer pageCurrent);
}
