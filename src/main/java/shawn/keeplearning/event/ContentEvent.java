package shawn.keeplearning.event;

import org.springframework.context.ApplicationEvent;

/**
 * @date 2018/7/17 14:57
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class ContentEvent extends ApplicationEvent {
	public ContentEvent(final String content) {
		super(content);
	}
}
