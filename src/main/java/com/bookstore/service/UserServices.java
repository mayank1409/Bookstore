package com.bookstore.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(Connection connection, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
	}

	public void listUsers() throws ServletException, IOException {
		listUsers(null);
	}

	public void listUsers(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.list();

		request.setAttribute("listUsers", listUsers);

		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "user_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");

		password = Base64.getEncoder().encodeToString(password.getBytes());

		Users userByEmail = userDAO.findByEmail(email);
		if (!Objects.isNull(userByEmail)) {
			String message = "Could not create user. User already exists with same email";
			request.setAttribute("message", message);
			String listPage = "user_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
			dispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, password, fullName);
			userDAO.create(newUser);
			listUsers("New User Created Successully");
		}
	}

	public void editUser() throws ServletException, IOException {
		String userIdFromRequest = request.getParameter("userId");
		Integer userId = Integer.parseInt(userIdFromRequest);
		Users user = userDAO.get(userId);

		request.setAttribute("user", user);
		String editPage = "user_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");

		Users user = userDAO.get(userId);
		Users userByEmail = userDAO.findByEmail(email);

		if (userByEmail != null && userByEmail.getUserId() != user.getUserId()) {
			String message = "Could not update user.";
			request.setAttribute("message", message);
			request.setAttribute("user", user);
			String userForm = "user_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(userForm);
			dispatcher.forward(request, response);
		} else {

			Users updateUser = new Users(userId, email, password, fullName);
			userDAO.update(updateUser);

			String message = "User has been updated successfully";
			listUsers(message);
		}
	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		userDAO.delete(userId);
		String message = "User has been deleted successfully";
		listUsers(message);
	}

	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		password = Base64.getEncoder().encodeToString(password.getBytes());
		Users user = userDAO.checkLogin(email, password);
		if (user != null) {
			request.getSession().setAttribute("email", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		} else {
			String message = "Login failed. Incorrect email or password";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}
}
