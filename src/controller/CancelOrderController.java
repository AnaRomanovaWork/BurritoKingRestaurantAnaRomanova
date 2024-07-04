//class stores functionality to cancel order view

package controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AllOrdersInfo;
import model.MainModel;
import model.Model;

public class CancelOrderController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblOutput;

    @FXML
    private Label lblWelcome;

    @FXML
    private TableView<AllOrdersInfo> tbvOrders;

    @FXML
    private TableColumn<AllOrdersInfo, String> tcFoodItem;

    @FXML
    private TableColumn<AllOrdersInfo, String> tcOrderN;

    @FXML
    private TableColumn<AllOrdersInfo, String> tcOrderStatus;

    @FXML
    private TableColumn<AllOrdersInfo, Double> tcTotalPrice;

    @FXML
    public void initialize() {
    	tcOrderN.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	tcTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    	tcOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
    	tcFoodItem.setCellValueFactory(new PropertyValueFactory<>("foodItems"));
        
        loadUserOrders();
    }

    //load orders for specific user
    private void loadUserOrders() {
    	Model model = MainModel.getInstance();
    	
        List<AllOrdersInfo> userOrders = model.getUserOrders();
        if (userOrders.isEmpty()) {
        	tbvOrders.setVisible(false);
        	lblWelcome.setText("You have not ordered anything yet.");
        	
        }else {
        	ObservableList<AllOrdersInfo> activeOrders = FXCollections.observableArrayList(userOrders);
        	tbvOrders .setItems(activeOrders);
        	tbvOrders.setVisible(true);
        }  
    }
    
    
    //back to main menu
    @FXML
    public void backToMainMenu() throws IOException {
    	Model model = MainModel.getInstance();
    	model.clearShoppingBasket(model.getShoppingBasket());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
        Parent mainScreen = loader.load();
        Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setTitle("Main menu");
        stage.setScene(mainScene);
    }
    
    //handle cancel order functionality
    @FXML
    void handleCancelOrder(ActionEvent event) {
    	Model model = MainModel.getInstance();
        AllOrdersInfo selectedOrder = tbvOrders.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            lblOutput.setText("Please select an order to collect.");
            return;
      }
       String outputMessage = model.cancelOrder(selectedOrder.getOrderNumber());
       lblOutput.setText(outputMessage);
    }

}

