package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.service.SysLogService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/log/")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	@RequestMapping("doDeleteObjects")
	
	public JsonResult doDeleteObjects(
			Integer... ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
	@GetMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String username,
			@RequestParam Integer pageCurrent) {
		  return new JsonResult(
		  sysLogService.findPageObjects(username,
				pageCurrent));
	}
}