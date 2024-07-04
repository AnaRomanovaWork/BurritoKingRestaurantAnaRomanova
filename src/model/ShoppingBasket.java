//shopping basket class represent functionality of shopping basket

package model;
import java.util.HashMap;
import java.util.Map;


public class ShoppingBasket {
	private Map<FoodItem, Integer> FoodItemsList = new HashMap<>();
	
	
	public Map<FoodItem, Integer> getFoodItemsList() {
		return FoodItemsList;
	}
	
	//method to add food item to shopping basket
	public String addFoodItem(FoodItem foodItem, int quantity) {
	    if (quantity < 1) {
	        return "Quantity cannot be less than 1";
	    } else {
	        String foodItemName = foodItem.getName();
	        if (FoodItemsList.containsKey(foodItem)) {
	            int currentQuantity = FoodItemsList.get(foodItem);
	            FoodItemsList.put(foodItem, currentQuantity + quantity);
	        } else {
	            FoodItemsList.put(foodItem, quantity);
	        }
	        return foodItemName + " successfully added!";
	    }
	} 
	
	//method to add meal to shopping basket
	public String addMeal(Meal meal, int quantity) {
	    if(quantity < 1) {
	        return "Quantity cannot be less than 1";
	    } else {
	        String mealName = meal.getName();
	        
	        
	        FoodItem burrito = meal.getBurrito();
	        FoodItem fries = meal.getFries();
	        FoodItem soda = meal.getSoda();
	        
	        
	        String message = "";
	        message += addFoodItem(burrito, quantity);
	        message += addFoodItem(fries, quantity);
	        message += addFoodItem(soda, quantity);
	        
	        return mealName + " successfully added!\n" + message;
	    }
	}
	
	//method to update quantity of specific item
	public void updateQuantity(FoodItem foodItem, int quantity) { 
		if (FoodItemsList.containsKey(foodItem)) {
	        FoodItemsList.put(foodItem, quantity);
		} else {
			System.out.println("This food item is not in shopping cart");
		}
	}
	
	//method to remove food item from basket
	public void removeFoodItem(FoodItem foodItem) {
		if (FoodItemsList.containsKey(foodItem)) {
			FoodItemsList.remove(foodItem);
		} else {
			System.out.println("This food item is not in shopping cart");
		}
		
	}
	
	//method to clear basket
	 public void clearBasket() {
		 FoodItemsList.clear();
	    }
	 
	   
}

