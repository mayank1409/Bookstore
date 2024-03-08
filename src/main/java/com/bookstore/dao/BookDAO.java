package com.bookstore.dao;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.util.BookstoreDBUtils;

public class BookDAO {

	private static Connection connection = BookstoreDBUtils.getDatabaseConnection();
	private CategoryDAO categoryDAO = new CategoryDAO();

	public int create(Book book) {
		int row = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(
					"insert into book (title, author, description, isbn, image, price, publish_date, last_update_time, category_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			String title = book.getTitle();
			String author = book.getAuthor();
			String description = book.getDescription();
			String isbn = book.getIsbn();
			byte[] image = book.getImage();
			float price = book.getPrice();
			Date publishDate = book.getPublishDate();
			Date lastUpdateTime = book.getLastUpdateTime();

			java.sql.Date publishDateSQL = new java.sql.Date(publishDate.getTime());
			java.sql.Date lastUpdateTimeSQL = new java.sql.Date(lastUpdateTime.getTime());

			statement.setString(1, title);
			statement.setString(2, author);
			statement.setString(3, description);
			statement.setString(4, isbn);
			statement.setBinaryStream(5, new ByteArrayInputStream(image));
			statement.setFloat(6, price);
			statement.setDate(7, publishDateSQL);
			statement.setDate(8, lastUpdateTimeSQL);
			statement.setInt(9, book.getCategory().getCategoryId());

			row = statement.executeUpdate();
			System.out.println(row + " inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

		return row;
	}

	public int update(Book book) {
		int row = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(
					"update book set title=?, author=?, description=?, isbn=?, image=?, price=?, publish_date=?, last_update_time=?, category_id=? Where book_id=?");

			String title = book.getTitle();
			String author = book.getAuthor();
			String description = book.getDescription();
			String isbn = book.getIsbn();
			byte[] image = book.getImage();
			float price = book.getPrice();
			Date publishDate = book.getPublishDate();
			Date lastUpdateTime = book.getLastUpdateTime();

			java.sql.Date publishDateSQL = new java.sql.Date(publishDate.getTime());
			java.sql.Date lastUpdateTimeSQL = new java.sql.Date(lastUpdateTime.getTime());

			statement.setString(1, title);
			statement.setString(2, author);
			statement.setString(3, description);
			statement.setString(4, isbn);
			statement.setBinaryStream(5, new ByteArrayInputStream(image));
			statement.setFloat(6, price);
			statement.setDate(7, publishDateSQL);
			statement.setDate(8, lastUpdateTimeSQL);
			statement.setInt(9, book.getCategory().getCategoryId());
			statement.setInt(10, book.getBookId());

			row = statement.executeUpdate();
			System.out.println(row + " updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

		return row;
	}

	public Book get(Integer id) {
		Book book = null;

		Statement statement;
		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("select * from book where book_id=" + id);

			while (resultSet.next()) {
				Blob blob = resultSet.getBlob(6);
				int categoryId = resultSet.getInt(10);

				Category category = categoryDAO.get(categoryId);

				book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), blob.getBytes(1, (int) blob.length()),
						resultSet.getFloat(7), resultSet.getDate(8), resultSet.getDate(9), category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return book;
	}

	public void delete(Integer id) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from book where book_id=?");
			statement.setInt(1, id);
			int row = statement.executeUpdate();
			System.out.println(row + " updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Book> list() {
		List<Book> books = new ArrayList<Book>();

		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from book");

			while (resultSet.next()) {
				int categoryId = resultSet.getInt(10);
				Category category = categoryDAO.get(categoryId);
				Blob blob = resultSet.getBlob(6);
				Book book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), blob.getBytes(1, (int) blob.length()),
						resultSet.getFloat(7), resultSet.getDate(8), resultSet.getDate(9), category);
				books.add(book);
				blob.free();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		return books;
	}

	public long count() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select count(*) as count from book");
			return resultSet.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Book findByTitle(String title) {
		Book book = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from book where title='" + title + "'");

			while (resultSet.next()) {
				Blob blob = resultSet.getBlob(6);
				int categoryId = resultSet.getInt(10);
				Category category = categoryDAO.get(categoryId);
				book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), blob.getBytes(1, (int) blob.length()),
						resultSet.getFloat(7), resultSet.getDate(8), resultSet.getDate(9), category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return book;
	}

	public List<Book> listByCategory(Integer categoryId) {
		List<Book> books = new ArrayList<Book>();

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("select * from book Where category_id=?");

			statement.setInt(1, categoryId);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Category category = categoryDAO.get(categoryId);
				Blob blob = resultSet.getBlob(6);
				Book book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), blob.getBytes(1, (int) blob.length()),
						resultSet.getFloat(7), resultSet.getDate(8), resultSet.getDate(9), category);
				books.add(book);
				blob.free();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

		return books;
	}
}
