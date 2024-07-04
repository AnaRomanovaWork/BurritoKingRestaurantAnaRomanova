//class Meal represents meal functionality

package model;

public class Meal {
    private String name;
    private FoodItem burrito;
    private FoodItem fries;
    private FoodItem soda;
    private double discount;

    public Meal() {
        this.name = "Meal";
        this.burrito = new Burrito();
        this.fries = new Fries();
        this.soda = new Soda();
        this.discount = 1.00;
        applyDiscountToItems();
        
    }

    //method to calculate meal cost
    public double calculateMealCost() {
    	double totalCost = burrito.getPrice() + fries.getPrice() + soda.getPrice();
    	return totalCost;
    }
    
    //method to apply discount
    private void applyDiscountToItems() {
        burrito.applyDiscount(discount);
        fries.applyDiscount(discount);
        soda.applyDiscount(discount);
    }
    
    
	
	//getter discount method
	public double getDiscount() {
		return discount;
	}
	
	//setter discount method
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	//getter name method
	public String getName() {
		return name;
	}
	
	//method to get burrito
	public FoodItem getBurrito() {
		return burrito;
	}
	
	//method to get fries
	public FoodItem getFries() {
		return fries;
	}
	
	//method to get soda
	public FoodItem getSoda() {
		return soda;
	}
}
