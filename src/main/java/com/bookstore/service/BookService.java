package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {

	private CategoryDAO categoryDAO;
	private BookDAO bookDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookService(Connection connection, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO();
		categoryDAO = new CategoryDAO();

	}

	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBook = bookDAO.list();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listBook", listBook);
		String listPage = "book_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void showBookForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.list();
		System.out.println(listCategory);
		request.setAttribute("listCategory", listCategory);
		String listPage = "book_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title");
		Book book = bookDAO.findByTitle(title);
		if (book != null) {
			String message = "Could not create book. Book with same title already exists";
			request.setAttribute("message", message);
			String categoryForm = "book_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(categoryForm);
			dispatcher.forward(request, response);
		}
		book = new Book();
		book.setTitle(title);
		readBook(book);

		int row = bookDAO.create(book);
		if (row > 0) {
			listBooks("New Book created successfully");
		} else {
			throw new ServletException();
		}
	}

	private void readBook(Book book) throws ServletException, IOException {

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);

		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		Float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("Error parsing date");
		}

		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setPublishDate(date);
		book.setLastUpdateTime(new Date(System.currentTimeMillis()));
		Part part = request.getPart("image");
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			book.setImage(imageBytes);
		}
	}

	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.list();
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);
		String editPage = "book_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateBook() throws ServletException, IOException {
		System.out.println("Book Id : " + request.getParameter("bookId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookDAO.get(bookId);
		String title = request.getParameter("title");
		Book bookByTitle = bookDAO.findByTitle(title);

		if (!Objects.isNull(bookByTitle) && !bookByTitle.getTitle().equals(book.getTitle())) {
			List<Category> listCategory = categoryDAO.list();
			String message = "Book can not be updated. Book already exists with same title";
			request.setAttribute("message", message);
			request.setAttribute("book", book);
			request.setAttribute("listCategory", listCategory);
			String editPage = "book_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
			dispatcher.forward(request, response);
		} else {
			book.setTitle(title);
			readBook(book);
			int row = bookDAO.update(book);
			if (row > 0) {
				listBooks("Book updated successfully");
			} else {
				throw new ServletException();
			}
		}
	}

	public void deleteBook() throws ServletException, IOException {
		System.out.println("Book Id : " + request.getParameter("bookId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		bookDAO.delete(bookId);
		listBooks("Book deleted successfully");
	}

	public void viewBooksByCategory() throws ServletException, IOException {
		System.out.println("Category Id : " + request.getParameter("categoryId"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = categoryDAO.get(categoryId);
		List<Category> listCategory = categoryDAO.list();
		List<Book> listByCategory = bookDAO.listByCategory(categoryId);

		request.setAttribute("listByCategory", listByCategory);
		request.setAttribute("category", category.getName());
		request.setAttribute("listCategory", listCategory);

		String listPage = "frontend/book_list_by_category.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);

	}

}
