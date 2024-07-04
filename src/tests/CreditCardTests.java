//class test functionality of credit card class
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CreditCard;

public class CreditCardTests {
	
	//test of valid credit card data
	@Test
	public void validateCardValidInput() {
		String cardNumber = "1234567812345678";
		String cardExpiryDate = "10/28";
		String cardCvv = "333";
		CreditCard card = new CreditCard(cardNumber, cardExpiryDate, cardCvv);
		String outputMessage = card.validateCard();
		assertEquals(outputMessage, "Payment successful!");
	}
	
	//test of invalid card number
	@Test
	public void validateCardInvalidCardNumber() {
		String cardNumber = "123456745678";
		String cardExpiryDate = "10/28";
		String cardCvv = "333";
		CreditCard card = new CreditCard(cardNumber, cardExpiryDate, cardCvv);
		String outputMessage = card.validateCard();
		assertEquals(outputMessage, "Invalid Card Number");
	}
	
	//test of invalid expiry date
	@Test
	public void validateCardInvalidExpiryDate() {
		String cardNumber = "1234567812345678";
		String cardExpiryDate = "10/20";
		String cardCvv = "333";
		CreditCard card = new CreditCard(cardNumber, cardExpiryDate, cardCvv);
		String outputMessage = card.validateCard();
		assertEquals(outputMessage, "Invalid expiry date");
	}
	
	//test of invalid cvv
	@Test
	public void validateCardInvalidCvv() {
		String cardNumber = "1234567812345678";
		String cardExpiryDate = "10/28";
		String cardCvv = "abc";
		CreditCard card = new CreditCard(cardNumber, cardExpiryDate, cardCvv);
		String outputMessage = card.validateCard();
		assertEquals(outputMessage, "Invalid cvv");
	}
}
