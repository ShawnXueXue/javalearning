package shawn.keeplearning.zk;

import java.util.concurrent.TimeUnit;

/**
 * @date 2018/7/31 11:50
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public interface Lock {
	void lock() throws Exception;
	boolean lock(long time, TimeUnit unit) throws Exception;
	void unlock() throws Exception;
}
