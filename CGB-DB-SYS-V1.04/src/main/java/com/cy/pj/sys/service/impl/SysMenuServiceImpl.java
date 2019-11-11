package com.cy.pj.sys.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
@Transactional(timeout = 30,
               rollbackFor = Throwable.class,
               isolation = Isolation.READ_COMMITTED)
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public int updateObject(SysMenu entity) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		//......
		//2.保存菜单对象
		int rows=0;
		try {
			rows=sysMenuDao.updateObject(entity);
		}catch(Throwable e) {//了解
			log.error(e.getMessage());
			//报警
			throw new ServiceException("系统维护中");
		}
		//3.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysMenu entity) {
		//1.参数校验
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("菜单名不能为空");
		//......
		//2.保存菜单对象
		int rows=0;
		try {
		  rows=sysMenuDao.insertObject(entity);
		}catch(Throwable e) {//了解
		  log.error(e.getMessage());
		  //报警
		  throw new ServiceException("系统维护中");
		}
		//3.返回结果
		return rows;
	}
	@Transactional(readOnly = true)
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@Transactional//标识作用
	@Override
	public int deleteObject(Integer id) {
		//1.参数校验
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		//2.基于id统计子菜单并校验
		int rowCount=sysMenuDao.getChildCount(id);
		if(rowCount>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除菜单角色关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//4.删除菜单自身信息
		int rows=sysMenuDao.deleteObject(id);
		if(rows==0)
		throw new ServiceException("菜单可能已经不存在");
		//5.返回结果
		return rows;
	}
	@RequiredLog("查询菜单")
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> findObjects() {
		String tName=Thread.currentThread().getName();
		System.out.println("method.query.thread.name="+tName);
		List<Map<String,Object>> list=
		sysMenuDao.findObjects();
		if(list.size()==0)
		throw new ServiceException("no records");
		return list;
	}

}
