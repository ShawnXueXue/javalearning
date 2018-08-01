package shawn.keeplearning.jdk8;

/**
 * @date 2018/8/1 10:04
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */

/**
 * vm options:
 * -XX:PermSize=128M // init size of PermGen, only for 1.7 or lower version
 * -XX:MaxPermSize=128M // max size of PermGen, only for 1.7 or lower version
 * -Xms512M // init size of heap
 * -Xmx512M // max size of heap
 * -XX:SurvivorRatio=8 // Eden:S1:S2 = 8:1:1
 * -XX:NewSize=7g // init size of YoungGen
 * -XX:MaxNewSize=7g // max size of YoungGen
 * -XX:+PrintTenuringDistribution // print object age in survivor
 * -XX:MaxTenuringThreshold=10 // Threshold that how many times of Minor GC a object will move from YoungGen to OldGen
 * -XX:TargetSurvivorRatio=90
 * -XX:+UseCMSInitiatingOccupancyOnly
 * -XX:CMSInitiatingOccupancyFraction=40
 * -XX:-UseAdaptiveSizePolicy
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:+PrintHeapAtGC
 * -XX:+PrintGCApplicationStoppedTime
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGCLogFiles=5
 * -XX:GCLogFileSize=50M
 * -Xloggc:/var/log/gc.log
 * -verbose:gc
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseParNewGC
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
