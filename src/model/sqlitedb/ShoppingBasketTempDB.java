//temporary table to store shopping basket's items, stores foodItem Name, quantity, total price


package model.sqlitedb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.FoodItem;
import model.ShoppingBasketList;



public class ShoppingBasketTempDB {
    final static String TABLE_NAME = "ShoppingBasketTemp";

    public static void createTempTable() {
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    " (foodItemName VARCHAR(50) PRIMARY KEY UNIQUE, " +
                    "foodItemQuantity INTEGER NOT NULL, " +
                    "totalItemPrice REAL NOT NULL)");
            System.out.println("Shopping basket table created");
        } catch (SQLException e) {
            System.out.println("Error creating shopping basket table: " + e.getMessage());
        }
    }
	
    //add food item to table
	public static String addFoodItem(FoodItem foodItem, int quantity){
		 String query = "INSERT INTO " + TABLE_NAME + " (foodItemName, foodItemQuantity, totalItemPrice) VALUES (?, ?, ?)";
		 try (Connection con = DatabaseConnection.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setString(1, foodItem.getName());
	            pstmt.setInt(2, quantity);
	            pstmt.setDouble(3, foodItem.getPrice() * quantity);
	            pstmt.executeUpdate();
	            return "Food item added successfully.";
	        } catch (SQLException e) {
	            System.out.println("Error adding food item: " + e.getMessage());
	            return "Error adding food item: " + e.getMessage();
	        }
	    }
	
	//update quantity
	public static void updateQuantity(FoodItem foodItem, int quantity) {
		String sql = "UPDATE " + TABLE_NAME + " SET foodItemQuantity = ?, totalItemPrice = ? WHERE foodItemName = ?";
		try (Connection con = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
				 
				 pstmt.setInt(1, quantity);
		         pstmt.setDouble(2, foodItem.getPrice() * quantity);
		         pstmt.setString(3, foodItem.getName());
		         pstmt.executeUpdate();
		  
		        } catch (SQLException e) {
		            System.out.println("Error updating food item: " + e.getMessage());
		        }
		    }
	
	//delete item
	public static String deleteItem(String foodItemName) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE foodItemName = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, foodItemName);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Food item deleted successfully.";
            } else {
                return "Food item not found.";
            }
        } catch (SQLException e) {
            System.out.println("Error deleting food item: " + e.getMessage());
            return "Error deleting food item: " + e.getMessage();
        }
    }
		            
	//delete table
	public static void deleteTableShoppingBasket() {
		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
			System.out.println("Shopping basket deleted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//upload shopping basket from table to list
	public static List<ShoppingBasketList> shoppingBasketForTable() {
        List<ShoppingBasketList> shoppingBasketList = new ArrayList<>();
        String query = "SELECT foodItemName, foodItemQuantity, totalItemPrice FROM " + TABLE_NAME;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("foodItemName");
                int quantity = rs.getInt("foodItemQuantity");
                double price = rs.getDouble("totalItemPrice");
                
                ShoppingBasketList shoppingBasketInfo = new ShoppingBasketList(name, quantity, price);
                shoppingBasketList.add(shoppingBasketInfo);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching shopping basket items: " + e.getMessage());
        }
        return shoppingBasketList;
    }
	
	//calculate total price
	 public static double calculateTotalPrice() {
	        double totalPrice = 0.0;
	        String query = "SELECT SUM(totalItemPrice) AS total FROM " + TABLE_NAME;

	        try (Connection con = DatabaseConnection.getConnection();
	             Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            if (rs.next()) {
	                totalPrice = rs.getDouble("total");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error calculating total price: " + e.getMessage());
	        }

	        return totalPrice;
	    }
}
	

