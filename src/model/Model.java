//class model represent interface functionality

package model;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface Model {
	
	public void saveUserInput(String userInput);
	public String loadUserInput();
	public String validateRegistration(String username, String password, String firstName, String lastName);
	public String validateUser(String username);
	public String validatePassword(String username, String password);
	public String validateUserInput(String userInput, String fieldName);
	public String addUserValidation(String username, String password, String firstName, String lastName);
	public String getFirstNameOfUser(String username);
	public String getLastNameOfUser(String username);
	public List<AllOrdersInfo> getUserOrders();
	public String getPassword(String username);
	public String changePassword(String username, String newPassword);
	public String changeFirstName(String username, String newFirstName);
	public String changeLastName(String username, String newLastName);
	public String becomePremiumUser(String username, String email);
	public String emailValidation(String email);
	public PremiumUser getPremiumUser(String username);
	public boolean checkIfPremiumUser(String username);
	public void clearUserInput();
	public int getQuantity(TextField textField);
	public double updateBurritoPrice(int quantity);
	public void addBurrito(int quantity, Label label);
	public String updateTotalCost(FoodItem foodItem, double totalCost, int quantity);
	public double updateFriesPrice(int quantity);
	public void addFries(int quantity, Label label);
	public double updateSodaPrice(int quantity);
	public void addSoda(int quantity, Label label);
	public double updateMealPrice(int quantity);
	public void addMeal(int quantity, Label label); 
	public ShoppingBasket getShoppingBasket();
	public void addToShoppingBasketTable(ShoppingBasket shoppingBasket);
	public void deleteShoppingBasketTable();
	public List<ShoppingBasketList> getShoppingBasketList();
	public void clearShoppingBasket(ShoppingBasket shoppingbasket);
	public void removeFoodItem(String foodItemName);
	public void updateQuantity(FoodItem foodItem, int qiuantity);
	public FoodItem getFoodItem(String name);
	public WarmingTray getWarmingTray();
	public int calculateTotalTime();
	public FoodItem getFoodItemByName(String foodItemName);
	public void loadShoppingBasketFromDatabase();
	public double calculateTotalPrice();
	public String cardValidation(String cardNumber, String cardExpiryDate, String cardCvv);
	public String validateDateTime(String orderTime);
	public Order placeOrder(String orderDateTime, double userCredits);
	public void addOrderToDB(String userInput, Order order);
	public double getCredits(String username);
	public String validateDoubleInput(String enteredCredits, double userCredits);
	public void reduceCreditPoints(String username, double credits);
	public void updateCreditPoints(PremiumUser premiumUser, double totalPrice);
	public String validateCollectionDate(String username, int orderNumber, String orderCollectionTime);
	public String collectOrder(int orderNumber, String orderCollectionTime);
	public String cancelOrder(int orderNumber);
}
