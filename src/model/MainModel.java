//Main model class connect models' classes with controllers(implements interface Model)

package model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.sqlitedb.OrderDB;
import model.sqlitedb.OrderInfoDB;
import model.sqlitedb.OrderedFoodItemsDB;
import model.sqlitedb.PremiumUserDB;
import model.sqlitedb.ShoppingBasketTempDB;
import model.sqlitedb.UserDB;

public class MainModel implements Model {
	private static Model model;
	private Session session;
	private ShoppingBasket shoppingBasket;
	private WarmingTray warmingTray;
	
	private MainModel() {
		session = new Session();
		shoppingBasket = new ShoppingBasket();
	}
	
	//method to get instance of the model
	public static Model getInstance() {
		if(model == null) {
			model = new MainModel();
		}
		return model;
	}
	
	//method to save user input
	@Override
	public void saveUserInput(String userInput) {
		session.setUserInput(userInput);
	}
	
	//method to clear user input
	public void clearUserInput() {
	    session.setUserInput(null);
	}
	
	//method to load user input
	@Override
	public String loadUserInput() {
		return session.getUserInput();
	}
	
	//method to get warming tray object
	@Override
	public WarmingTray getWarmingTray() {
		return warmingTray;
	}
	
	//method to validate user input
	@Override
	public String validateUserInput(String userInput, String fieldName) {
	    if (userInput.isEmpty()) {
	        return fieldName + " cannot be empty.";
	    }
	    return "";
	}
	
	//method to validate user registration
	@Override
	public String validateRegistration(String username, String password, String firstName, String lastName) {
	    User user = new User(username, password, firstName, lastName);
	    return UserDB.addUser(user);
	}
	
	
	//method to validate User object
	@Override
	public String validateUser(String username) {
		if (username.isEmpty()) {
	        return "Username cannot be empty.";
	        }
		User user = UserDB.selectUser(username);
		if (user == null) {
			return "Wrong username. Please check or sign up.";
		  }
		  return "";
	}
// method to validate password
	@Override
	 public String validatePassword(String username, String password) {
		if(password.isEmpty()) {
			return "Password cannot be empty.";
		}
		 User user = UserDB.selectUser(username);
		 if (user == null || !password.equals(user.getUserPassword())){
			 return "Wrong password. Please check.";
		 }
		 return "";
	 }
	
	// method to get the first name of the user
	@Override
	
	public String getFirstNameOfUser(String username) {
		User user = UserDB.selectUser(username);
		String firstName = user.getUserFirstName();
		return firstName;
	}
	
	@Override
	//method to get the last name of the user
	public String getLastNameOfUser(String username) {
		User user = UserDB.selectUser(username);
		String lastName = user.getUserLastName();
		return lastName;
	}
	
	@Override
	//method to get password
	public String getPassword(String username) {
		User user = UserDB.selectUser(username);
		String password = user.getUserPassword();
		return password;
	}
	
	@Override
	//method to add user validation for controller
	public String addUserValidation(String username, String password, String firstName, String lastName){
		User newUser = new User(username, password, firstName, lastName);
		String validationMessage = UserDB.addUser(newUser);
		return validationMessage;	
	}
	
	//method to get the list of active orders to display them in the dashbord(LoggedIn view)
	@Override
	public List<AllOrdersInfo> getUserOrders() {
        String userName = session.getUserInput();
        return OrderExplorer.viewAllActiveUserOrders(userName);
    }
	
	//method to change password
	@Override 
	public String changePassword(String username, String newPassword){
		User user = UserDB.selectUser(username);
		String outputMessage = UserDB.updateUserPassword(user, newPassword);
		return outputMessage;
	}
	
	//method to change the first name of the use
	@Override 
	public String changeFirstName(String username, String newFirstName){
		User user = UserDB.selectUser(username);
		String outputMessage = UserDB.updateUserFirstName(user, newFirstName);
		return outputMessage;
	}
	
	//method to change the last name
	@Override 
	public String changeLastName(String username, String newLastName){
		User user = UserDB.selectUser(username);
		String outputMessage = UserDB.updateUserLastName(user, newLastName);
		return outputMessage;
		}
	
