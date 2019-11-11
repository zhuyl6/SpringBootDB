package com.cy.pj.sys.service;
import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;
public interface SysRoleService {
	/**
	 * 查询所有角色信息
	 * @return
	 */
	List<CheckBox> findRoles();
	/**
	 * 基于角色id查询角色名称，备注以及角色对应的菜单id
	 * @param id
	 * @return
	 */
	 SysRoleMenuVo findObjectById(Integer id);
	
	 /**
	  * 更新角色以及角色对应的菜单信息
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int updateObject(SysRole entity,Integer[] menuIds);
	 
	 /**
	  * 保存角色以及角色对应的菜单信息
	  * @param entity
	  * @param menuIds
	  * @return
	  */
	 int saveObject(SysRole entity,Integer[] menuIds);
	
	 /**
	  	* 删除角色以及角色对应的关系数据
	  * @param id 角色id
	  * @return 删除的行数
	  */
	 int deleteObject(Integer id);
	
	 PageObject<SysRole> findPageObjects(
			 String name,
			 Integer pageCurrent);
}
