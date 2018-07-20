package shawn.keeplearning.bazza;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @date 2018/7/19 13:22
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class ThreadPoolTest {

	private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	private static final int RUNNING    = -1 << COUNT_BITS;
	private static final int SHUTDOWN   =  0 << COUNT_BITS;
	private static final int STOP       =  1 << COUNT_BITS;
	private static final int TIDYING    =  2 << COUNT_BITS;
	private static final int TERMINATED =  3 << COUNT_BITS;

	private static int ctlOf(int rs, int wc) { return rs | wc; }

	@Test
	public void m1() {
		System.out.println(ctl);
	}
}
