//class represents warming tray and stores remains serves of fries
package model;

public class WarmingTray {
	private static WarmingTray instance;
	private int friesQuantity;
	
	private WarmingTray() {
        friesQuantity = 0;
    }
	
	public static WarmingTray getInstance() {
        if (instance == null) {
            instance = new WarmingTray();
        }
        return instance;
    }
	
	//getter fries quantity method
	public int getFriesQuantity() {
		return friesQuantity;
	}
	
	//setter fries quantity method
	public void setFriesQuantity(int friesQuantity) {
		this.friesQuantity = friesQuantity;
	}
	
	//add 5 serves of fries
	public void addFriesToTray() {
        friesQuantity += 5;
    }
	
	//takes fries from warming tray
	public void takeFriesFromTray(int quantity) {
		friesQuantity -= quantity;
	}
}

