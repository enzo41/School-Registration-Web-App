package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orangeandbronze.schoolreg.auth.User;
import com.orangeandbronze.schoolreg.service.EnlistService;
import com.orangeandbronze.schoolreg.service.EnlistService.EnlistmentResult;

/**
 * Servlet implementation class EnlistServlet
 */
@WebServlet("/enlist")
public class EnlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnlistService service = new EnlistService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] sectionNumbers = request.getParameterValues("sectionNumber");
		HttpSession session = request.getSession();
		Integer studentNumber = ((User) session.getAttribute("user")).getUserId();		
		// send section numbers to service, return with list of successfully enlisted & not successfully enlisted sections... for not successfully enlisted, state why
		try{
		EnlistmentResult result = service.enlistSections(studentNumber, sectionNumbers);

		// send insert result in session, forward to a JSP page; implements Post-Redirect-Get pattern
		session.setAttribute("noSection", false);
		session.setAttribute("result", result);
		response.sendRedirect(getServletContext().getContextPath() + "/enlistmentResult.jsp");
		}
		// added try and catch for null data or no section selected to loop in showSections
		catch(NullPointerException e){
		session.setAttribute("noSection", true);
		session.setAttribute("sampleD", true);
		session.setAttribute("selection", sectionNumbers[0]);
		response.sendRedirect(getServletContext().getContextPath() + "/showSections");
		}
	}

}
