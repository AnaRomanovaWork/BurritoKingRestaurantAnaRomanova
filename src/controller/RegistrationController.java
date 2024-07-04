////class stores functionality for registration view


package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import model.MainModel;
import model.Model;




public class RegistrationController {
	
	
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblMessage;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;
    
   //show main view
    @FXML
    private void handleBackToLogin(ActionEvent event) throws Exception{
    	Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
    	Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.setTitle("Burrito King Login page");
        stage.setScene(mainScene); 	
    }
    
    //handle registration
    @FXML
    private void handleRegistration(ActionEvent event) throws IOException{
    	Model model = MainModel.getInstance();
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        if (username.isEmpty() || password.isEmpty()) {
        	lblMessage.setText("Username or password cannot be empty.");
        } else {
        	String validationMessage = model.addUserValidation(username, password, firstName, lastName);
            lblMessage.setText(validationMessage);
        }
    }
    
    
}