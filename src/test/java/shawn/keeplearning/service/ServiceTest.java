package shawn.keeplearning.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @date 2018/6/25 14:45
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ContextAware.class, TestBean.class})
public class ServiceTest {

	@Test
	public void test1() {
		ApplicationContext applicationContext = ContextAware.getApplicationContext();
		Object contextAware = applicationContext.getBean("contextAware");
		System.out.println(applicationContext.getBean("testBean"));
		System.out.println(contextAware.hashCode());
	}
}
