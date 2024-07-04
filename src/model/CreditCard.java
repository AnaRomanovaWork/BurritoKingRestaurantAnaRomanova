// Credit card class to manage credit cards methods and validation

package model;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreditCard {
	private String cardNumber;
	private String cardExpiryDate;
	private String cardCvv;
	
	public CreditCard(String cardNumber, String cardExpiryDate, String cardCvv) {
		this.cardNumber = cardNumber;
		this.cardExpiryDate = cardExpiryDate;
		this.cardCvv = cardCvv;
		
	}
	
	//method to validate card(validation of card number & expiry date & cvv)
	public String validateCard() {
		if(!validateCardNumber()) {
			return "Invalid Card Number";
		}else if(!validateExpiryDate()) {
			return "Invalid expiry date";
		}else if(!validateCvv()) {
			return "Invalid cvv";
		}
		return "Payment successful!";
    }
	
	//method to validate card number
	private boolean validateCardNumber() {
        return cardNumber != null && cardNumber.length() == 16 && cardNumber.matches("\\d{16}");
    }
	
	//method to validate expiry date
	private boolean validateExpiryDate() {
        LocalDate currentDate = LocalDate.now();
        YearMonth expiryDate;
        try {
            expiryDate = YearMonth.parse(cardExpiryDate, DateTimeFormatter.ofPattern("MM/yy"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return !expiryDate.isBefore(YearMonth.from(currentDate));
    }
	
	//method to validate cvv
	private boolean validateCvv() {
        return cardCvv != null && cardCvv.length() == 3 && cardCvv.matches("\\d{3}");
	}
}
