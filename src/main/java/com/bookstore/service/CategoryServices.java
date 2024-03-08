package com.bookstore.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;
import com.bookstore.exception.DatabaseException;

public class CategoryServices {

	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(Connection connection, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException, DatabaseException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException, DatabaseException {
		List<Category> listCategory = categoryDAO.list();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listCategory", listCategory);
		String listPage = "category_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException, DatabaseException {
		String categoryName = request.getParameter("name");

		Category categoryByName = categoryDAO.findByName(categoryName);

		if (Objects.isNull(categoryByName)) {
			Category newCategory = new Category(categoryName);
			categoryDAO.create(newCategory);
			listCategory("New Category created successfully");
		} else {
			String message = "Could not create Category. Category already exists with same name";
			request.setAttribute("message", message);
			String categoryForm = "category_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(categoryForm);
			dispatcher.forward(request, response);
		}
	}

	public void editCategory() throws ServletException, IOException, DatabaseException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = categoryDAO.get(categoryId);

		request.setAttribute("category", category);
		String editPage = "category_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException, DatabaseException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");

		Category category = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);

		if (Objects.isNull(categoryByName)) {
			Category updateCategory = new Category(categoryId, categoryName);
			categoryDAO.update(updateCategory);
			String message = "Category has been updated successfully";
			listCategory(message);
		} else if (categoryByName != null && categoryByName.getCategoryId() != category.getCategoryId()) {
			String message = "Could not update Category.";
			request.setAttribute("message", message);
			request.setAttribute("category", category);
			String userForm = "category_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(userForm);
			dispatcher.forward(request, response);
		}
	}

	public void deleteCategory() throws ServletException, IOException, DatabaseException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));

		Category category = categoryDAO.get(categoryId);
		if (Objects.isNull(category)) {
			String message = "Category does not exist";
			listCategory(message);
		} else if (!Objects.isNull(category.getBooks()) && category.getBooks().size() > 0) {
			String message = "Category is associated with Books. Category can not be deleted";
			listCategory(message);
		} else {
			categoryDAO.delete(categoryId);
			String message = "Category has been deleted successfully";
			listCategory(message);
		}
	}

}
