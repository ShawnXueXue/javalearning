package shawn.keeplearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @date 2018/7/18 11:27
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@EnableAsync
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
