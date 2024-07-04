//class to connect database

package model.sqlitedb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String DB_URL = "jdbc:sqlite:ApplicationDB.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}
}
