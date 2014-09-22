package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orangeandbronze.schoolreg.service.EnlistService;

@WebServlet("/showSections")
public class ShowSectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EnlistService service = new EnlistService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sections", service.getAllSections());
		// enlist.jsp is in WEB-INF so it cannot be accessed by users directly
		getServletContext().getRequestDispatcher("/WEB-INF/enlist.jsp").forward(request, response);
	}

}
