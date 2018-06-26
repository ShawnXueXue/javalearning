package shawn.keeplearning.domain;

import java.io.Serializable;

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
}
