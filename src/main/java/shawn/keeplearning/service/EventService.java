package shawn.keeplearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import shawn.keeplearning.event.ContentEvent;

/**
 * @date 2018/7/20 10:12
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@Service
public class EventService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Async
	public void touchEvent(String content) {
		System.out.println("Current thread:" + Thread.currentThread().getName());
		applicationEventPublisher.publishEvent(new ContentEvent(content));
	}
}
