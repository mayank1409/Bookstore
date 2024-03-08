package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

	public AdminLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		boolean loggedIn = false;
		if (session != null && session.getAttribute("email") != null) {
			loggedIn = true;
		}

		String loginURI = httpRequest.getContextPath() + "/admin/login";
		boolean isLoginPageRequest = httpRequest.getRequestURI().endsWith("login.jsp");
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);

		if (loggedIn && (isLoginPageRequest || loginRequest)) {
			String homePage = "index.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
			dispatcher.forward(request, response);
		} else if (loggedIn || loginRequest) {
			chain.doFilter(request, response);
		} else {
			String loginPage = "login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
