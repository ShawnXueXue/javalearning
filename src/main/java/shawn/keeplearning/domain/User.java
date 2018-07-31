package shawn.keeplearning.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 2018/6/26 10:36
 * Author: Shawn
 * Contact me:shawnglhf@gmail.com
 */
public class User implements Serializable {

	private static final long serialVersionUID = -896858145157594064L;

	private String userName;
	private transient String passwd;
	private static String nation;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		User.nation = nation;
	}

	private static boolean isValid(String input) {
		List<String> stack = new ArrayList<String>();
		for (int i = 0; i < input.length(); ++i) {
			char c = input.charAt(i);
			if ('(' == c || '[' == c || '{' == c) {
				stack.add(String.valueOf(c));
			} else if (')' == c || ']' == c || '}' == c) {
				if (stack.size() <= 0)
					return false;
				stack.remove(stack.size() - 1);
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
//		System.out.println(isValid("([a{[w]}]{})"));
		String a = "a/bc/ccc";
		System.out.println(a.substring(a.lastIndexOf('/') - 1, a.length()));
	}
}
