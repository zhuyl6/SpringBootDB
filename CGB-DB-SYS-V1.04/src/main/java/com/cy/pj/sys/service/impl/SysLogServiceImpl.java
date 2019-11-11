package com.cy.pj.sys.service.impl;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
    //has a
	@Autowired
	private SysLogDao sysLogDao;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Async//表示使用异步方式写日志,默认池对象ThreadPoolTaskExecutor
	//@Async("asyncExecutor")//其中名字为池对象的名字
	@Override
	public Future<Integer> saveObject(SysLog entity) {
		String tName=Thread.currentThread().getName();
		System.out.println("log.save.thread.name="+tName);
		int rows=sysLogDao.insertObject(entity);
		//try{Thread.sleep(10000);}catch(Exception e) {}
		return new AsyncResult<Integer>(rows);
	}//假如异步操作中调用的方法有返回值，可以使用AsyncResult对象进行封装。
	
	@RequiresPermissions("sys:log:delete")
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("参数值无效");
		//2.执行删除
		int rows=sysLogDao.deleteObjects(ids);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//3.返回结果
		return rows;
	}
	
	@Override
	public PageObject<SysLog> findPageObjects(
			String username,Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=sysLogDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=
		sysLogDao.findPageObjects(username,
				startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
}






