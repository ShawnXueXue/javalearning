package shawn.keeplearning.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @date 2018/7/31 11:51
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class ZkLock implements Lock {
	private static final String CONNECT_STRING = "10.2.27.122:6380";
	private static final String LOCK_NAMESPACE = "/shawn/lock";
	private static final String LOCK_PREFIX = "lockQueue_";
	private static ZkLock instance;
	private static ZooKeeper zk;

	private ConcurrentMap<Thread, LockData> threadLock = new ConcurrentHashMap<>();

	private static class LockData {
		Thread owningThread;
		String lockingSeq;
		AtomicInteger lockCount = new AtomicInteger(1);
		private LockData(Thread owningThread, String lockingSeq) {
			this.owningThread = owningThread;
			this.lockingSeq = lockingSeq;
		}
	}

	public static ZkLock getInstance() {
		if (null == instance) {
			instance = new ZkLock();
		}
		return instance;
	}

	private ZkLock() {
		try {
			zk = new ZooKeeper(CONNECT_STRING, 6000, event -> {
				System.out.println("Receive event " + event);
				if (Watcher.Event.KeeperState.SyncConnected == event.getState())
					System.out.println("Connection is established...");
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkRootPath() {
		try {
			if (zk.exists(LOCK_NAMESPACE, false) == null)
				zk.create(LOCK_NAMESPACE, "DistributeLock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean lock(long time, TimeUnit unit) throws Exception {
		checkRootPath();
		Thread thread = Thread.currentThread();
		LockData lockData = threadLock.get(thread);
		if (null != lockData) {
			lockData.lockCount.incrementAndGet();
			return true;
		}
		String lockSeq = tryLock(time, unit);
		if (null != lockSeq) {
			threadLock.put(thread, new LockData(thread, lockSeq));
			return true;
		}
		return false;
	}

	@Override
	public void lock() throws Exception {
		if (!lock(-1, null))
			throw new IOException("Lost connection while trying to acquire lock: " + LOCK_NAMESPACE);
	}

	@Override
	public void unlock() throws Exception {
		Thread thread = Thread.currentThread();
		LockData lockData = threadLock.get(thread);
		if (null == lockData)
			throw new RuntimeException(String.format("Current thread do not own lock: %s", LOCK_NAMESPACE));
		int i = lockData.lockCount.decrementAndGet();
		if (i > 0)
			return;
		if (i < 0)
			throw new RuntimeException(String.format("Lock count has gone negative for lock: %s", LOCK_NAMESPACE));
		try {
			deleteLockSeq(lockData.lockingSeq);
		} finally {
			threadLock.remove(thread);
		}
	}

	private void deleteLockSeq(String lockSeq) throws KeeperException, InterruptedException {
		zk.delete(lockSeq, -1);
	}

	private String tryLock(long time, TimeUnit unit) throws KeeperException, InterruptedException {
		long startTime = System.currentTimeMillis();
		Long millisToWait = unit == null ? null : unit.toMillis(time);
		boolean haveTheLock = false;
		boolean delete = false;
		String lockSeq = zk.create(LOCK_NAMESPACE + '/' + LOCK_PREFIX,
							Thread.currentThread().getName().getBytes(),
							ZooDefs.Ids.OPEN_ACL_UNSAFE,
							CreateMode.EPHEMERAL_SEQUENTIAL);
		String lockSeqNode = lockSeq.substring(lockSeq.lastIndexOf('/') + 1, lockSeq.length());
		try {
			while (!haveTheLock) {
				List<String> children = zk.getChildren(LOCK_NAMESPACE, false);
				Collections.sort(children);
				int index = 0;
				for (; index < children.size(); ++index) {
					if (children.get(index).equals(lockSeqNode))
						break;
				}
				if (index >= children.size())
					throw new RuntimeException(String.format("Can not find created lockSeqNode in lock: %s", LOCK_NAMESPACE));
				if (0 == index) {
					haveTheLock = true;
				} else {
					synchronized (this) {
						zk.exists(LOCK_NAMESPACE + '/' + children.get(index - 1), event -> notifyFromWatcher());
						if (null != millisToWait) {
							millisToWait -= (System.currentTimeMillis() - startTime);
							startTime = System.currentTimeMillis();
							if ( millisToWait <= 0 )
							{
								delete = true;    // timed out - delete our node
								break;
							}
							wait(millisToWait);
						} else
							wait();
					}
				}
			}
		} catch (Exception e) {
			delete = true;
			throw e;
		} finally {
			if (delete)
				deleteLockSeq(lockSeq);
		}
		return haveTheLock ? lockSeq : null;
	}

	private synchronized void notifyFromWatcher() {
		notifyAll();
	}
}
