package shawn.keeplearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shawn.keeplearning.service.TestBean;

/**
 * @date 2018/5/25 13:35
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@Controller
public class MyController {

	@Autowired
	private TestBean testBean;

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World";
	}
	@RequestMapping("/test")
	@ResponseBody
	String test() {
		return testBean.test1();
	}
}
