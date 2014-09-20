package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orangeandbronze.schoolreg.service.LoginService;
import com.orangeandbronze.schoolreg.users.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private LoginService service = new LoginService();

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {		
		
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpSession session = hreq.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || user.isInvalid()) {
			
			String userId = request.getParameter("userId");
			if (userId == null || userId.trim().equals("")) {
				// Go back to login page.
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			} else {
				// instatiate a User and save to session
				session.setAttribute("user", service.getUser(Integer.parseInt(userId)));
				
			}
			
			
			
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