	//method to become a premium user
	@Override 
	public String becomePremiumUser(String username, String email) {
		PremiumUserDB.createPremiumUserTable();
		User user = UserDB.selectUser(username);
		PremiumUser premiumUser = new PremiumUser(user.getUserName(), user.getUserPassword(), user.getUserFirstName(), user.getUserLastName(), email);
		String outputMessage = PremiumUserDB.addPremiumUser(premiumUser);
		return outputMessage;
	}
	
	//email validation method 
	@Override 
	 public String emailValidation(String email) {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null || email.trim().isEmpty()) {
	            return "Email field is empty.\nPlease enter your email";
	        } else if (pat.matcher(email).matches()) {
	            return "";
	        } else {
	            return "Invalid email.";
	        }
	    }
	 
	//method to get premium user
	@Override 
	 public PremiumUser getPremiumUser(String username) {
		    PremiumUser premiumUser = PremiumUserDB.selectUser(username);
		    return premiumUser;
		}
	//method to check if user has premium functionality
	@Override 
	 public boolean checkIfPremiumUser(String username) {
		    PremiumUser premiumUser = getPremiumUser(username);
		    if (premiumUser != null) {
		        return true;
		    } else {
		        return false;
		    }
		}
	
	//method to update burrito price for order a meal
	@Override 
	public double updateBurritoPrice(int quantity) {
		Burrito burrito = new Burrito();
		double price = burrito.getPrice() * quantity;
		return price;
	}
	 
	//method to add burrito to shopping basket
	@Override 
	 public void addBurrito(int quantity, Label label) {
		 Burrito burrito = new Burrito();
		 String outputMessage = shoppingBasket.addFoodItem(burrito, quantity);
		 label.setText(outputMessage);
	 }
	
	//method to update fries price for order a meal
	@Override 
	public double updateFriesPrice(int quantity) {
		Fries fries = new Fries();
		double price = fries.getPrice() * quantity;
		return price;
	}
	
	//method to add fries to shopping basket
	@Override 
	 public void addFries(int quantity, Label label) {
		 Fries fries = new Fries();
		 String outputMessage = shoppingBasket.addFoodItem(fries, quantity);
		 label.setText(outputMessage);
	 }
	
	// method to update soda price for order a meal
	@Override 
	public double updateSodaPrice(int quantity) {
		Soda soda = new Soda();
		double price = soda.getPrice() * quantity;
		return price;
	}
	
	//method to add soda to shopping basket
	@Override 
	 public void addSoda(int quantity, Label label) {
		 Soda soda = new Soda();
		 String outputMessage = shoppingBasket.addFoodItem(soda, quantity);
		 label.setText(outputMessage);
	 }
	//method to calculate total price of a meal
	@Override 
	public double updateMealPrice(int quantity) {
		Meal meal = new Meal();
		double price = meal.calculateMealCost() * quantity;
		return price;
	}
	
	//method to add a meal
	@Override 
	 public void addMeal(int quantity, Label label) {
		 Meal meal = new Meal();
		 String outputMessage = shoppingBasket.addMeal(meal, quantity);
		 label.setText(outputMessage);
	 }
	
	//method to get a shopping basket
	@Override 
	public ShoppingBasket getShoppingBasket() {
		return shoppingBasket;
	}
	
	//method to clear a shopping basket
	@Override
	
	public void clearShoppingBasket(ShoppingBasket shoppingbasket) {
		shoppingBasket.clearBasket();
	}
	
	//method to add current shopping basket to temporary shopping basket table
	@Override
	public void addToShoppingBasketTable(ShoppingBasket shoppingBasket) {
		ShoppingBasketTempDB.createTempTable();
		Map<FoodItem, Integer> foodItemsList = shoppingBasket.getFoodItemsList();
		for (Map.Entry<FoodItem, Integer> entry : foodItemsList.entrySet()) {
            FoodItem foodItem = entry.getKey();
            int quantity = entry.getValue();
            ShoppingBasketTempDB.addFoodItem(foodItem, quantity);
		}
            
	}
	
	//method delete temporary shopping basket table
	@Override
	public void deleteShoppingBasketTable() {
		ShoppingBasketTempDB.deleteTableShoppingBasket();
	}
	
	//method to get shopping basket list to represent it in order checkout view
	@Override
	public List<ShoppingBasketList> getShoppingBasketList() {
        return ShoppingBasketTempDB.shoppingBasketForTable();
    }
	
	//method to remove items from shopping basket table 
	@Override
	public void removeFoodItem(String foodItemName) {
		ShoppingBasketTempDB.deleteItem(foodItemName);
	}
	
	
	//method to update items in a shopping basket
	@Override
	
	public void updateQuantity(FoodItem foodItem, int qiuantity) {
		ShoppingBasketTempDB.updateQuantity(foodItem, qiuantity);
		loadShoppingBasketFromDatabase(); 
	}
	
	//method to get food item
	@Override
	public FoodItem getFoodItem(String name) {
		if(name.equals("Burrito")) {
			FoodItem burrito = new Burrito();
			return burrito;
		}else if(name.equals("Fries")){
			FoodItem fries = new Fries();
			return fries;
		}else {
			FoodItem soda = new Soda();
			return soda;
		}
		
	}
	
	//method to update a total cost of an order
	@Override
	public String updateTotalCost(FoodItem foodItem, double totalCost, int quantity) {
		double cost = foodItem.getPrice() * quantity;
		totalCost = totalCost + cost;
		return String.valueOf(totalCost);
	}
	
	//method to get quantity
	@Override 
	 public int getQuantity(TextField textField) {
	        try {
	            return Integer.parseInt(textField.getText());
	        } catch (NumberFormatException e) {
	            return 0;
	        }
	    }
	
	//method to calculate total cooking time
	@Override
    public int calculateTotalTime() {
        int friesCookingTime = 0;
        int burritoCookingTime = 0;

        loadShoppingBasketFromDatabase();
        Kitchen kitchen = new Kitchen();
        Map<FoodItem, Integer> foodItems = shoppingBasket.getFoodItemsList();

        for (Map.Entry<FoodItem, Integer> entry : foodItems.entrySet()) {
            FoodItem foodItem = entry.getKey();
            
            int quantity = entry.getValue();

            if (foodItem instanceof Fries) {
                friesCookingTime = kitchen.cookingFries(quantity);
            } else if (foodItem instanceof Burrito) {
                burritoCookingTime = kitchen.cookingBurrito(quantity);
            }
        }

        return Math.max(burritoCookingTime, friesCookingTime);
    }
	
	//method to get food item  by name
	@Override
	public FoodItem getFoodItemByName(String foodItemName) {
	    switch (foodItemName) {
	        case "Fries":
	            return new Fries();
	        case "Burrito":
	            return new Burrito();
	        default:
	            return new Soda(); 
	    }
	}

	//method to load shopping basket from table
	@Override
	 public void loadShoppingBasketFromDatabase() {
			shoppingBasket.clearBasket(); 
	        List<ShoppingBasketList> basketItems = ShoppingBasketTempDB.shoppingBasketForTable();
	        for (ShoppingBasketList basketItem : basketItems) {
	            String foodItemName = basketItem.getFoodItemName();
	            int quantity = basketItem.getFoodItemQuantity();

	            FoodItem foodItem = getFoodItemByName(foodItemName);
	            shoppingBasket.addFoodItem(foodItem, quantity);
	        
	    }
	  }
	
	//method to calculate total price
	@Override
	public double calculateTotalPrice() {
		return ShoppingBasketTempDB.calculateTotalPrice();
	}
	
	//method to validate credit card
	@Override
	public String cardValidation(String cardNumber, String cardExpiryDate, String cardCvv) {
		CreditCard creditCard = new CreditCard(cardNumber,cardExpiryDate, cardCvv);
		String output = creditCard.validateCard();
		return output;
	}
	
	//method to validate day/time
	
	@Override
	public String validateDateTime(String orderTime) {
		if(orderTime.equals(null)) {
			return "Invalid order date and time";
		}else {
			try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	            LocalDateTime.parse(orderTime, formatter);
	            return "";
	        } catch (DateTimeParseException e) {
	            return "Invalid order date and time";
	        }
		}
		
	}
	
	//method to place an order
	@Override
	public Order placeOrder(String orderDateTime, double userCredits) {
		loadShoppingBasketFromDatabase();
		int orderNumber = OrderDB.getHighestOrderID() + 1;
		Order order = new Order(shoppingBasket, orderNumber);
		double totalSum = order.getTotalPrice() - userCredits/100;
		order.setTotalPrice(totalSum);
		order.setOrderPlaceDateTime(orderDateTime);
		order.setOrderStatus("PLACED");
		int totalTime = calculateTotalTime();
		order.setTotalTime(totalTime);
		String username = loadUserInput();
		boolean premiumUserCheck = checkIfPremiumUser(username);
		if(premiumUserCheck) {
		   PremiumUser premiumUser = PremiumUserDB.selectUser(username);
		   updateCreditPoints(premiumUser, order.getTotalPrice());
		}
		return order;
	}
	
	//method to add order to database
	@Override
	public void addOrderToDB(String userInput, Order order) {
		User user = new User(userInput, getPassword(userInput), getFirstNameOfUser(userInput), getLastNameOfUser(userInput));
		OrderDB.addOrder(user, order);
		OrderInfoDB.addOrderInfo(order);
		OrderedFoodItemsDB.addFoodItem(order);
		shoppingBasket.clearBasket();
	}
	
	//method to get credits of premium user
	@Override 
	public double getCredits(String username) {
		PremiumUser premiumUser = PremiumUserDB.selectUser(username);
		Double credits = premiumUser.getUserCredits();
		return credits;
	}
	
	//method of validation double input
	@Override 
	public String validateDoubleInput(String enteredCredits, double userCredits) {
	    try {
	        double credits = Double.parseDouble(enteredCredits);
	        if (credits > userCredits) {
	            return "Invalid credits value.";
	        }
	        return "";
	    } catch (NumberFormatException e) {
	        return "Invalid credits value.";
	    }
	}
	
	//method to reduce credits for a premium user
	@Override
	 public void reduceCreditPoints(String username, double credits) {
		PremiumUser premiumUser = getPremiumUser(username);
		double currentCredits = premiumUser.getUserCredits();
		double remainCredits = currentCredits - credits;
		premiumUser.setUserCredits(remainCredits);
		PremiumUserDB.updateUserCredits(premiumUser);
	}
	
	//method to update credit points of premium user
	@Override
	public void updateCreditPoints(PremiumUser premiumUser, double totalPrice) {
		double credits = premiumUser.getUserCredits() + totalPrice;
		premiumUser.setUserCredits(credits);
		PremiumUserDB.updateUserCredits(premiumUser);
	}
	
	//method to validate collection time
	@Override
	public String validateCollectionDate(String username, int orderNumber, String orderCollectionTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    Date collectionDate = null;
	    try {
	        collectionDate = dateFormat.parse(orderCollectionTime);
	    } catch (ParseException e) {
	        return "Invalid date format for collection time.";
	    }
		AllOrdersInfo order = OrderExplorer.selectOrder(username, orderNumber);
		int preparationTime = order.getTotalTime();
		Date orderPlaceDateTime = order.getOrderPlaceDateTime();
		Date minimumCollectionDate = new Date(orderPlaceDateTime.getTime() + preparationTime * 60 * 1000);
		if (collectionDate.before(minimumCollectionDate)) {
	        return "Collection time must be at least " + preparationTime + " minutes after the order placed time.";
	    }
		return "";
	}
	
	//method to collect order
	@Override
	public String collectOrder(int orderNumber, String orderCollectionTime) {
		String outputMessage = OrderInfoDB.orderStatusUpdate(orderNumber, "COLLECTED");
		if(!outputMessage.equals("")) {
			return outputMessage;
		}
		String outputMessage1 = OrderInfoDB.orderCollectionTimeUpdate(orderNumber, orderCollectionTime);
		if(!outputMessage1.equals("")) {
			return outputMessage1;
		}
		return "Order successfully collected.";
	}
	
	//method to cancel order
	@Override
	public String cancelOrder(int orderNumber) {
		String outputMessage = OrderInfoDB.orderStatusUpdate(orderNumber, "CANCELLED");
		if(!outputMessage.equals("")) {
			return outputMessage;
		}else {
			return "Order successfully cancelled.";	
		}
		
	}
}
