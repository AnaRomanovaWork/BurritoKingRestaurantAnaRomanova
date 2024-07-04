//user table stores username, password, first name, last name)

package model.sqlitedb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;


public class UserDB {
	final static String TABLE_NAME = "Users";
	public static void createUserTable() {
		try (Connection con = DatabaseConnection.getConnection();
	             Statement stmt = con.createStatement()) {
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
	                    "(username VARCHAR(50) PRIMARY KEY UNIQUE, " +
	                    "password VARCHAR(50) NOT NULL, " +
	                    "first_name VARCHAR(50) NOT NULL, " +
	                    "last_name VARCHAR(50) NOT NULL)");
	            System.out.println("User table created successfully");

	        } catch (SQLException e) {
	            System.out.println("Error creating user table: " + e.getMessage());
	        }
	    }
	
	//add user to table
	public static String addUser(User user){
		 String query = "INSERT INTO " + TABLE_NAME + " (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";
		 try (Connection con = DatabaseConnection.getConnection();
	            PreparedStatement pstmt = con.prepareStatement(query)) {
	            pstmt.setString(1, user.getUserName());
	            pstmt.setString(2, user.getUserPassword());
	            pstmt.setString(3, user.getUserFirstName());
	            pstmt.setString(4, user.getUserLastName());
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	            	return "User added successfully";
	            } else {
	            	return "Failed to add user";
	            }
	        } catch (SQLException e) {
	        	return "Error adding user: Username already exists";
	        }
	    }
	
	//select user 
	public static User selectUser(String username) {
	    String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
	    
	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setString(1, username);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String usernameR = resultSet.getString("username");
	                String passwordR = resultSet.getString("password");
	                String firstNameR = resultSet.getString("first_name");
	                String lastNameR = resultSet.getString("last_name");

	                return new User(usernameR, passwordR, firstNameR, lastNameR);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	//update first name of specific user
	public static String updateUserFirstName(User user, String firstName) {
		String sql = "UPDATE " + TABLE_NAME + " SET first_name = ? WHERE username = ?";
		try (Connection con = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
			
	            pstmt.setString(1, firstName);
	            pstmt.setString(2, user.getUserName());
	            
	            int rowsUpdated = pstmt.executeUpdate();
	            if (rowsUpdated > 0) {
	                return "";
	            } else {
	            	return "No user found with username: " + user.getUserName();
	            }
	            
	        } catch (SQLException e) {
	        	return "Error updating user's first name: " + e.getMessage();
	        }
	    }
	
	//update last name of the user
	public static String updateUserLastName(User user, String lastName) {
		String sql = "UPDATE " + TABLE_NAME + " SET last_name = ? WHERE username = ?";
		try (Connection con = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
			
	            pstmt.setString(1, lastName);
	            pstmt.setString(2, user.getUserName());
	            
	            int rowsUpdated = pstmt.executeUpdate();
	            if (rowsUpdated > 0) {
	                return "";
	            } else {
	                return "No user found with username: " + user.getUserName();
	            }
	            
	        } catch (SQLException e) {
	        	return "Error updating user's last name: " + e.getMessage();
	        }
	    }
	
	//update user password
	public static String updateUserPassword(User user, String password) {
		String sql = "UPDATE " + TABLE_NAME + " SET password = ? WHERE username = ?";
		try (Connection con = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
			
	            pstmt.setString(1, password);
	            pstmt.setString(2, user.getUserName());
	            
	            int rowsUpdated = pstmt.executeUpdate();
	            if (rowsUpdated > 0) {
	                return "";
	            } else {
	               return "No user found with username: " + user.getUserName();
	            }
	            
	        } catch (SQLException e) {
	            return "Error updating user's password: " + e.getMessage();
	        }
	    }
	
	//delete table
	public static void DeleteTableUserDBTable() {
			final String TABLE_NAME = "Users";
			try (Connection con = DatabaseConnection.getConnection();
					Statement stmt = con.createStatement();) {
				stmt.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	
	
	
}



