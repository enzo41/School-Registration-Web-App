package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orangeandbronze.schoolreg.auth.User;
import com.orangeandbronze.schoolreg.service.TuitionFeeAssessmentService;

/**
 * Servlet implementation class TuitionFeeAssessmentServlet
 */
@WebServlet("/TuitionFeeAssessment")
public class TuitionFeeAssessmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		
		//Check the number of unit if it is greater than minimum load or not
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		boolean isMoreThanMinimumLoad = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(user.getUserId());
		
		if(isMoreThanMinimumLoad){
			//Caluculate tuition fee
			BigDecimal tuitionFee = tuitionFeeAssessmentService.calculateTuitionFeeOfCurrenctTerm();
		} else{
			
		}
		//Redirect to jsp
		response.sendRedirect("/school-registration-web-app/tuitionFeeAssessment.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
