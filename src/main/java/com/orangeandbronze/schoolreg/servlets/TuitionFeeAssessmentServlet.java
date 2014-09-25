package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orangeandbronze.schoolreg.auth.User;
import com.orangeandbronze.schoolreg.domain.TuitionFeeAssessment;
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
		Integer studentNumber = user.getUserId();

		boolean isMoreThanMinimumLoad = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(studentNumber);
		session.removeAttribute("tuitionFeeAssessment");
		session.removeAttribute("tuitionFeeAssessmentError");
		
		if(isMoreThanMinimumLoad){
			//Create tuitionFeeAssessment
			TuitionFeeAssessment tuitionFeeAssessment = tuitionFeeAssessmentService.createTuitionFeeAssessmentOfCurrenctTerm(studentNumber);
			session.setAttribute("tuitionFeeAssessment", tuitionFeeAssessment);
		} else{
			String tuitionFeeAssessmentError =	"Your enlisted units are less than minimum load.<br>" +
												"Please enlist sections first. The minimum load of tuition fee assessment is the following:<br>" +
												"Freshmen  : 15 units<br>" +
												"Sophomore : 18 unuts<br>" +
												"Junior    : 18 units";
			session.setAttribute("tuitionFeeAssessmentError", tuitionFeeAssessmentError);
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
