# BurritoKingRestaurantAnaRomanova

Application for ordering multiple food items.

This project was an assignment for the Advanced Programming Course(Java).

I started to learn Java in February, this application was finished in June.

GUI was made with SceneBuilder.

I used singleton and facade design patterns.

All data about users and orders are stored in the SQL database. 

## Main functionality

**Log in**


![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/17d25612-9aec-4775-8b55-fe7bf2ecc741)

If the user inputs the wrong password, the application displays error.

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/520d19e8-4bd2-4a7a-8801-96a478b9240b)


**Sign up**

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/945e346b-6957-4193-9d39-062dd154db06)

As the username should be unique, the application will show an error for the existing username.

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/e0db8b75-9084-4243-a721-019c68a3343a)


**Main Menu**

New users cannot see any active orders. The application only displays greetings.
![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/41426cc9-f3a2-4810-8bae-1a993817c157)

Example of active orders' dashboard for a user

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/c4eaf7bc-3c97-4991-9732-6f7ff1f950a1)

The user can edit profile data and can become premium user

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/3e5ca693-42b3-4cbb-bb39-34b963d9df75)

The user has the following options related to orders

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/99ad3579-7df0-40e3-a0db-b3353abafb96)


**Edit profile**

Change first name/last name/password, username is not changeable

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/5a63f338-7134-4591-bfa6-9ff9f35fb6c3)

**Become Premium User**

There are more advanced features for VIP users. When a new user is registered, the user is a non-VIP user by default. Once a non-VIP user logs in, the user has the option of upgrading to a VIP user. To upgrade, the application asks if the user agrees to receive promotions.
The user will become a VIP once they agree and provide a valid email address. Then the user can
access VIP functionalities:

Please log out and log in again to access VIP functionalities.

A VIP user has the following additional functionalities:
* Ordering a meal (a burrito, one serve of fries, and a soda), with a discount of $3.
* Collecting credits for all orders. The user can earn one credit for each dollar spent.
* Use credits when paying for orders. The program should ask the user if he/she wants to redeem credits when checking out. If yes, ask the user to specify the number of credits to redeem. Every 100 credits can be used as one dollar. If credits are redeemed in payment, the final order price of this order should be recorded as the actual amount paid by user. In other functionalities, the final order price will be used. For example, when “Viewing all orders”, user will only see the actual price paid for that order. In “Collecting credits”, the user can collect credits for the actual paid amount.

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/3eed3f96-cbaf-49ba-9e29-802450030546)

Invalid email error

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/7b8ff6bb-5c58-4120-83fe-da2d1f5d650e)

Agree to recieve promotion error

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/71d48b74-0f2d-48a3-bf08-864d20f4a0e2)

## Make Order ##

### Order Menu for a Premium User ### 

Includes Meal - 1 burrito, 1 serve of fries, 1 soda with a price reduction of 1$ for each item
![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/a8380bc0-8862-4896-9279-836ce422160a)

### Order Menu for a Regular User ### 

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/eef880b2-468a-43aa-bf6c-4faac1fc302c)

### Checkout Menu ###

Users can update quantity or remove item

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/18cf91a3-e733-4463-b992-88d65a01422f)

### Payment ###

Payment menu performs simple vaidation to check:
  * if the card number has 16 digits
  * if the expiry date is a future date
  * if cvv has 3 digits

**Payment menu for Premium user**

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/6c580f73-73ff-4e56-829b-8b5b2f07f35a)

**Payment menu for Regular user**

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/e72416c8-c065-49f4-a0d3-ea47a2b9af4c)

## View all orders ##
The user can view the following details of all orders: the day and time that the order was placed, the total price, and the status of the orders.

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/1825e9c7-60f9-4864-8064-2adadf683139)

## Collect order ##

A user can collect an order. The GUI allow the user to enter a “fake” time at which the order is collected. Collect time must be at least order-placed time + time to prepare.

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/011f5e77-b433-4742-beee-cdbd1fb81166)

## Cancel order ##

A user can cancel an order that has not been “collected”.

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/6b8f1d6d-0c53-4ecb-aa74-576305b37645)


## Export all orders ##

The user can export all orders to a csv file. The user can choose what order information to be exported. Also, the user can select the destination of the file and specify the name. 

![image](https://github.com/AnaRomanovaWork/BurritoKingRestaurantAnaRomanova/assets/113778877/e7e221f0-95b1-4c59-9c47-0deb8e612250)







