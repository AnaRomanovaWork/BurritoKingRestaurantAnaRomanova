//class stores functionality for loggeIn c view(Main Menu)

package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AllOrdersInfo;
import model.MainModel;
import model.Model;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class LoggedinController {
	
	@FXML
    private Button btnLogOut;

    @FXML
    private MenuItem becomePremiumUserItem;

    @FXML
    private MenuItem cancelOrder;

    @FXML
    private MenuItem collectOrder;

    @FXML
    private MenuItem editProfileMenuItem;

    @FXML
    private TableColumn<AllOrdersInfo, String> foodItemsListColumn;

    @FXML
    private Menu logOutMenu;

    @FXML
    private MenuItem makeorderMenuItem;

    @FXML
    private TableColumn<AllOrdersInfo, String> orderNColumn;

    @FXML
    private TableColumn<AllOrdersInfo, String> orderStatusColumn;

    @FXML
    private TableColumn<AllOrdersInfo, Double> totalPriceColumn;

    @FXML
    private MenuItem viewAllOrdersMenuItem;

    @FXML
    private Label outputLabel;
    
    @FXML
    private Label outputLabel2;
    
    @FXML
    private ImageView imageBK;

    
    @FXML
    private TableView<AllOrdersInfo> orderDashboard;
    
    
	//view starts with user dashbord with all active orders
    @FXML
	public void initialize() {
    	Model model = MainModel.getInstance();
    	String userFirstName = model.getFirstNameOfUser(model.loadUserInput());
    	String userLastName = model.getLastNameOfUser(model.loadUserInput());
    	outputLabel.setText(userFirstName +" " + userLastName + " welcome to Burrito King!");
    	
    	orderNColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        foodItemsListColumn.setCellValueFactory(new PropertyValueFactory<>("foodItems"));
        
        loadUserOrders();
        boolean premiumUserCheck = model.checkIfPremiumUser(model.loadUserInput()); 
        becomePremiumUserItem.setVisible(!premiumUserCheck);
    }
    
    //load orders
    @FXML
    private void loadUserOrders() {
    	Model model = MainModel.getInstance();
    	
        List<AllOrdersInfo> userOrders = model.getUserOrders();
        if (userOrders.isEmpty()) {
        	orderDashboard.setVisible(false);
        	imageBK.setVisible(true);
        	outputLabel2.setText("You have not ordered anything yet.");
        	
        }else {
        	ObservableList<AllOrdersInfo> activeOrders = FXCollections.observableArrayList(userOrders);
            orderDashboard .setItems(activeOrders);
            orderDashboard.setVisible(true);
            outputLabel2.setText("Here is information about your active orders.");
            imageBK.setVisible(false);
        }  
    }
    
    //show edit user profile view
    @FXML
	public void showEditUserProfile() throws IOException{
		Stage stage = (Stage) editProfileMenuItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditUserProfile.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("Edit Profile");
	    stage.show();
	}
	
    //show become premium user view
    @FXML
	public void showBecomePremiumUser() throws IOException{
		Stage stage = (Stage) becomePremiumUserItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BecomePremiumUser.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("Become Premium User");
	    stage.show();
	}
	
    //show shopping basket view(start of making order)
    @FXML
	public void showShoppingBasket() throws IOException{
		Stage stage = (Stage) makeorderMenuItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShoppingBasket.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("Shopping basket");
	    stage.show();
	}
	
    //show all orders view
    @FXML
	public void showAllOrders() throws IOException{
		Stage stage = (Stage) viewAllOrdersMenuItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AllOrders.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("All orders");
	    stage.show();
	}
	
    //show collect order view
    @FXML
	public void collectOrder() throws IOException{
		Stage stage = (Stage) viewAllOrdersMenuItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CollectOrder.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("Collect order");
	    stage.show();
	}
    
    //show cancel order view
    @FXML
	public void cancelOrder() throws IOException{
		Stage stage = (Stage) viewAllOrdersMenuItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CancelOrder.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("Cancel order");
	    stage.show();
	}
    
    //show export all orders view
    @FXML
	public void exportAllOrders() throws IOException{
		Stage stage = (Stage) viewAllOrdersMenuItem.getParentPopup().getOwnerWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExportAllOrders.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
	    stage.setTitle("Export orders");
	    stage.show();
	}
	
    //show main view
    @FXML
	public void logOut() throws IOException {
		
		Parent mainScreen = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
    	Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setTitle("Burrito King Login page");
        stage.setScene(mainScene); 
    	Model model = MainModel.getInstance();
    	model.clearUserInput();
    }

}
