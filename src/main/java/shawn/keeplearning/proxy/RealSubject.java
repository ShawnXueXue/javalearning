package shawn.keeplearning.proxy;

/**
 * @date 2018/7/24 16:03
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class RealSubject implements Subject {
	@Override
	public void rent() {
		System.out.println("real rent!");
	}

	@Override
	public void hello(String name) {
		System.out.println(String.format("Hello: %s", name));
	}
}
