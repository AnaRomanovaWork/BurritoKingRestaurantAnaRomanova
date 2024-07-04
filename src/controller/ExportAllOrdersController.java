//class stores functionality for export all orders view


package controller;

import java.io.IOException;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AllOrdersInfo;
import model.MainModel;
import model.Model;
import model.OrderExplorer;
import model.OrderExporter;

public class ExportAllOrdersController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnExport;

    @FXML
    private CheckBox cbFoodItems;

    @FXML
    private CheckBox cbOrderN;

    @FXML
    private CheckBox cbStatus;

    @FXML
    private CheckBox cbTotalPrice;

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
    private TextField tfName;

    @FXML
    private TextField tfPath;
    
    //view starts with data about all user order
    @FXML
    public void initialize() {
    	tcOrderN.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	tcTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    	tcOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
    	tcFoodItem.setCellValueFactory(new PropertyValueFactory<>("foodItems"));
        
        loadUserOrders();
    }
    
    //load user orders
    private void loadUserOrders() {
    	Model model = MainModel.getInstance();
        List<AllOrdersInfo> userOrders = OrderExplorer.viewAllUserOrders(model.loadUserInput());
        if (userOrders.isEmpty()) {
        	tbvOrders.setVisible(false);
        	lblWelcome.setText("You have not ordered anything yet.");
        	
        }else {
        	ObservableList<AllOrdersInfo> allOrders = FXCollections.observableArrayList(userOrders);
        	tbvOrders .setItems(allOrders);
        	tbvOrders.setVisible(true);
        }  
    }
    
    //handle export button
    @FXML
    private void handleExportButtonAction() {
    	 Model model = MainModel.getInstance();
    	 List<AllOrdersInfo> userOrders = OrderExplorer.viewAllUserOrders(model.loadUserInput());
    	 
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Save Orders");
         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
         String filePath = fileChooser.showSaveDialog(btnExport.getScene().getWindow()).getPath();
         boolean exportOrderNumber = cbOrderN.isSelected();
         boolean exportStatus = cbStatus.isSelected();
         boolean exportFoodItemsList = cbFoodItems.isSelected();
         boolean exportTotalPrice = cbTotalPrice.isSelected();
         
         
         try {
             OrderExporter.exportOrdersToCSV(userOrders, filePath, exportStatus, exportOrderNumber, exportTotalPrice, exportFoodItemsList);
             lblOutput.setText("Export Successful. Orders exported successfully to: " + filePath);
         } catch (IOException e) {
        	 lblOutput.setText("Export Error. An error occurred while exporting orders: " + e.getMessage());
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

}

