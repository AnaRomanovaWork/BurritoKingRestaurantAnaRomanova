//Shopping basket list class represent functionality which help store data to temporary shopping basket database

package model;

public class ShoppingBasketList {
	    private String foodItemName;
	    private int foodItemQuantity;
	    private double totalItemPrice;

	    public ShoppingBasketList(String foodItemName, int foodItemQuantity, double totalItemPrice) {
	        this.foodItemName = foodItemName;
	        this.foodItemQuantity = foodItemQuantity;
	        this.totalItemPrice = totalItemPrice;
	    }

	    //get food item name
	    public String getFoodItemName() {
	        return foodItemName;
	    }
	    
	    //set food item name
	    public void setFoodItemName(String foodItemName) {
	        this.foodItemName = foodItemName;
	    }
	    
	    //get food item quantity
	    public int getFoodItemQuantity() {
	        return foodItemQuantity;
	    }
	    
	    //set food item quantity
	    public void setFoodItemQuantity(int foodItemQuantity) {
	        this.foodItemQuantity = foodItemQuantity;
	    }
	    
	    //get total price
	    public double getTotalItemPrice() {
	        return totalItemPrice;
	    }
	    
	    //set total price
	    public void setTotalItemPrice(double totalItemPrice) {
	        this.totalItemPrice = totalItemPrice;
	    }
}

