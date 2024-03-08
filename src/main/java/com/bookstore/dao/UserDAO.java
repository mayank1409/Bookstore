package com.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Users;
import com.bookstore.util.BookstoreDBUtils;

public class UserDAO {

	private static Connection connection = BookstoreDBUtils.getDatabaseConnection();

	public int create(Users user) {
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into users values (" + user.getUserId() + ",'"
					+ user.getEmail() + "','" + user.getPassword() + "','" + user.getFullName() + "')");
			System.out.println(result + " user inserted successfully");
			return result;
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
			return 0;
		}
	}

	public int update(Users user) {
		try {
			Statement statement = connection.createStatement();
			int result = statement
					.executeUpdate("update users set email='" + user.getEmail() + "', password='" + user.getPassword()
							+ "', full_name='" + user.getFullName() + "' where user_id=" + user.getUserId());
			System.out.println(result + " rows updated successfully");
			return result;
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
			return 0;
		}
	}

	public Users get(Integer id) {
		Users user = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users where user_id=" + id);
			while (resultSet.next()) {
				user = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4));
			}
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
		}

		return user;
	}

	public void delete(Integer id) {
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("delete from users where user_id=" + id);
			if (result > 0) {
				System.out.println(result + " rows deleted successfully");
			}
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
		}
	}

	public List<Users> list() {
		List<Users> users = new ArrayList<Users>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users");
			while (resultSet.next()) {
				Users user = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4));
				users.add(user);
			}
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
		}

		return users;
	}

	public long count() {
		long count = 0l;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select count(*) from users");
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
		}
		return count;
	}

	public Users findByEmail(String email) {
		Users user = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users where email='" + email + "'");
			while (resultSet.next()) {
				user = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
			}
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
		}

		return user;
	}

	public Users checkLogin(String email, String password) {
		Users user = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from users where email='" + email + "'" + " and password='" + password + "'");
			while (resultSet.next()) {
				user = new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
			}
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
		}

		return user;
	}

}
