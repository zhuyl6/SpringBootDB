package com.cy.pj.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cy.pj.common.web.TimeAccessInterceptor;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{

	/**
	  * 注册拦截器并制定拦截规则
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册拦截器
		registry.addInterceptor(new TimeAccessInterceptor())
		//设置要拦截的资源
		.addPathPatterns("/user/doLogin");
	}
    /**
          *  注册过滤器对象DelegatingFilterProxy,
          *  此对象由Spring框架提供，核心作用就是
          *  通过它基于web请求加载指定的Bean对象。      
     * @return
     */
	//@Bean
	public FilterRegistrationBean<DelegatingFilterProxy>  filterRegistrationBean() {
		FilterRegistrationBean<DelegatingFilterProxy> rBean=
		new FilterRegistrationBean<>();
		rBean.setFilter(new DelegatingFilterProxy("shiroFilterFactory"));
		rBean.addUrlPatterns("/*");
		return rBean;
	} 
}






