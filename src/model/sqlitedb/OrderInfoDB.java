//order info table stores order number, total price, total time, order status, order place date&time, order cokllection time

package model.sqlitedb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import model.Order;


public class OrderInfoDB {
	final static String TABLE_NAME = "OrdersInfo";
	public static void createOrderInfoTable() {
		try (Connection con = DatabaseConnection.getConnection();
	             Statement stmt = con.createStatement()) {
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
	                    "(orderNumber INTEGER PRIMARY KEY, " +
	                    "totalPrice REAL NOT NULL, " +
	                    "totalTime INTEGER NOT NULL, " +
	                    "orderStatus VARCHAR(10) NOT NULL, " +
	                    "orderPlaceDateTime TEXT NOT NULL, " +
	                    "orderCollectionTime TEXT , " + 
	                    "FOREIGN KEY (orderNumber) REFERENCES OrderDB(orderNumber))");
	            System.out.println("Order info table created successfully");
	        } catch (SQLException e) {
	            System.out.println("Error creating order info table: " + e.getMessage());
	        }
	    }
	
	//add an order to table
	public static void addOrderInfo(Order order){
		 String query = "INSERT INTO " + TABLE_NAME + " (orderNumber, totalPrice, totalTime, orderStatus, orderPlaceDateTime, orderCollectionTime) VALUES (?, ?, ?, ?, ?, ?)";
		 try (Connection con = DatabaseConnection.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setInt(1, order.getOrderNumber());
	            pstmt.setDouble(2, order.getTotalPrice());
	            pstmt.setInt(3, order.getTotalTime());
	            pstmt.setString(4, order.getOrderStatus());
	            pstmt.setString(5, order.getOrderPlaceDateTime());
	            pstmt.setString(6, order.getOrderCollectDateTime());
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Order info added successfully");
	            } else {
	                System.out.println("Failed to add order info");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error adding order info: Order already exists");
	        }
	    }
	
	//update order status
	public static String orderStatusUpdate(int orderNumber, String newStatus) {
		String sql = "UPDATE " + TABLE_NAME + " SET orderStatus = ? WHERE orderNumber = ?";
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, newStatus);
				pstmt.setInt(2, orderNumber);
	            
	            int rowsUpdated = pstmt.executeUpdate();
	            
	            if (rowsUpdated > 0) {
	                return "";
	            } else {
	            	return "This order doesn't exist: " + orderNumber;
	            }
	            
	        } catch (SQLException e) {
	        	System.out.print("Error : " + e.getMessage());
	        	return "Error : " + e.getMessage();
	        }
	}
	
	//update order collection data and time
	public static String orderCollectionTimeUpdate(int orderNumber, String orderCollectionTime) {
	    String sql = "UPDATE " + TABLE_NAME + " SET orderCollectionTime = ? WHERE orderNumber = ?";
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, orderCollectionTime);
	        pstmt.setInt(2, orderNumber);
	        
	        int rowsUpdated = pstmt.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            return "";
	        } else {
	            return "This order doesn't exist: " + orderNumber;
	        }
	        
	    } catch (SQLException e) {
	        return "Error updating collection time: " + e.getMessage();
	    }
	}
	
	
	//delete table
	public static void DeleteTableOrderInfoDBTable() {	
		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
