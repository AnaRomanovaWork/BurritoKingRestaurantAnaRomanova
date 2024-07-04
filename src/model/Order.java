//Class order represents order functionality

package model;

import java.util.Map;



public class Order {
	private ShoppingBasket shoppingBasket;
	private double totalPrice;
	private int totalTime;
	private String orderStatus;
	private int orderNumber;
	private String orderPlaceDateTime;
	private String orderCollectDateTime;
	
	public Order(ShoppingBasket shoppingBasket,int orderNumber) {
		this.shoppingBasket = shoppingBasket;
		this.totalPrice = calculateTotalPrice();
		this.totalTime = 0;
		this.orderStatus = "";
		this.orderNumber = orderNumber;
	}
	
	
	//method to get shopping basket
	public ShoppingBasket getShoppingBasket() {
		return shoppingBasket;
	}
	
	//method to get total price
	public double getTotalPrice() {
		return totalPrice;
	}
	
	//method to get total time
	public int getTotalTime() {
		return totalTime;
	}
	
	//method to get order status
	public String getOrderStatus() {
		return orderStatus;
	}
	
	//method to get order number
	public int getOrderNumber() {
		return orderNumber;
	}
	
	//method to get order place date and time
	public String getOrderPlaceDateTime() {
		return orderPlaceDateTime;
	}
	
	//method to get order collect date and time
	public String getOrderCollectDateTime() {
		return orderCollectDateTime;
	}
	
	//method to set order place date and time
	public void setOrderPlaceDateTime(String orderPlaceDateTime) {
		this.orderPlaceDateTime = orderPlaceDateTime;
	}
	
	//method to set order collect date and time
	public void setOrderCollectDateTime(String orderCollectDateTime) {
		this.orderCollectDateTime = orderCollectDateTime;
	}
	
	//method to set order status
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	//method to set total time 
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	//method to set total price
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//method to calculate total price of the order
	public double calculateTotalPrice() {
	    Map<FoodItem, Integer> foodItems = shoppingBasket.getFoodItemsList();
	    for (Map.Entry<FoodItem, Integer> entry : foodItems.entrySet()) {
	        FoodItem foodItem = entry.getKey();
	        int quantity = entry.getValue();
	        double itemPrice = foodItem.getPrice();
	        totalPrice += itemPrice * quantity;
	    }
	    return totalPrice;
	}
}
