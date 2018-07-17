package shawn.keeplearning.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @date 2018/7/17 15:08
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@Async
@Component
public class UserTwoListener implements ApplicationListener<ContentEvent> {
	@Override
	public void onApplicationEvent(ContentEvent contentEvent) {
		System.out.println("[UserTwoListener]msg;" + contentEvent.getSource());
		System.out.println("[UserTwoListener]Curt thread:" + Thread.currentThread().getName());
	}
}
