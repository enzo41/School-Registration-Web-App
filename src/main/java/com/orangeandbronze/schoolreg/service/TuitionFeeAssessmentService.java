package com.orangeandbronze.schoolreg.service;

import java.math.BigDecimal;
import java.util.List;

import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDao;
import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDaoImpl;
import com.orangeandbronze.schoolreg.dao.SectionDao;
import com.orangeandbronze.schoolreg.dao.StudentDao;
import com.orangeandbronze.schoolreg.dao.StudentDaoImpl;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.domain.Term;

public class TuitionFeeAssessmentService {
	private final int UNIT_PER_SUBJECT = 3;
	private final int FRESHMEN_MINIMUM_UNITS = 15;
	private final int SOPHOMORE_MINIMUM_UNITS = 18;
	private final int JUNIOR_MINIMUM_UNITS = 18;
	private final int SENIOR_MINIMUM_UNITS = 0;
	private final int MISCELLANEOUS_FEES = 2000;
	
	EnrollmentSectionDao enrollmentSectionDao = new EnrollmentSectionDaoImpl();
	StudentDao studentDao = new StudentDaoImpl();
	SectionDao sectionDao = new SectionDao();
	
	public boolean checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(int studentNumber){
		
		Integer numberOfEnlistedSection = enrollmentSectionDao.countEnlistedSection(studentNumber, Term.getCurrent());
		Integer totalEnlistedUnits = numberOfEnlistedSection * UNIT_PER_SUBJECT;
		
		Student student = studentDao.getStudentByStudentNumber(studentNumber);
		boolean isGreaterEqualMinimum = false;
		
		switch (student.getAcademicYear()) {
        case 1:  isGreaterEqualMinimum = (totalEnlistedUnits >= FRESHMEN_MINIMUM_UNITS);
                 break;
        case 2:  isGreaterEqualMinimum = (totalEnlistedUnits >= SOPHOMORE_MINIMUM_UNITS);
                 break;
        case 3:  isGreaterEqualMinimum = (totalEnlistedUnits >= JUNIOR_MINIMUM_UNITS);
                 break;
        case 4:  isGreaterEqualMinimum = (totalEnlistedUnits >= SENIOR_MINIMUM_UNITS);
                 break;
		}
		
		return isGreaterEqualMinimum;
	}
	
	
	public BigDecimal calculateTuitionFeeOfCurrenctTerm(int studentNumber){

		//Fetch Enlisted Sections of current term
		List<Section> sectionListOfCurrenctTerm = sectionDao.fetchEnrollmentSectionOfCurrenctTerm();
		
		
		//Count the number of enlisted undergraduate subject
		
		//Count the number of enlisted graduate subject
		
		//Check scholarship status
		
		//Calculate tuition fee
		
		return new BigDecimal("0");
	}
	
}
