//class to test functionality of premium user


package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.MainModel;
import model.Model;
import model.PremiumUser;

public class PremiumUserFunctionalityTest {
	private Model model;
	PremiumUser premiumUser = new PremiumUser("Anna", "555", "Anna", "Black", "abc@mail.com", 30);

    @Before
    public void setUp() {
        model = MainModel.getInstance();
        
    }

    //email validation with valid input
	@Test
	public void emailValidationTestValidEmail() {
		String email = "abc@mail.com";
		String outputMessage = model.emailValidation(email);
		assertEquals(outputMessage, "");
	}
	
	//email validation with invalid input
	@Test
	public void emailValidationTestInValidEmail() {
		String email = "abcmail.com";
		String outputMessage = model.emailValidation(email);
		assertEquals(outputMessage, "Invalid email.");
		
	}
	
	//email validation with empty field
	@Test
	public void emailValidationTestEmptyEmail() {
		String email = "";
		String outputMessage = model.emailValidation(email);
		assertEquals(outputMessage, "Email field is empty.\nPlease enter your email");
	}
	
	//test of reduce credit points
	@Test
	public void reduceCreditPoints() {
		model.updateCreditPoints(premiumUser, 60);
		double premiumUserCreditPoints = premiumUser.getUserCredits();
		assertEquals(90, premiumUserCreditPoints, 0.001);
	}
	
	//test of double valid input for reduce credit points
	@Test
	public void validateDoubleInputValidValue() {
		String enteredValue = "10.00";
		double creditPoints = premiumUser.getUserCredits();
		String outputMessage = model.validateDoubleInput(enteredValue, creditPoints);
		assertEquals(outputMessage, "");
	}
	
	//test of double invalid input for reduce credit points
	@Test
	public void validateDoubleInputInvalidValue() {
		String enteredValue = "abc";
		double creditPoints = premiumUser.getUserCredits();
		String outputMessage = model.validateDoubleInput(enteredValue, creditPoints);
		assertEquals(outputMessage, "Invalid credits value.");
	}
	
	

}
