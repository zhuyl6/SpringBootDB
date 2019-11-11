package com.cy.pj.sys.service;
import java.util.concurrent.Future;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
public interface SysLogService {
	/**
	 * 写用户行为日志到数据库
	 * @param entity
	 * @return
	 */
	Future<Integer> saveObject(SysLog entity);
	/**
	 * 基于id执行删除操作
	 * @param ids
	 * @return
	 */
	int deleteObjects(Integer... ids);
     /**
      * 分页查询当前页记录
      * @param username
      * @param pageCurrent
      * @return
      */
	 PageObject<SysLog> findPageObjects(
			 String username,
			 Integer pageCurrent);
}
