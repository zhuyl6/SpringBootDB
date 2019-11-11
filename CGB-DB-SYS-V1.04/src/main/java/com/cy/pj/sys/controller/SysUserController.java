package com.cy.pj.sys.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.util.ShiroUtils;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;


@RestController
@RequestMapping("/user/")
public class SysUserController {//BeanFactory
	 @Autowired
	 private SysUserService sysUserService;
	 
	 @RequestMapping("doGetLoginUser")
	 public JsonResult doGetLoginUser() {
		 return new JsonResult(ShiroUtils.getLoginUser());
	 }
	 
	 @RequestMapping("doUpdatePassword")
	 public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		 sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("update ok");
	 }

	 
	 @RequestMapping("doLogin")
	 public JsonResult doLogin(
			 String username,String password,
			 boolean isRememberMe) {
		 System.out.println("doLogin(..)");
		 //1.封装用户信息
		 UsernamePasswordToken token=new UsernamePasswordToken();
		 token.setUsername(username);
		 token.setPassword(password.toCharArray());
		 //2.提交用户信息(借助Subject对象)
		 //获取Subject对象
		 Subject subject=SecurityUtils.getSubject();
		 //提交用户信息进行认证
		 if(isRememberMe)token.setRememberMe(true);
		 subject.login(token);
		 return new JsonResult("登录ok");
	 }
	 @RequestMapping("doFindObjectById")
	 public JsonResult doFindObjectById(
			 Integer id) {
		 return new JsonResult(sysUserService.findObjectById(id));
	 }
	 @RequestMapping("doUpdateObject")
	 public JsonResult doUpdateObject(
			 SysUser entity,Integer[] roleIds) {
		 sysUserService.updateObject(entity, roleIds);
		 return new JsonResult("update ok");
	 }
	 @RequestMapping("doSaveObject")
	 public JsonResult doSaveObject(
			 SysUser entity,Integer[] roleIds) {
		 sysUserService.saveObject(entity, roleIds);
		 return new JsonResult("save ok");
	 }
	 
	 @RequestMapping("doValidById")
	 public JsonResult doValidById(Integer id,Integer valid) {
		 SysUser user=(SysUser)
		 SecurityUtils.getSubject().getPrincipal();
		 sysUserService.validById(id, valid,user.getUsername());
		 return new JsonResult("update ok");
	 }
	 @GetMapping("doFindPageObjects")
     public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
    	 return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
     }
}
