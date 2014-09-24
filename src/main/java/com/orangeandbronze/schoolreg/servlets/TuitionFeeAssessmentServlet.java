package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//Check the number of unit if it is over minimum load or not
		Integer totalUnit = tuitionFeeAssessmentService.getTotalEnlistedUnitsOfCurrentTerm();
		
		//Caluculate tuition fee
		BigDecimal tuitionFee = tuitionFeeAssessmentService.calculateTuitionFeeOfCurrenctTerm();
		
		//Redirect to jsp
		response.sendRedirect("/school-registration-web-app//tuitionFeeAssessment.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
