package shawn.keeplearning.zk;

import java.util.concurrent.TimeUnit;

/**
 * @date 2018/7/31 17:20
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class LockTest {

	public static void lock() {
		ZkLock instance = ZkLock.getInstance();
		for (int i = 1; i < 3; ++i) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + " begin.");
					instance.lock();
					instance.lock();
					System.out.println(Thread.currentThread().getName() + " lock.");
					Thread.sleep(4000);
					instance.unlock();
					instance.unlock();
					System.out.println(Thread.currentThread().getName() + " unlock.");
					System.out.println(Thread.currentThread().getName() + " end.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Thread" + i).start();
		}
	}

	public static void lockWithTimeout() {
		ZkLock instance = ZkLock.getInstance();
		for (int i = 1; i < 3; ++i) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + " begin.");
					if (instance.lock(2000, TimeUnit.MILLISECONDS)) {
						System.out.println(Thread.currentThread().getName() + " lock.");
						Thread.sleep(4000);
						instance.unlock();
					} else {
						System.out.println(Thread.currentThread().getName() + " lock timeout. Give up.");
					}
					System.out.println(Thread.currentThread().getName() + " end.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, "Thread" + i).start();
		}
	}

	public static void main(String[] args) {
		lock();
//		lockWithTimeout();
	}
}
