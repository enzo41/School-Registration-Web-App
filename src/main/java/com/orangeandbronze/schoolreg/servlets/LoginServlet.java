package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orangeandbronze.schoolreg.service.LoginService;
import com.orangeandbronze.schoolreg.users.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginService service = new LoginService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		User user = service.getUser(userId);		// We'll allow the User class to leak into the presentation layer since authentication should be infrastructure and not domain. The alternative would be to use Collections or Maps of Strings, which would be error-prone.
		if (user.getType() == User.Type.INVALID) {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("user", user);
		}
	}

}
