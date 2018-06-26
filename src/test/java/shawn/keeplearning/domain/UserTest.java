package shawn.keeplearning.domain;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.*;

/**
 * @date 2018/6/26 13:56
 * Author: Shawn
 * Contact me: shawnglhf@gmail.com
 */
public class UserTest {

	private static final String FILE_PATH = "c:/user.txt";

	@Test
	public void ObjSerialize() {
		User user = new User();
		user.setUserName("Shawn");
		user.setPasswd("123456");
		user.setNation("CHINA");

		System.out.println("Before Serializable: ");
		System.out.println("userName: " + user.getUserName());
		System.out.println("password: " + user.getPasswd());
		System.out.println("nation: " + user.getNation());

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			oos.writeObject(user);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		user.setNation("AMERICA");

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
			User userNew = (User) ois.readObject();
			ois.close();

			System.out.println("After Serializable: ");
			System.out.println("userName: " + userNew.getUserName());
			System.out.println("password: " + userNew.getPasswd());
			System.out.println("nation: " + userNew.getNation());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ObjSerializeJson() {
		User user = new User();
		user.setUserName("Shawn");
		user.setPasswd("123456");
		user.setNation("CHINA");

		System.out.println("Before Serializable: ");
		System.out.println("userName: " + user.getUserName());
		System.out.println("password: " + user.getPasswd());
		System.out.println("nation: " + user.getNation());

		Gson gson = new Gson();
		String s = gson.toJson(user);
		user.setNation("AMERICA");
		User userNew = gson.fromJson(s, User.class);

		System.out.println("After Serializable: ");
		System.out.println("userName: " + userNew.getUserName());
		System.out.println("password: " + userNew.getPasswd());
		System.out.println("nation: " + userNew.getNation());
	}
}
