//premium user class represents functionality extends user class

package model;

public class PremiumUser extends User{
	
	private String userEmail;
	private double userCredits;
	
	
	public PremiumUser(String username, String password, String firstName, String lastName, String email) {
        super(username, password, firstName, lastName); 
        this.userEmail = email;
        this.userCredits = 0.0; 
    }
	
	public PremiumUser(String username, String password, String firstName, String lastName, String email, double userCredits) {
	    super(username, password, firstName, lastName); 
	    this.userEmail = email; 
	    this.userCredits = userCredits; 
	}
	
	//get premium user email
	public String getUserEmail() {
		return userEmail;
	}
	
	//get premium user credits
	public double getUserCredits() {
		return userCredits;
	}
	
	//set premium user email
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	//set premium user credits
	public void setUserCredits(double userCredits) {
		this.userCredits = userCredits;
	}
}
