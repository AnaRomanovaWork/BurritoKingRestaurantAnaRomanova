//class stores functionality of edit user profile view

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

public class EditUserProfileController {

    @FXML
    private Button btnMainMenu;

    @FXML
    private Button btnSubmit;

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelLastName;

    @FXML
    private Label labelPassword;
    
    @FXML
    private Label labelUsername;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfPassword;
    
    @FXML
    private Label lblOutput;
    
    //view started with information about specific user
    @FXML
    public void initialize() {
    	Model model = MainModel.getInstance();
    	String userPassword = model.getPassword(model.loadUserInput());
    	String userFirstName = model.getFirstNameOfUser(model.loadUserInput());
    	String userLastName = model.getLastNameOfUser(model.loadUserInput());
    	labelFirstName.setText(userFirstName);
    	labelLastName.setText(userLastName);
    	labelPassword.setText(userPassword);
    	labelUsername.setText(model.loadUserInput());
    }
    
    //edit user data
    @FXML
    public void editUserProfile() {
        String editedPassword = tfPassword.getText();
        String editedFirstName = tfFirstName.getText();
        String editedLastName = tfLastName.getText();
        Model model = MainModel.getInstance();
        
        String passwordOutputMessage = "";
        String firstNameOutputMessage = "";
        String secondNameOutputMessage = "";

        if (editedPassword.isEmpty() && editedFirstName.isEmpty() && editedLastName.isEmpty()) {
            lblOutput.setText("You haven't entered anything.");
        } else {
            if (!editedPassword.isEmpty()) {
                passwordOutputMessage = model.changePassword(model.loadUserInput(), editedPassword);
            }
            if (!editedFirstName.isEmpty()) {
                firstNameOutputMessage = model.changeFirstName(model.loadUserInput(), editedFirstName);
            }
            if (!editedLastName.isEmpty()) {
                secondNameOutputMessage = model.changeLastName(model.loadUserInput(), editedLastName);
            }

            if (passwordOutputMessage.isEmpty() && firstNameOutputMessage.isEmpty() && secondNameOutputMessage.isEmpty()) {
                lblOutput.setText("Updated successfully");
            } else {
                StringBuilder outputMessage = new StringBuilder();
                if (!passwordOutputMessage.isEmpty()) {
                    outputMessage.append(passwordOutputMessage).append("\n");
                }
                if (!firstNameOutputMessage.isEmpty()) {
                    outputMessage.append(firstNameOutputMessage).append("\n");
                }
                if (!secondNameOutputMessage.isEmpty()) {
                    outputMessage.append(secondNameOutputMessage).append("\n");
                }
                lblOutput.setText(outputMessage.toString().trim());
            }
        }
    }
    
    //back to main menu
    @FXML
    public void backToMainMenu() throws IOException {
       Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/Loggedin.fxml"));
       Scene mainScene = new Scene(mainScreen);
       Stage stage = (Stage) btnMainMenu.getScene().getWindow();
       stage.setTitle("Main menu");
       stage.setScene(mainScene); 	
    }

}

