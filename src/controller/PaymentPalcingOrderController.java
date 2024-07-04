//class stores functionality for payment&placing order

package controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MainModel;
import model.Model;

public class PaymentPalcingOrderController {

    @FXML
    private Button btnBackToMain;

    @FXML
    private Button btnPay;
    
    @FXML
    private CheckBox cbRedeem;

    @FXML
    private Label lblAmmount;

    @FXML
    private Label lblOutput;
    
    @FXML
    private Label lblPoints;

    @FXML
    private TextField tfCardNumber;

    @FXML
    private TextField tfCvv;

    @FXML
    private TextField tfExpiryDate;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfOrderTime;
    
    @FXML
    private TextField tfCreditsAmount;
    
    
    private Model model; 
    
    public PaymentPalcingOrderController() {
    	model = MainModel.getInstance(); 
	}
    
   
    @FXML
    public void initialize() {
    	lblAmmount.setText("Payment amount: " + model.calculateTotalPrice() + " $");
    	boolean premiumUserCheck = model.checkIfPremiumUser(model.loadUserInput()); 
    	if (premiumUserCheck) {
    		lblPoints.setText("You have " + model.getCredits(model.loadUserInput()) + " credits");
    	}else {
    		cbRedeem.setVisible(premiumUserCheck);
    		tfCreditsAmount.setVisible(premiumUserCheck);
    	}
    }
    
    //handle payment
    @FXML
    public void handlePayment() {
        String orderDateTime = tfOrderTime.getText();
        String validationDateMessage = model.validateDateTime(orderDateTime);
        lblOutput.setText(validationDateMessage);
        
        if (validationDateMessage.equals("")) {
            String cardNumber = tfCardNumber.getText();
            String cvv = tfCvv.getText();
            String expDate = tfExpiryDate.getText();
            String validationCardMessage = model.cardValidation(cardNumber, expDate, cvv);
            lblOutput.setText(validationCardMessage);
            
            if (validationCardMessage.equals("Payment successful!")) {
                if (cbRedeem.isSelected()) {
                    String enteredCredits = tfCreditsAmount.getText();
                    double userCredits = model.getCredits(model.loadUserInput());
                    lblOutput.setText(model.validateDoubleInput(enteredCredits, userCredits));
                    model.addOrderToDB(model.loadUserInput(), model.placeOrder(orderDateTime, userCredits));
                    model.reduceCreditPoints(model.loadUserInput(), userCredits);
                } else {
                    model.addOrderToDB(model.loadUserInput(), model.placeOrder(orderDateTime, 0));
                }
            }
        }
    }
    
    //back to main menu
    @FXML
    public void backToMainMenu() throws IOException {
       model.deleteShoppingBasketTable();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
       Parent mainScreen = loader.load();
       Scene mainScene = new Scene(mainScreen);
       Stage stage = (Stage) btnBackToMain.getScene().getWindow();
       stage.setTitle("Main menu");
       stage.setScene(mainScene);
    }
    

}

