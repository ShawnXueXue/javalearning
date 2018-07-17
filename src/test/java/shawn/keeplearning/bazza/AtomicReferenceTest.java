package shawn.keeplearning.bazza;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @date 2018/7/4 14:31
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class AtomicReferenceTest {

	private static String initStr = "init";

	@Test
	public void t1() {
		AtomicReference<String> aStr = new AtomicReference<String>(initStr);
		System.out.println(aStr.getAndSet("new"));
		System.out.println(aStr.compareAndSet("new", "new1"));
		System.out.println(aStr);
	}
	@Test
	public void t2() {
		AtomicInteger ai = new AtomicInteger(0);
		ai.incrementAndGet();
	}
}
