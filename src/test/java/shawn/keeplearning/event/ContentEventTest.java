package shawn.keeplearning.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @date 2018/7/17 15:17
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {UserOneListener.class, UserTwoListener.class, UserThreeListener.class, UserFourListener.class})
public class ContentEventTest {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Test
	public void test1() {
		System.out.println(Thread.currentThread().getName());
		applicationEventPublisher.publishEvent(new ContentEvent("news a"));
		System.out.println("end");
	}

	@Bean
	ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
		ThreadPoolTaskExecutor eventExecutor = new ThreadPoolTaskExecutor();
		eventExecutor.setCorePoolSize(50);
		eventExecutor.setMaxPoolSize(200);
		eventExecutor.setQueueCapacity(150);
		eventExecutor.setThreadNamePrefix("EventAsyncExecutor-");
		eventExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		eventExecutor.initialize();
		eventMulticaster.setTaskExecutor(eventExecutor);
		return eventMulticaster;
	}
}
