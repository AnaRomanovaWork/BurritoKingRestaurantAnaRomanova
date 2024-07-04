//All orders class to store necessary data from Database for UI tables

package model;

import java.util.Date;

public class AllOrdersInfo {
	private int orderNumber;
    private String orderStatus;
    private double totalPrice;
    private int totalTime;
    private String foodItems;
    private Date orderPlaceDateTime;
    
    
    public AllOrdersInfo(int orderNumber, String orderStatus, double totalPrice, int totalTime, String foodItems, Date orderPlaceDateTime) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.totalTime = totalTime;
        this.foodItems = foodItems;
        this.orderPlaceDateTime = orderPlaceDateTime;
        
    }

    //get order number 
    public int getOrderNumber() {
        return orderNumber;
    }

    //get order status class
    public String getOrderStatus() {
        return orderStatus;
    }
    
    //get order total price
    public double getTotalPrice() {
        return totalPrice;
    }
    
    //get list of foodItems
    public String getFoodItems() {
		return foodItems;
	}
    
    //get order place date
    public Date getOrderPlaceDateTime() {
        return orderPlaceDateTime;
    }
    
    //get order total time
    public int getTotalTime() {
		return totalTime;
	}
}
