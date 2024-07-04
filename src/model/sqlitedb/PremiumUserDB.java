//premium user table stores username email userCredits


package model.sqlitedb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.PremiumUser;


public class PremiumUserDB {
	final static String TABLE_NAME = "PremiumUsers";
	public static void createPremiumUserTable() {
		try (Connection con = DatabaseConnection.getConnection();
	             Statement stmt = con.createStatement()) {
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
	                    "(username VARCHAR(50) PRIMARY KEY UNIQUE, " +
	                    "email VARCHAR(50) NOT NULL UNIQUE, " +
	                    "userCredits REAL , " +
	            		"FOREIGN KEY (username) REFERENCES Users(username))");
	        } catch (SQLException e) {
	            System.out.println("Error creating user table: " + e.getMessage());
	        }
	    }
	
	//add premium user to table
	public static String addPremiumUser(PremiumUser premiumUser){
		 String query = "INSERT INTO " + TABLE_NAME + " (username, email, userCredits) VALUES (?, ?, ?)";
		 try (Connection con = DatabaseConnection.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setString(1, premiumUser.getUserName());
	            pstmt.setString(2, premiumUser.getUserEmail());
	            pstmt.setDouble(3, premiumUser.getUserCredits());
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                return "Successfully became a premium user!\nPlease log out and log in again to access VIP functionalities.";
	            } else {
	                return "Failed to add user";
	            }
	        } catch (SQLException e) {
	            if (e.getMessage().contains("UNIQUE")) {
	                return "Error: A user with this email already exists.";
	            } else {
	                return "Error: " + e.getMessage();
	            }
	        }
	    }
	
	//select premium user
	public static PremiumUser selectUser(String username) {
		String sql = "SELECT u.username, u.password, u.first_name, u.last_name, p.email, p.userCredits " +
					"FROM Users u "+
					"JOIN PremiumUsers p ON u.username = p.username " +
					"WHERE u.username = ? ";
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement statement = con.prepareStatement(sql)) {
	         statement.setString(1, username);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String usernameR = resultSet.getString("username");
	                String passwordR = resultSet.getString("password");
	                String firstNameR = resultSet.getString("first_name");
	                String lastNameR = resultSet.getString("last_name");
	                String emailR = resultSet.getString("email");
	                double userCreditsR = resultSet.getDouble("userCredits");
	                return new PremiumUser(usernameR, passwordR, firstNameR, lastNameR, emailR, userCreditsR);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	//update user credits
	public static void updateUserCredits(PremiumUser premiumUser) {
		String sql = "UPDATE " + TABLE_NAME + " SET userCredits = ? WHERE username = ?";
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setDouble(1,	premiumUser.getUserCredits());
	            pstmt.setString(2, premiumUser.getUserName());
	            
	            int rowsUpdated = pstmt.executeUpdate();
	            
	            if (rowsUpdated > 0) {
	                System.out.println("User's credits updated successfully");
	            } else {
	                System.out.println("No user found with username: " + premiumUser.getUserName());
	            }
	            
	        } catch (SQLException e) {
	            System.out.println("Error updating user's credits: " + e.getMessage());
	        }
	}
	
	//delete table
	public static void DeleteTablePremiumUserDBTable() {
		try (Connection con = DatabaseConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
		
}
	
	


