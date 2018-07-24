package shawn.keeplearning.proxy;

import java.lang.reflect.Proxy;

/**
 * @date 2018/7/24 16:07
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class Client {
	public static void main(String[] args) {
		Subject realSubject = new RealSubject();
		DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
		Subject subject = (Subject) Proxy.newProxyInstance(dynamicProxy.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), dynamicProxy);
		System.out.println(subject.getClass().getName());
		subject.rent();
		subject.hello("world");
	}
}
