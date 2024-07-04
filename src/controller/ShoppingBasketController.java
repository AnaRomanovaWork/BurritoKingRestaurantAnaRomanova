//class stores functionality for shopping basket view

package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MainModel;
import model.Model;

public class ShoppingBasketController {

    @FXML
    private Button btnAddBurrito;

    @FXML
    private Button btnAddFries;

    @FXML
    private Button btnAddMeal;

    @FXML
    private Button btnAddSoda;

    @FXML
    private Button btnCheckOut;

    @FXML
    private Button btnMainMenu;

    @FXML
    private Label lblBurritoTotal;

    @FXML
    private Label lblFriesTotal;

    @FXML
    private Label lblMealTotal;

    @FXML
    private Label lblSodaTotal;
    
    @FXML
    private Label lblOutputMessage;

    @FXML
    private Label lblMeal;
    
    @FXML
    private TextField tfBurritoQuantity;

    @FXML
    private TextField tfFriesQuantity;

    @FXML
    private TextField tfMealQuantity;

    @FXML
    private TextField tfSodaQuantity;
    
    private Model model; 
    
    public ShoppingBasketController() {
    	model = MainModel.getInstance(); 
    }

    @FXML
    public void initialize() {
    	 
        tfBurritoQuantity.setText("0");
        tfFriesQuantity.setText("0");
        tfMealQuantity.setText("0");
        tfSodaQuantity.setText("0");
        
        lblBurritoTotal.setText("0 $");
        lblFriesTotal.setText("0 $");
        lblSodaTotal.setText("0 $");
        lblMealTotal.setText("0 $");
        
        boolean premiumUserCheck = model.checkIfPremiumUser(model.loadUserInput()); 
        btnAddMeal.setVisible(premiumUserCheck);
        tfMealQuantity.setVisible(premiumUserCheck);
        lblMealTotal.setVisible(premiumUserCheck);
        lblMeal.setVisible(premiumUserCheck);
    }
    
    
    @FXML
    public void backToMainMenu() throws IOException {
    	model.clearShoppingBasket(model.getShoppingBasket());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
        Parent mainScreen = loader.load();
        Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnMainMenu.getScene().getWindow();
        stage.setTitle("Main menu");
        stage.setScene(mainScene);
    }
    
    
    @FXML
    public void showCheckout() throws IOException {
    	model.addToShoppingBasketTable(model.getShoppingBasket());
        Stage stage = (Stage) btnCheckOut.getParent().getScene().getWindow(); 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckOut.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Checkout");
        stage.show();
    }

    
	public void handleAddBurrito() {
		int burritoQuantity =  model.getQuantity(tfBurritoQuantity);
		if(burritoQuantity <= 0) {
    		lblOutputMessage.setText("Wrong quantity, please, enter a valid value.");
    		lblBurritoTotal.setText("0 $");
    	}else {
    		double price = model.updateBurritoPrice(burritoQuantity);
        	lblBurritoTotal.setText(String.valueOf(price) + "$");
        	model.addBurrito(burritoQuantity, lblOutputMessage);
    	}
	}
	
	public void handleAddFries() {
		int friesQuantity =  model.getQuantity(tfFriesQuantity);
		if(friesQuantity <= 0) {
    		lblOutputMessage.setText("Wrong quantity, please, enter a valid value.");
    		lblFriesTotal.setText("0 $");
    	}else {
    		double price = model.updateFriesPrice(friesQuantity);
        	lblFriesTotal.setText(String.valueOf(price) + "$");
        	model.addFries(friesQuantity, lblOutputMessage);
    	}
	}
	
	public void handleAddSoda() {
		int sodaQuantity =  model.getQuantity(tfSodaQuantity);
		if(sodaQuantity <= 0) {
    		lblOutputMessage.setText("Wrong quantity, please, enter a valid value.");
    		lblSodaTotal.setText("0 $");
    	}else {
    		double price = model.updateSodaPrice(sodaQuantity);
        	lblSodaTotal.setText(String.valueOf(price) + "$");
        	model.addSoda(sodaQuantity, lblOutputMessage);
    	}
	}
	
	public void handleAddMeal() {
		int mealQuantity =  model.getQuantity(tfMealQuantity);
		if(mealQuantity <= 0) {
    		lblOutputMessage.setText("Wrong quantity, please, enter a valid value.");
    		lblMealTotal.setText("0 $");
    	}else {
    		double price = model.updateMealPrice(mealQuantity);
        	lblMealTotal.setText(String.valueOf(price) + "$");
        	model.addMeal(mealQuantity, lblOutputMessage);
    	}
	}
	

}

