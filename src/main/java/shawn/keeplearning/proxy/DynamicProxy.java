package shawn.keeplearning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @date 2018/7/24 16:04
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class DynamicProxy implements InvocationHandler {
	private Object subject;
	public DynamicProxy(Object subject) {
		this.subject = subject;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before rent house");
		System.out.println("Method:" + method);
		method.invoke(subject, args);
		System.out.println("after rent house");
		return null;
	}
}
