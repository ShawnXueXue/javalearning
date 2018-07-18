package shawn.keeplearning.service;

import org.springframework.stereotype.Component;

/**
 * @date 2018/6/25 15:02
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@Component("testBean")
public class TestBean {

	public TestBean() {
		System.out.println("TestBean Constructed!");
	}

	public String test1() {
		return "This is test1";
	}
}
