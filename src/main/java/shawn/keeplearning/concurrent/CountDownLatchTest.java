package shawn.keeplearning.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @date 2018/7/30 13:15
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch countDownLatch = new CountDownLatch(5);
		System.out.println("Begin to collect 5 ending signals.");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Get all 5 ending signal, shut down.");
			}
		}).start();
		for (int i = 0; i < 5; ++i) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("1 ending signal.");
					countDownLatch.countDown();
				}
			}).start();
		}
	}
}
