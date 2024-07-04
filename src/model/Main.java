package model;
import java.io.IOException;

import controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.sqlitedb.OrderDB;
import model.sqlitedb.OrderInfoDB;
import model.sqlitedb.OrderedFoodItemsDB;
import model.sqlitedb.PremiumUserDB;
import model.sqlitedb.UserDB;




public class Main extends Application {
	
	private Stage primaryStage;
	private MainViewController mainController;
    
    
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Burrito King Login page");
        showMainView();
    }
    
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
    
    public void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        Parent root = loader.load();
        loader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public MainViewController getMainController() {
        return mainController;
    }
    
    public static void main(String[] args) {
    	UserDB.createUserTable();
    	PremiumUserDB.createPremiumUserTable();
    	OrderDB.createOrderTable();
    	OrderInfoDB.createOrderInfoTable();
    	OrderedFoodItemsDB.createOrderedFoodItemsTable();
    	launch(args);
    }
}






    	