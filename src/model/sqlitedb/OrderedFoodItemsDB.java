//order food items database stores order number, food items, quantity

package model.sqlitedb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import model.FoodItem;
import model.Order;


public class OrderedFoodItemsDB {
	final static String TABLE_NAME = "OrderedFoodItems";
	public static void createOrderedFoodItemsTable() {
		try (Connection con = DatabaseConnection.getConnection();
	             Statement stmt = con.createStatement()) {
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
	                    "(orderNumber INTEGER NOT NULL, " +
	                    "foodItemName VARCHAR(10) NOT NULL, " +
	                    "quantity INTEGER, " +
	                    "PRIMARY KEY (orderNumber, foodItemName), " +
	            		"FOREIGN KEY (orderNumber) REFERENCES OrderDB(orderNumber))");
	            System.out.println("Ordered Food Items table created successfully");
	        } catch (SQLException e) {
	            System.out.println("Error creating OrderedFoodItems table: " + e.getMessage());
	        }
	    }
	
	//add food item to database
	public static void addFoodItem(Order order) {
	    String query = "INSERT INTO " + TABLE_NAME + " (orderNumber, foodItemName, quantity) VALUES (?, ?, ?)";
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(query)) {
	        Map<FoodItem, Integer> foodItems = order.getShoppingBasket().getFoodItemsList();
	        for (Map.Entry<FoodItem, Integer> entry : foodItems.entrySet()) {
	            FoodItem foodItem = entry.getKey();
	            int quantity = entry.getValue();
	            pstmt.setInt(1, order.getOrderNumber());
	            pstmt.setString(2, foodItem.getName());
	            pstmt.setInt(3, quantity);
	            pstmt.executeUpdate();
	        }
	        System.out.println("Food items added to order successfully");
	    } catch (SQLException e) {
	        System.out.println("Error adding food items to order: " + e.getMessage());
	    }
	}
	
	//delete specific item from database
	public static void deleteRow(Order order) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE orderNumber = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, order.getOrderNumber());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected == 1) {
                System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
                System.out.println(rowsAffected + " row(s) affected");
            } else {
                System.out.println("No rows were deleted for order number: " + order.getOrderNumber());
            }
        } catch (SQLException e) {
            System.out.println("Error deleting row from table " + TABLE_NAME + ": " + e.getMessage());
        }
    }
	
	//delete whole table
	public static void DeleteTableOrderedFoodTable() {	
		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
