package com.cy.pj.common.config;
import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {
	 /**
	  * 配置shiro框架中的缓存管理器，借助Shiro框架中
	  * 内置缓存对象对用户权限信息进行cache操作。
	  * @return
	  */
	 //@Bean("shiroCacheManager")
	 public CacheManager cacheManager() {
		 return new MemoryConstrainedCacheManager();
	 }
	 /**
	  * 配置记住我
	  * @return
	  */
	 public CookieRememberMeManager cookieRememberMeManager() {
		 CookieRememberMeManager cManager=new CookieRememberMeManager();
		 SimpleCookie cookie=new SimpleCookie("rememberMe");
		 cookie.setMaxAge(7*24*60*60);
		 cManager.setCookie(cookie);
		 return cManager;
	 }
	 /**
	  * 配置会话管理器对象(用户登录成功以后的状态信息可以
	  * 存储到会话对象中)
	  * @param realm
	  * @return
	  */
	 public SessionManager sessionManager() {
		 DefaultWebSessionManager sManager=
		 new DefaultWebSessionManager();
		 sManager.setGlobalSessionTimeout(30*60*1000);
		 return sManager;
	 }
	 @Bean
	 public SecurityManager securityManager(
			 Realm realm) {
		 DefaultWebSecurityManager sManager=
		 new DefaultWebSecurityManager();
		 sManager.setRealm(realm);
		 sManager.setCacheManager(cacheManager());
		 sManager.setRememberMeManager(cookieRememberMeManager());
		 sManager.setSessionManager(sessionManager());
		 return sManager;
	 }
	 @Bean//默认key为方法名
	 public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
		 //构建bean对象，通过此对象创建过滤器工厂。
		 ShiroFilterFactoryBean fBean=
		 new ShiroFilterFactoryBean();
		 //注入SecurityManager
		 fBean.setSecurityManager(securityManager);
		 //设置登录url
		 fBean.setLoginUrl("/doLoginUI");
		 //设置过滤规则
		 LinkedHashMap<String,String> cMap=new LinkedHashMap<>();
		 cMap.put("/bower_components/**", "anon");
		 cMap.put("/build/**", "anon");
		 cMap.put("/dist/**", "anon");
		 cMap.put("/plugins/**", "anon");//anon表示允许匿名访问
		 cMap.put("/user/doLogin", "anon");//anon表示允许匿名访问
		 cMap.put("/doLogout", "logout");//anon表示允许匿名访问
		 //cMap.put("/**", "authc");//表示需要认证以后访问
		 cMap.put("/**", "user");//记住我功能需要将authc改为user
		 fBean.setFilterChainDefinitionMap(cMap);
		 return fBean;
	 }
	 //===========授权配置=================
	 /**
	  * 配置shiro框架中bean对象的生命周期管理器，
	  * 此对象的编写要按照spring框架的标准进行实现。
	  */
	 @Bean //默认bean的名字为方法名
	 public LifecycleBeanPostProcessor 
	        lifecycleBeanPostProcessor() {
		 return new LifecycleBeanPostProcessor();
	 }
	 /**
	  * 此对象会在Spring容器启动时，
	  * 扫描所有的Advisor(通知)对象
	  * ，基于Advisor对象中切入点(Pointcut)
	  * 的描述，进行代理对象的创建。
	  */
	 @DependsOn("lifecycleBeanPostProcessor")
	 @Bean
	 public DefaultAdvisorAutoProxyCreator 
	     defaultAdvisorAutoProxyCreator() {
		 return new DefaultAdvisorAutoProxyCreator();
	 }
	 /**配置Advisor对象，在此对象中定义切入点以及要在此切入点
	  * 进行实现功能功能扩展(advice)*/
	 @Bean
	 public AuthorizationAttributeSourceAdvisor 
	 authorizationAttributeSourceAdvisor(
			 SecurityManager securityManager) {
		 AuthorizationAttributeSourceAdvisor advisor=
		 new AuthorizationAttributeSourceAdvisor();
		 advisor.setSecurityManager(securityManager);
		 return advisor;
	 }
	 
}






