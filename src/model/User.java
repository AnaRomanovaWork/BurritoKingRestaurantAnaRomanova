//user class represents user functionality

package model;

public class User {
	private String userName;
	private String userPassword;
	private String userFirstName;
	private String userLastName;
	
	public User(String userName, String userPassword, String userFirstName, String userLastName){
		this.userName = userName;
		this.userPassword = userPassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
	}
	
	//get user name method
	public String getUserName() {
		return userName;
	}
	
	//get user password
	public String getUserPassword() {
		return userPassword;
	}
	
	//get user first name
	public String getUserFirstName() {
		return userFirstName;
	}
	
	//get user last name
	public String getUserLastName() {
		return userLastName;
	}
	
	//set username
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//set user password
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	//set user first name
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	//set user last name
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

}

