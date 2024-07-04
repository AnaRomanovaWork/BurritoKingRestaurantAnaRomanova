//class stores functionality for Main view


package controller;
import java.io.IOException;
import javafx.event.ActionEvent;
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
import javafx.scene.Node;


public class MainViewController {
	
	@FXML
    private TextField tfUsername, tfPassword;
	
	@FXML
	private Label lblMainMessage;
	
	@FXML
	private Button buttonLogin, buttonSignup;
	
	//handle login actions
	@FXML
	private void Login(ActionEvent event) throws IOException {
	    String username = tfUsername.getText();
	    String password = tfPassword.getText();
	    Model model = MainModel.getInstance();
	    
	    
	    String userValidationMessage = model.validateUser(username);
	    String passwordValidationMessage = model.validatePassword(username, password);
	    
	    if(userValidationMessage.isEmpty()&& passwordValidationMessage.isEmpty()) {
	    	model.saveUserInput(username);
            showLoggedIn();
	    }else if(!userValidationMessage.isEmpty()) {
	    	lblMainMessage.setText(userValidationMessage);
	    }else if(!passwordValidationMessage.isEmpty()) {
	    	lblMainMessage.setText(passwordValidationMessage);
	    }
	}
	
	//show loggedIn in view
	private void showLoggedIn() throws IOException {
	    Stage stage = (Stage) buttonLogin.getScene().getWindow(); 
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
	    Parent root = loader.load();
	    loader.getController();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Main menu");
	    stage.show();
	}
	
	//show sign up view
	@FXML
	private void SignUp(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.setTitle("Registration");
	    stage.show();
	}

	
}









//package controller;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//
//public class MainViewController {
//	@FXML
//    private TextField tfUsername;
//
//    @FXML
//    private TextField tfPassword;
//
//    @FXML
//    private Button buttonLogin;
//    
//    @FXML
//    private Button buttonSignup;
//    
//    @FXML
//    private Stage primaryStage;
//
//    public void setPrimaryStage(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }
//   
//
//    @FXML
//    private void handleButtonLoginAction(ActionEvent event)  {
//    	System.out.println("You clicked the Login button!");
//    	}
//    
//    @FXML
//    private void handleButtonSignupAction(ActionEvent event)  {
//    	System.out.println("You clicked the Signup button!");
//    }
////    private void handleLogin() {
////        String username = usernameField.getText();
////        String password = passwordField.getText();
////        // Perform login logic here, e.g., call a service or validate credentials
////        System.out.println("Login attempt with username: " + username + " and password: " + password);
////    }
//}
//
//
