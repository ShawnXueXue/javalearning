package shawn.keeplearning.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @date 2018/6/25 14:36
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@Service
public class ContextAware implements BeanNameAware, ApplicationContextAware {
	private static ApplicationContext applicationContext;
	@Override
	public void setBeanName(String s) {
		System.out.println("ContextAware bean name: " + s);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ContextAware.applicationContext = applicationContext;
		System.out.println(applicationContext.getBean(ContextAware.class).hashCode());
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
