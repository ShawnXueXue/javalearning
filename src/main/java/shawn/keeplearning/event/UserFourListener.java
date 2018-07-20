package shawn.keeplearning.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @date 2018/7/17 15:12
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
//@Async
@Component
public class UserFourListener implements SmartApplicationListener {
	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
		return aClass == ContentEvent.class;
	}

	@Override
	public boolean supportsSourceType(Class<?> aClass) {

		return aClass == String.class;
	}


	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		System.out.println("[User4Listener]msg;" + applicationEvent.getSource());
		System.out.println("[User4Listener]Curt thread:" + Thread.currentThread().getName());
	}

	@Override
	public int getOrder() {
		return 2;
	}
}
