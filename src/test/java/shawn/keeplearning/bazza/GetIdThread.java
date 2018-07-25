package shawn.keeplearning.bazza;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2018/7/22
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class GetIdThread implements Runnable {
	private IdGenerator ig;

	GetIdThread(IdGenerator ig) {
		this.ig = ig;
	}


	@Override
	public void run() {
		String tname = Thread.currentThread().getName();
		for (int i = 0; i < 20000; ++i) {
			System.out.println(tname + ",thread id:" + Thread.currentThread().getId() + ", get id: " + ig.incrementAndGet(tname));
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long l = System.currentTimeMillis();
		IdGenerator ig = new IdGenerator();
		List<Thread> cache = new ArrayList<Thread>();
		for (int i = 0; i < 5; ++i) {
			GetIdThread t1 = new GetIdThread(ig);
			GetIdThread t2 = new GetIdThread(ig);
			Thread thread1 = new Thread(t1, "AppId");
			Thread thread2 = new Thread(t2, "PoolId");
			thread1.start();
			thread2.start();
			cache.add(thread1);
			cache.add(thread2);
		}
		for (Thread thread : cache) {
			thread.join();
		}
		System.out.println(System.currentTimeMillis() - l + "ms");
	}
}
