package shawn.keeplearning.concurrent.DelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @date 2018/8/3 15:07
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class DelayItem implements Delayed {
	private int id;
	private String content;
	private long excuteTime;
	public DelayItem(int id, String content, long delayTime, TimeUnit timeUnit) {
		this.id  = id;
		this.content = content;
		this.excuteTime = timeUnit.toNanos(delayTime) + System.nanoTime();
	}
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		DelayItem delayItem = (DelayItem)o;
		return this.excuteTime > delayItem.excuteTime ? 1 : this.excuteTime < delayItem.excuteTime ? -1 : 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(long excuteTime) {
		this.excuteTime = excuteTime;
	}
}
