package shawn.keeplearning.jdk8;

/**
 * @date 2018/7/30 18:15
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class FunctionalInterfaceImpl implements FunctionalInterfaceTest {
	private FunctionalInterfaceTest t;
	public FunctionalInterfaceImpl() {}
	public FunctionalInterfaceImpl(FunctionalInterfaceTest t) {
		this.t = t;
	}
	@Override
	public void absFunc() {
		if (null == t)
			System.out.println("FunctionalInterfaceImpl.absFunc!");
		else
			t.absFunc();
	}

	@Override
	public void defaultMethod() {
		System.out.println("FunctionalInterfaceImpl.defaultMethod!");
	}

	public static void main(String[] args) {
		FunctionalInterfaceImpl functionalInterface = new FunctionalInterfaceImpl();
		functionalInterface.absFunc();
		functionalInterface.defaultMethod();
		FunctionalInterfaceTest.staticMethod();

		new FunctionalInterfaceImpl(() -> {
			System.out.println("lamda from main!");
		}).absFunc();
	}
}
