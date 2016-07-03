package com.nd.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}
	
	public static <T> T getBean(String beanId, Class<T> cls){
		return (T)applicationContext.getBean(beanId, cls);
	}
	
	public static Object  getBean(String beanId){
		return applicationContext.getBean(beanId);
	}
}
