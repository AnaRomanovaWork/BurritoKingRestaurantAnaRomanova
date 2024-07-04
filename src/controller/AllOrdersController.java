//class for ui functionality to represent all orders of the specific user

package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AllOrdersInfo;
import model.MainModel;
import model.Model;
import model.OrderExplorer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AllOrdersController {

    @FXML
    private Button btnBackToMain;

    @FXML
    private TableColumn<AllOrdersInfo, String> tcPlacedTime;

    @FXML
    private TableColumn<AllOrdersInfo, String> tcStatus;

    @FXML
    private TableColumn<AllOrdersInfo, Double> tcTotalPrice;

    @FXML
    private TableView<AllOrdersInfo> tableOrders;

    private ObservableList<AllOrdersInfo> orders;

   
    //store data to a table for user
    @FXML
    public void initialize() {
 
        tcPlacedTime.setCellValueFactory(order -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new SimpleStringProperty(dateFormat.format(order.getValue().getOrderPlaceDateTime()));
        });
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        

        tcTotalPrice.setCellValueFactory(order -> {
            double price = order.getValue().getTotalPrice();
            return new SimpleDoubleProperty(price).asObject();
        });
        
        loadOrders();
    }

    //handle back to main menu
    public void backToMainMenu() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
        Parent mainScreen = loader.load();
        Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnBackToMain.getScene().getWindow();
        stage.setTitle("Main menu");
        stage.setScene(mainScene);
    }
    
    //load orders for a specific user
    private void loadOrders() {
    	Model model = MainModel.getInstance();
    	String username = model.loadUserInput();
        List<AllOrdersInfo> orderList = OrderExplorer.viewAllUserOrders(username);
        orders = FXCollections.observableArrayList(orderList);
        tableOrders.setItems(orders);
    }
}
