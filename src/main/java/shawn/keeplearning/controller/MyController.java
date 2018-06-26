package shawn.keeplearning.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2018/5/25 13:35
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@Controller
@EnableAutoConfiguration
public class MyController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(MyController.class, args);
	}
}
