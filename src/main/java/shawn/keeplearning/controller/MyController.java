package shawn.keeplearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shawn.keeplearning.service.EventService;
import shawn.keeplearning.service.TestBean;

import java.util.Map;

/**
 * @date 2018/5/25 13:35
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
@Controller
public class MyController {

	@Autowired
	private TestBean testBean;
	@Autowired
	private EventService eventService;

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

	@RequestMapping("/touch_event.json")
	@ResponseBody
	ResponseEntity<?> touchEvent(@RequestBody Map<String, Object> params) {
		eventService.touchEvent((String)params.get("content"));
		return new ResponseEntity(params, new HttpHeaders(), HttpStatus.OK);
	}
}
