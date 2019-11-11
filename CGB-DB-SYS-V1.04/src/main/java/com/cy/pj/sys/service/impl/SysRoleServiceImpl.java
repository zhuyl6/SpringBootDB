package com.cy.pj.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredTime;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;
@Service
public class SysRoleServiceImpl implements SysRoleService {

	private SysRoleDao sysRoleDao;
	private SysRoleMenuDao sysRoleMenuDao;
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	public SysRoleServiceImpl(
			SysRoleDao sysRoleDao,
			SysRoleMenuDao sysRoleMenuDao,
			SysUserRoleDao sysUserRoleDao) {
		this.sysRoleDao=sysRoleDao;
		this.sysRoleMenuDao=sysRoleMenuDao;
		this.sysUserRoleDao=sysUserRoleDao;
	}
	@Override
	public List<CheckBox> findRoles() {
		List<CheckBox> list=sysRoleDao.findRoles();
		//if(list==null||list.size()==0)
			//throw new IllegalArgumentException("没有对应的角色信息");
		return list;
	}
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.参数校验
		if(id==null||id<1)
			throw new IllegalArgumentException("参数无效");
		//2.基于id执行查询
		SysRoleMenuVo rm=
		sysRoleDao.findObjectById(id);
		System.out.println("rm="+rm);
		if(rm==null)
			throw new IllegalArgumentException("记录已经不存在");
		//3.返回结果
		return rm;
	}
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity))
			throw new IllegalArgumentException("角色名不允许为空");
		if(menuIds==null||menuIds.length==0)
			throw new IllegalArgumentException("需要为角色分配菜单权限");
		//2.保存数据
		//2.1保存角色自身信息
		int rows=sysRoleDao.updateObject(entity);
		//2.2更新角色和菜单关系数据
		//2.2.1删除关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		//2.2.2添加新的关系数据
		sysRoleMenuDao.insertObjects(
				entity.getId(),
				menuIds);
		//3.返回结果
		return rows;
	}
	@RequiredTime
	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.参数校验
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity))
		throw new IllegalArgumentException("角色名不允许为空");
		if(menuIds==null||menuIds.length==0)
		throw new IllegalArgumentException("需要为角色分配菜单权限");
		//2.保存数据
		//2.1保存角色自身信息
		int rows=sysRoleDao.insertObject(entity);
		//2.2保存角色和菜单关系数据
		sysRoleMenuDao.insertObjects(
				entity.getId(),
				menuIds);
		//3.返回结果
		return rows;
	}
	
	@Override
	public int deleteObject(Integer id) {
		//1.参数校验
		if(id==null||id<1)
			throw new IllegalArgumentException("参数不合法");
		//2.删除角色菜单关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		//3.删除用户角色关系数据
		sysUserRoleDao.deleteObjectsByRoleId(id);
		//4.删除角色自身信息
		int rows=sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	
	@Override
	public PageObject<SysRole> findPageObjects(
			String username,Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=sysRoleDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
		sysRoleDao.findPageObjects(username,
		startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
}






