package shawn.keeplearning.concurrent.DelayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @date 2018/8/3 15:33
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class Consumer implements Runnable {
	private DelayQueue<DelayItem> queue;
	public Consumer(DelayQueue<DelayItem> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		while (true) {
			try {
				DelayItem take = queue.take();
				System.out.println("Consume DelayItem: (" + take.getId() + ", " + take.getContent() + ")");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DelayQueue<DelayItem> q = new DelayQueue<>();
		DelayItem di1 = new DelayItem(1, "World", 1000, TimeUnit.MILLISECONDS);
		DelayItem di2 = new DelayItem(2, "Hello", 3000, TimeUnit.MILLISECONDS);
		q.offer(di1);
		q.offer(di2);
		new Thread(new Consumer(q)).start();
	}
}
