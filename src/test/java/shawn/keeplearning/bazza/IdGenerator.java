package shawn.keeplearning.bazza;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @date: 2018/7/25
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class IdGenerator {
	// idea 1
//	ConcurrentMap<String, AtomicLong> name2id = new ConcurrentHashMap<String, AtomicLong>();
//	public long incrementAndGet(String name) {
//		AtomicLong zero = new AtomicLong(1);
//		AtomicLong current;
//		if (null != (current = name2id.putIfAbsent(name, zero))) {
//			current.incrementAndGet();
//			return current.get();
//		} else {
//			return 1L;
//		}
//	}

	// idea 2
//	Map<String, AtomicLong> name2id = new HashMap<String, AtomicLong>();
//	public long incrementAndGet(String name) {
//		AtomicLong atomicLong = name2id.get(name);
//		synchronized ("1") {
//			if (null == atomicLong) {
//				name2id.put(name, new AtomicLong(1L));
//				return 1L;
//			} else {
//				return atomicLong.incrementAndGet();
//			}
//		}
//	}

	//idea 3
	Map<String, AtomicLong> name2id = new HashMap<String, AtomicLong>();
	ReentrantLock lock = new ReentrantLock();
	public long incrementAndGet(String name) {
		lock.lock();
		AtomicLong atomicLong = name2id.get(name);
		if (null == atomicLong) {
			name2id.put(name, new AtomicLong(1L));
			lock.unlock();
			return 1L;
		} else {
			lock.unlock();
			return atomicLong.incrementAndGet();
		}
	}
}
