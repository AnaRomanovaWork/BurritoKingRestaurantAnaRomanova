//Kitchen class represents methods of cooking food items

package model;

public class Kitchen {
	private Fries fries = new Fries();
	private Burrito burrito = new Burrito();
	private WarmingTray warmingTray;
	
	
	public Kitchen() {
        warmingTray = WarmingTray.getInstance();
    }
	
	//method to get cooking time for fries
	public int cookingFries(int quantity){
		int time = 0;
		while (quantity > 0) {
			if (warmingTray.getFriesQuantity() > 0) {
		    	int friesTaken = Math.min(quantity, warmingTray.getFriesQuantity());
		    	warmingTray.takeFriesFromTray(friesTaken);
		    	quantity -= friesTaken;
			}else if(warmingTray.getFriesQuantity() == 0) {
		        	time += fries.getCookingTime();
		            warmingTray.addFriesToTray();
		            int friesTaken = Math.min(quantity, 5);
		            warmingTray.takeFriesFromTray(friesTaken);
		            quantity -= friesTaken;
			}
	        
	    }
	    return time;
	}
	
	// method to get cooking time for burrito
	public int cookingBurrito(int quantity) {
	    int time = 0;
	    if (quantity % 2 == 0) {
	        time = burrito.getCookingTime() * (quantity / 2);
	    } else {
	        time = burrito.getCookingTime() * ((quantity / 2) + 1);
	    }

	    return time;
	}
	
		
	
}
