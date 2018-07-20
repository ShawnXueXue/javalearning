package shawn.keeplearning.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shawn.keeplearning.service.EventService;

/**
 * @date 2018/7/17 15:17
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {UserOneListener.class,
		UserTwoListener.class,
		UserThreeListener.class,
		UserFourListener.class,
		EventService.class})
public class ContentEventTest {

	@Autowired
	private EventService eventService;

	@Test
	public void test1() {
		eventService.touchEvent("HI");
		System.out.println("end");
	}
}
