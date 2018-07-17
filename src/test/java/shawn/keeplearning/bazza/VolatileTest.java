package shawn.keeplearning.bazza;

/**
 * @date 2018/6/26 19:29
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */

/**
 * - volatile语义
 * 1) 可以保证可见性和有序性
 * 2) 对一个volatile域的写，happens-before于任意后续对这个volatile域的读
 * - 可以使用volatile的场景:
 * 1）对变量的写操作不依赖于当前值
 * 2）该变量没有包含在具有其他变量的不变式中
 */
public class VolatileTest {

	public volatile int inc = 0;
	public void increase() {
		inc++;
	}

	public static void main(String[] args) {
		final VolatileTest test = new VolatileTest();
		for (int i = 0; i < 10; ++i) {
			new Thread(){
				public void run() {
					for (int j = 0; j < 1000; ++j) {
						test.increase();
					}
				}
			}.start();
		}

		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.println(test.inc);
	}
}
