package shawn.keeplearning.jdk8;

/**
 * @date 2018/8/1 10:04
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */

/**
 * vm options:
 * -XX:PermSize=128M						// init size of PermGen, only for 1.7 or lower version
 * -XX:MaxPermSize=128M						// max size of PermGen, only for 1.7 or lower version
 * -Xms512M									// init size of heap
 * -Xmx512M									// max size of heap
 * -Xmn256M									// set size of YoungGen to 256M
 * -XX:SurvivorRatio=8						// Eden:S1:S2 = 8:1:1
 * -XX:NewSize=7g							// init size of YoungGen
 * -XX:MaxNewSize=7g						// max size of YoungGen
 * -XX:+PrintTenuringDistribution			// print object age in survivor after Minor GC
 * -XX:MaxTenuringThreshold=10				// Threshold that how many times of Minor GC a object will move from YoungGen to OldGen
 * -XX:TargetSurvivorRatio=90				// 设定survivor区的目标使用率,默认50
 * -XX:+UseCMSInitiatingOccupancyOnly		// 只是用设定的回收阈值(上面指定的70%),如果不指定,JVM仅在第一次使用设定值,后续则自动调整.
 * -XX:CMSInitiatingOccupancyFraction=40	// 是指设定CMS在对内存占用率达到40%的时候开始GC(因为CMS会有浮动垃圾,所以一般都较早启动GC)
 * -XX:-UseAdaptiveSizePolicy				// 自动选择年轻代大小和eden,s1,s1的比例. +为自动选择, -为不自动选择
 * -XX:+UseConcMarkSweepGC					// 设置JVM堆的老年代使用CMS并发收集器,设置该参数后,-XX:NewRatio参数失效,但-Xmn参数依然有效
 * -XX:+UseParNewGC							// 设置新生代使用并发收集器
 * -verbose:gc								// equals to -XX:+PrintGC, is the alias of -XX:+PrintGC
 * -XX:+PrintGCDetails						// set -XX:+PrintGCc automatic
 * -XX:+PrintGCDateStamps
 * -XX:+PrintHeapAtGC
 * -XX:+PrintGCApplicationStoppedTime
 * -XX:GCLogFileSize=50M
 * -Xloggc:/var/log/gc.log
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGCLogFiles=5
 */
public class JvmTest {
	private static final int _1MB = 1024*1024;

	/**
	 * Endless Full GC (Ergonomics)
	 * vm options:
	 -Xms512M
	 -Xmx512M
	 -Dcom.sun.management.jmxremote.port=9999
	 -Dcom.sun.management.jmxremote.authenticate=false
	 -Dcom.sun.management.jmxremote.ssl=false
	 -XX:+PrintGCDetails
	 -XX:+PrintGCDateStamps
	 */
	public static void t1() {
		while (true) {
			int[] a = new int[40 * _1MB];
			sleep(1000);
		}
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		t1();
	}
}
