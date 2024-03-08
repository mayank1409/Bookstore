package com.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bookstore.exception.DatabaseException;

public class BookstoreDBUtils {

	private static Connection conn;
	private static String password = "password";
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/bookstoredb";
	private static String username = "root";

	public static Connection getDatabaseConnection() {
		if (conn == null) {
			conn = createConnection();
		}
		return conn;
	}

	private static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static DatabaseException handleDBException(SQLException exception) {
		return new DatabaseException(exception.getMessage());
	}
}
