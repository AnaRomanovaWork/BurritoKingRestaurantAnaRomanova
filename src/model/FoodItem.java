//abstract class that is using for soda, burrito, fries items), 
package model;

import java.util.Objects;

public abstract class FoodItem {
	
	private String name;
	private double price;
	private int cookingTime;
	
	//constructor for FoodItem class
	public FoodItem(String name, double price, int cookingTime) {
		this.name = name;
		this.price = price;
		this.cookingTime = cookingTime;
	}
	
	
	
	//getter price method
	public double getPrice() {
		return price;
	}
	
	// setter price method
	public void setPrice(double price){
		this.price = price;
	}
	
	//getter name method
	public String getName() {
		return name; 
	}
	
	//getter cooking time method
	public int getCookingTime() {
		return cookingTime;
	}
	
	//method to apply discount
	public void applyDiscount(double discount) {
		double discountedPrice = getPrice() - discount;
        setPrice(Math.max(discountedPrice, 0));
    }
	
	
	//check if current object equals to the current food item
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        FoodItem foodItem = (FoodItem) o;
	        return Objects.equals(name, foodItem.name);
	    }
	
	 //generate hash code for shopping basket
	 @Override
	    public int hashCode() {
	        return Objects.hash(name);
	    }
}


