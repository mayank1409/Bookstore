package com.bookstore.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bookstore.util.BookstoreDBUtils;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static Connection databaseConnection;

	@Override
	public void init() throws ServletException {
		databaseConnection = BookstoreDBUtils.getDatabaseConnection();
	}

	@Override
	public void destroy() {
		if (databaseConnection != null) {
			try {
				databaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
