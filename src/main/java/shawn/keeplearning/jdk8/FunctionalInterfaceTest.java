package shawn.keeplearning.jdk8;

/**
 * @date 2018/7/30 18:04
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
@FunctionalInterface
public interface FunctionalInterfaceTest {
	void absFunc();
	boolean equals(Object obj);
	default void defaultMethod() {
		System.out.println("FunctionalInterfaceTest.defaultMethod!");
	}
	static void staticMethod() {
		System.out.println("FunctionalInterfaceTest.staticMethod!");
	}
	// @FunctionalInterfaceTest can not contain two or more abstract function
//	void function2();
}
