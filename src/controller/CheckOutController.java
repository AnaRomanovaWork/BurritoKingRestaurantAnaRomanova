//class stores functionality to check out view

package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.MainModel;
import model.Model;
import model.ShoppingBasketList;

public class CheckOutController {

    @FXML
    private Button btnConfirmOrder;

    @FXML
    private Label lblCookingTime;

    @FXML
    private Label lblTotalCost;
    
    @FXML
    private TableView<ShoppingBasketList> tableView;

    @FXML
    private TableColumn<ShoppingBasketList, String> tbclFoodItem;

    @FXML
    private TableColumn<ShoppingBasketList, Double> tbclPrice;

    @FXML
    private TableColumn<ShoppingBasketList, Integer> tbclQuantity;

    @FXML
    private TableColumn<ShoppingBasketList, Void> tbclRemove;

    @FXML
    private TableColumn<ShoppingBasketList, Void> tbclUpdate;
    
    @FXML
    private Label lblOutputMessage;
    
    private Model model;
    
    public CheckOutController() {
        model = MainModel.getInstance(); 
    }

    public void initialize() {
        tbclFoodItem.setCellValueFactory(new PropertyValueFactory<>("foodItemName"));
        tbclQuantity.setCellValueFactory(new PropertyValueFactory<>("foodItemQuantity"));
        tbclPrice.setCellValueFactory(new PropertyValueFactory<>("totalItemPrice"));

        ObservableList<ShoppingBasketList> data = FXCollections.observableArrayList(model.getShoppingBasketList());
        tableView.setItems(data);
        
        addRemoveButtonToTable();
        addUpdateButtonToTable();
        updateLabels();
    }

    //add to table remove item button 
    private void addRemoveButtonToTable() {
        Callback<TableColumn<ShoppingBasketList, Void>, TableCell<ShoppingBasketList, Void>> cellFactory = new Callback<TableColumn<ShoppingBasketList, Void>, TableCell<ShoppingBasketList, Void>>() {
            @Override
            public TableCell<ShoppingBasketList, Void> call(final TableColumn<ShoppingBasketList, Void> param) {
                final TableCell<ShoppingBasketList, Void> cell = new TableCell<ShoppingBasketList, Void>() {

                    private final Button btn = new Button("Remove");
                    {
                        btn.setOnAction((event) -> {
                            ShoppingBasketList data = getTableView().getItems().get(getIndex());
                            model.removeFoodItem(data.getFoodItemName()); 
                            getTableView().getItems().remove(data);
                            changeButtonName(); 
                            updateLabels();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        tbclRemove.setCellFactory(cellFactory);
        updateLabels();
        changeButtonName();
    }
    
    //add of update item quantity button
    private void addUpdateButtonToTable() {
        Callback<TableColumn<ShoppingBasketList, Void>, TableCell<ShoppingBasketList, Void>> cellFactory =
                new Callback<TableColumn<ShoppingBasketList, Void>, TableCell<ShoppingBasketList, Void>>() {
                    @Override
                    public TableCell<ShoppingBasketList, Void> call(TableColumn<ShoppingBasketList, Void> param) {
                        final TableCell<ShoppingBasketList, Void> cell = new TableCell<ShoppingBasketList, Void>() {

                            private final TextField txtQuantity = new TextField();
                            private final Button btn = new Button("Update");

                            {
                                txtQuantity.setPrefWidth(30); 
                                btn.setPrefWidth(60);
                                btn.setOnAction((event) -> {
                                    ShoppingBasketList data = getTableView().getItems().get(getIndex());
                                    try {
                                        int newQuantity = Integer.parseInt(txtQuantity.getText());
                                        if (newQuantity > 0) {
                                            model.updateQuantity(model.getFoodItem(data.getFoodItemName()), newQuantity);
                                            data.setFoodItemQuantity(newQuantity);
                                            updateTable();
                                            updateLabels(); // Update labels after updating quantity
                                        } else {
                                            lblOutputMessage.setText("Quantity must be greater than zero.");
                                        }
                                    } catch (NumberFormatException e) {
                                        lblOutputMessage.setText("Invalid quantity entered: " + e.getMessage());
                                    }
                                });
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(new HBox(txtQuantity, btn)); 
                                }
                            }
                        };
                        return cell;
                    }
                };

        tbclUpdate.setCellFactory(cellFactory);
        updateLabels(); 
    }
    
    //update lables
    private void updateLabels() {
        lblCookingTime.setText("Total time: " + model.calculateTotalTime() + " minutes");
        lblTotalCost.setText("Total price: " + model.calculateTotalPrice() + " $");
    }
    
    //update Table
    public void updateTable() {
        tbclFoodItem.setCellValueFactory(new PropertyValueFactory<>("foodItemName"));
        tbclQuantity.setCellValueFactory(new PropertyValueFactory<>("foodItemQuantity"));
        tbclPrice.setCellValueFactory(new PropertyValueFactory<>("totalItemPrice"));

        ObservableList<ShoppingBasketList> data = FXCollections.observableArrayList(model.getShoppingBasketList());
        tableView.setItems(data);
    }
    
    //change button Name
    private void changeButtonName() {
        if (tableView.getItems().isEmpty()) {
            btnConfirmOrder.setText("Back to Main Menu");
        } else {
            btnConfirmOrder.setText("Checkout");
        }
    }
    
    //handle confirmation of the order
    public void handleConfirmOrder() throws IOException {
        if (tableView.getItems().isEmpty()) {
        	showMainMenu();
        } else {
        	showPayment();
        }
    }
    
    //show main menu
    private void showMainMenu() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Loggedin.fxml"));
        Parent mainScreen = loader.load();
        Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnConfirmOrder.getScene().getWindow();
        stage.setTitle("Main menu");
        stage.setScene(mainScene);
    }
    
    //show payment
    private void showPayment() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentPlacingOrder.fxml"));
        Parent mainScreen = loader.load();
        Scene mainScene = new Scene(mainScreen);
        Stage stage = (Stage) btnConfirmOrder.getScene().getWindow();
        stage.setTitle("Payment");
        stage.setScene(mainScene);
    }
}