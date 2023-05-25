package model.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	private String user_id;
	private String password;
	
	/* コンストラクタ */
	public UserBean() {
		
	}
	
	/* アクセサメソッド */
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
