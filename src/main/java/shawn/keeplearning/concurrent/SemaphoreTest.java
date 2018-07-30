package shawn.keeplearning.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @date 2018/7/30 13:31
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		int nums = 50;
		Semaphore semaphore =  new Semaphore(10);
		for (int i = 0; i < nums; ++i) {
			int finali = i;
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println("Customer " + finali + " is picking up...");
					Thread.sleep(1000);
					System.out.println("Customer " + finali + " finished.");
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
