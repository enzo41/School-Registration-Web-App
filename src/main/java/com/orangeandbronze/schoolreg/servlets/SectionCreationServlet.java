package com.orangeandbronze.schoolreg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;
import com.orangeandbronze.schoolreg.service.SectionCreationService;

/**
 * Servlet implementation class NewSectionCreation
 */
@WebServlet("/SectionCreation")
public class SectionCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SectionCreationService sectionCreationService = new SectionCreationService();
		
		List<Faculty> facultyList = sectionCreationService.fetchFacultyList();
		List<Subject> subjectList = sectionCreationService.fetchSubjectList(); 
		List<Days> daysList = sectionCreationService.fetchDaysList();
		List<Period> periodList = sectionCreationService.fetchPeriodList();
		
		HttpSession session = request.getSession();
		session.setAttribute("facultyList", facultyList);
		session.setAttribute("subjectList", subjectList);
		session.setAttribute("daysList", daysList);
		session.setAttribute("periodList", periodList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/sectionCreation.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sectionNumber = request.getParameter("sectionNumber");
		Integer facultyNumber = new Integer(request.getParameter("facultyNumber"));
		String subjectId = request.getParameter("subjectId");
		Days day = Enum.valueOf (Days.class, request.getParameter("day"));
		Period period = Enum.valueOf (Period.class, request.getParameter("period"));
		Schedule schedule = new Schedule(day, period);
		
		SectionCreationService sectionCreationService = new SectionCreationService();
		boolean isTeacherScheduleAvailable = sectionCreationService.checkTeacherScheduleAvailability(facultyNumber, schedule);
		HttpSession session = request.getSession();
		
		if(isTeacherScheduleAvailable){
			sectionCreationService.createSection(sectionNumber, facultyNumber, subjectId, schedule);

			List<Faculty> facultyList = (List<Faculty>) session.getAttribute("facultyList");
			for(Faculty faculty: facultyList){
				if(faculty.equals(new Integer(facultyNumber))){
					session.setAttribute("faculty", faculty);
					break;
				}
			}
		}else{
			//Teacher is unavailable with the specified schedule. Teacher is assigned to SECTION.
			
		}
		
		response.sendRedirect("/school-registration-web-app/sectionCreationResult.jsp");
		
	}

}
