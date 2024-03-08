package com.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Category;
import com.bookstore.util.BookstoreDBUtils;

public class CategoryDAO {

	private static Connection connection = BookstoreDBUtils.getDatabaseConnection();

	public int create(Category category) {
		int row = 0;
		try {
			Statement statement = connection.createStatement();
			row = statement.executeUpdate("insert into category(name) values('" + category.getName() + "')");
			System.out.println(row + " inserted successfully");
			return row;
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
			return 0;
		}

	}

	public int update(Category category) {
		int row = 0;
		try {
			Statement statement = connection.createStatement();
			row = statement.executeUpdate("update category set name='" + category.getName() + "' where category_id="
					+ category.getCategoryId());
			System.out.println(row + " inserted successfully");
			return row;
		} catch (SQLException e) {
			BookstoreDBUtils.handleDBException(e);
			return 0;

		}

	}

	public Category get(Integer id) {
		Category category = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from category where category_id=" + id);

			while (resultSet.next()) {
				category = new Category(resultSet.getInt(1), resultSet.getString(2));
			}

		} catch (SQLException e) {
		}

		return category;
	}

	public void delete(Integer id) {
		try {
			Statement statement = connection.createStatement();
			int row = statement.executeUpdate("delete from category where category_id=" + id);
			System.out.println(row + " deleted successfully");
		} catch (SQLException e) {
		}

	}

	public List<Category> list() {
		List<Category> categories = new ArrayList<Category>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from category");

			while (resultSet.next()) {
				Category category = new Category(resultSet.getInt(1), resultSet.getString(2));
				categories.add(category);
			}

		} catch (SQLException e) {
		}

		return categories;
	}

	public long count() {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select count(*) as count from category");
			resultSet.getInt("count");
		} catch (SQLException e) {
		}

		return 0;
	}

	public Category findByName(String categoryName) {
		Category category = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from category where name='" + categoryName + "'");

			while (resultSet.next()) {
				category = new Category(resultSet.getInt(1), resultSet.getString(2));
			}

		} catch (SQLException e) {
		}

		return category;
	}

}
