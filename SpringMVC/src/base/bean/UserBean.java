package base.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserBean {

	@NotNull
	@Size(min = 6, max = 50)
	private String userName;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String password;
	
	@NotNull
	@Size(min = 6, max = 100)
	private String eMail;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
