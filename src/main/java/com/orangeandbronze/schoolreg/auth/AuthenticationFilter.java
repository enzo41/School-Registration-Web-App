package com.orangeandbronze.schoolreg.auth;

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
		
		// if no user was found in session
		if (user == null || user.isInvalid()) {
			
			// check if there's a userId in the request, if so, get a new User
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
		chain.doFilter(request, response); // go to the original request
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
