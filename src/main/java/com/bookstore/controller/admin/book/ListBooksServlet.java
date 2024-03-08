package com.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.exception.DatabaseException;
import com.bookstore.service.BookService;

/**
 * Servlet implementation class ListBooksServlet
 */
@WebServlet("/admin/list_books")
public class ListBooksServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bookService = new BookService(databaseConnection, request, response);
		try {
			bookService.listBooks();
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
