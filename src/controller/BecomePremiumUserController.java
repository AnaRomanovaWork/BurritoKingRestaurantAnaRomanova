//class for ui functionality of become premium user view

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

public class BecomePremiumUserController {

    @FXML
    private Button btnMainMenu;
    
    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSubmit;

    @FXML
    private CheckBox cbAgree;

    @FXML
    private Label lblOutput;

    @FXML
    private TextField tfEmail;
    
    private Model model; 
    
    public BecomePremiumUserController() {
    	model = MainModel.getInstance(); 
    }
    
    
    //method to become premium user
    public void becomePremiumUser() {
        String username = model.loadUserInput();
    	if(!cbAgree.isSelected()) {
    		lblOutput.setText("You should agree to recieve promotion");
    	}else {
    		String email = tfEmail.getText();
    		String validationMessage = model.emailValidation(email);
    		if(validationMessage.isEmpty()) {
    			String outputMessage = model.becomePremiumUser(username, email);
    			lblOutput.setText(outputMessage);
    		}else {
    			lblOutput.setText(validationMessage);
    		}
    	}
    	
    }
    
    //method to back to Main Menu
    public void backToMainMenu() throws IOException {
    	if(model.checkIfPremiumUser(model.loadUserInput())) {
    		btnMainMenu.setDisable(true);
    	}else {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
            Parent mainScreen = loader.load();
            Scene mainScene = new Scene(mainScreen);
            Stage stage = (Stage) btnMainMenu.getScene().getWindow();
            stage.setTitle("Main menu");
            stage.setScene(mainScene);
    	}
    }
    
    //method to log out
    public void logOut() throws IOException {
    	model.clearUserInput();
    	Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
    	Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setTitle("Burrito King Login page");
        stage.setScene(mainScene); 	
    }

}

