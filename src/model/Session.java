//Session method store user input for Main model class

package model;

public class Session {
	
	private String userInput;
	
	//method to set user input
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	
	//method to get user input
	public String getUserInput() {
		return userInput;
	}
}
