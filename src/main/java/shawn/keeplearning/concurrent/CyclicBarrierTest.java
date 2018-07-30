package shawn.keeplearning.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @date 2018/7/30 13:22
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		for (int i = 0; i < 2; ++i) {
			new Thread(() -> {
				String name = Thread.currentThread().getName();
				System.out.println(name + "checkPoint 1");

				try {
					cyclicBarrier.await();
					System.out.println(name + "checkPoint 2");
					cyclicBarrier.await();
					System.out.println(name + "checkPoint 3");
					cyclicBarrier.await();
					System.out.println(name + "checkPoint 4");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
