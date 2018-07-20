package shawn.keeplearning.bazza;

import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @date 2018/7/19 16:12
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class ReentrantLockTest implements Runnable {

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	Unsafe s = Unsafe.getUnsafe();
	int lock;
	public ReentrantLockTest(int lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		try {
			if (1 == lock) {
				lock1.tryLock();
				Thread.sleep(500);
				lock2.lockInterruptibly();
				System.out.println(Thread.currentThread().getName() + ", 执行完毕!");
			} else {
				lock2.lockInterruptibly();
				Thread.sleep(500);
				lock1.lockInterruptibly();
				System.out.println(Thread.currentThread().getName() + ", 执行完毕!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getName() + ", 退出!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest rlt1 = new ReentrantLockTest(1);
		ReentrantLockTest rlt2 = new ReentrantLockTest(2);
		Thread thread1 = new Thread(rlt1, "线程1");
		Thread thread2 = new Thread(rlt2, "线程2");
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
		thread2.interrupt();
	}
}
