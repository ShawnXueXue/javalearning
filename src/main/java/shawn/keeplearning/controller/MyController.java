package shawn.keeplearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2018/5/25 13:35
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@Controller
public class MyController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World";
	}
}
