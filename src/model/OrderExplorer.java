//class help to get necessary from database to show it in UI Tables 

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.sqlitedb.DatabaseConnection;

public class OrderExplorer {
	
	//method to view all orders of specific user
    public static List<AllOrdersInfo> viewAllUserOrders(String username) {
        String sql = "SELECT oi.orderPlaceDateTime, oi.totalPrice, oi.totalTime, oi.orderStatus, o.orderNumber, " +
                     "(SELECT GROUP_CONCAT(ofi.foodItemName, ', ') FROM OrderedFoodItems ofi WHERE ofi.orderNumber = o.orderNumber) AS foodItems " +
                     "FROM OrdersInfo oi " +
                     "JOIN Orders o ON oi.orderNumber = o.orderNumber " +
                     "WHERE o.userName = ? " +
                     "ORDER BY oi.orderPlaceDateTime DESC";
        List<AllOrdersInfo> orders = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            while (rs.next()) {
                String orderPlaceDateTimeStr = rs.getString("orderPlaceDateTime");
                Date orderPlaceDateTime = null;
                try {
                    orderPlaceDateTime = dateFormat.parse(orderPlaceDateTimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int orderNumber = rs.getInt("orderNumber");
                String orderStatus = rs.getString("orderStatus");
                double totalPrice = rs.getDouble("totalPrice");
                int totalTime = rs.getInt("totalTime");
                String foodItems = rs.getString("foodItems");

                AllOrdersInfo order = new AllOrdersInfo(orderNumber, orderStatus, totalPrice, totalTime, foodItems, orderPlaceDateTime);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving dashboard data: " + e.getMessage());
        }
        return orders;
    }

    //method to view all placed orders of specific user
    public static List<AllOrdersInfo> viewAllActiveUserOrders(String username) {
        String sql = "SELECT oi.orderPlaceDateTime, oi.totalPrice, oi.totalTime, oi.orderStatus, o.orderNumber, " +
                     "(SELECT GROUP_CONCAT(ofi.foodItemName, ', ') FROM OrderedFoodItems ofi WHERE ofi.orderNumber = o.orderNumber) AS foodItems " +
                     "FROM OrdersInfo oi " +
                     "JOIN Orders o ON oi.orderNumber = o.orderNumber " +
                     "WHERE o.userName = ? AND " +
                     "oi.orderStatus = 'PLACED' " +
                     "ORDER BY oi.orderPlaceDateTime DESC";
        List<AllOrdersInfo> orders = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            while (rs.next()) {
                String orderPlaceDateTimeStr = rs.getString("orderPlaceDateTime");
                Date orderPlaceDateTime = null;
                try {
                    orderPlaceDateTime = dateFormat.parse(orderPlaceDateTimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int orderNumber = rs.getInt("orderNumber");
                String orderStatus = rs.getString("orderStatus");
                double totalPrice = rs.getDouble("totalPrice");
                int totalTime = rs.getInt("totalTime");
                String foodItems = rs.getString("foodItems");

                AllOrdersInfo order = new AllOrdersInfo(orderNumber, orderStatus, totalPrice, totalTime, foodItems, orderPlaceDateTime);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving dashboard data: " + e.getMessage());
        }
        return orders;
    }
    
    //select specific order for specific user
    public static AllOrdersInfo selectOrder(String username, int orderNumber) {
        String sql = "SELECT oi.orderPlaceDateTime, oi.totalPrice, oi.totalTime, oi.orderStatus, o.orderNumber, " +
                     "(SELECT GROUP_CONCAT(ofi.foodItemName, ', ') FROM OrderedFoodItems ofi WHERE ofi.orderNumber = o.orderNumber) AS foodItems " +
                     "FROM OrdersInfo oi " +
                     "JOIN Orders o ON oi.orderNumber = o.orderNumber " +
                     "WHERE o.userName = ? AND o.orderNumber = ? " +
                     "ORDER BY oi.orderPlaceDateTime DESC";
        AllOrdersInfo order = null;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, orderNumber);
            ResultSet rs = pstmt.executeQuery();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            if (rs.next()) {
                String orderPlaceDateTimeStr = rs.getString("orderPlaceDateTime");
                Date orderPlaceDateTime = null;
                try {
                    orderPlaceDateTime = dateFormat.parse(orderPlaceDateTimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String orderStatus = rs.getString("orderStatus");
                double totalPrice = rs.getDouble("totalPrice");
                int totalTime = rs.getInt("totalTime");
                String foodItems = rs.getString("foodItems");

                order = new AllOrdersInfo(orderNumber, orderStatus, totalPrice, totalTime, foodItems, orderPlaceDateTime);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving order data: " + e.getMessage());
        }
        return order;
    }
}