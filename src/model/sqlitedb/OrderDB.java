//class order database stores order number username 

package model.sqlitedb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Order;
import model.User;

public class OrderDB {
	final static String TABLE_NAME = "Orders";
	public static void createOrderTable() {
		try (Connection con = DatabaseConnection.getConnection();
	             Statement stmt = con.createStatement()) {
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
	                    "(orderNumber INTEGER PRIMARY KEY, " +
	                    "userName VARCHAR(50) NOT NULL, " +
	                    "FOREIGN KEY (userName) REFERENCES UsersDB(userName))");
	            System.out.println("Orders table created successfully");
	        } catch (SQLException e) {
	            System.out.println("Error creating orders table: " + e.getMessage());
	        }
	    }
	
	//method to add order
	public static void addOrder(User user, Order order){
		 String query = "INSERT INTO " + TABLE_NAME + " (username, orderNumber) VALUES (?, ?)";
		 try (Connection con = DatabaseConnection.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setString(1, user.getUserName());
	            pstmt.setInt(2, order.getOrderNumber());
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Order added successfully");
	            } else {
	                System.out.println("Failed to add order");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error adding order: Order already exists");
	        }
	    }
	
	//method to delete table
	public static void deleteTableOrderDBTable() {	
		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//method to get the highest id
	public static int getHighestOrderID() {
        int highestOrderID = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT MAX(orderNumber) FROM Orders")) {
            if (resultSet.next()) {
                highestOrderID = resultSet.getInt(1);
            }
        } catch (Exception e) {
        	return highestOrderID;
        }
        return highestOrderID;
    }
	
	
}
