package shawn.keeplearning.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @date 2018/7/17 15:06
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@Async
@Component
public class UserOneListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof ContentEvent) {
			System.out.println("[User1Listener]msg;" + applicationEvent.getSource());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[User1Listener]Curt thread:" + Thread.currentThread().getName());
		}
	}
}
