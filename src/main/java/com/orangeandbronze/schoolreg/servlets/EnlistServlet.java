package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orangeandbronze.schoolreg.service.EnlistmentService;

/**
 * Servlet implementation class EnlistServlet
 */
@WebServlet("/enlist")
public class EnlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnlistmentService service = new EnlistmentService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] sectionNumbers = request.getParameterValues("sectionNumber");

		// placeholder code
		response.setContentType("text/html");
		PrintWriter w = response.getWriter();
		w.println("<p>You have chosen to enlist in the following section #s:");
		w.println("<ul>");
		for (String num : sectionNumbers) {
			w.println("<li>" + num);
		}
		w.println("</ul>");
		w.println("<p>Now get to work and finish this machine problem!");
		w.flush();
	}

}
