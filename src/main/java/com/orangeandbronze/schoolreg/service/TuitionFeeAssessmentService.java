package com.orangeandbronze.schoolreg.service;

import java.math.BigDecimal;

import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDao;
import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDaoImpl;
import com.orangeandbronze.schoolreg.dao.StudentDao;
import com.orangeandbronze.schoolreg.dao.StudentDaoImpl;

public class TuitionFeeAssessmentService {
	private final int UNIT_PER_SUBJECT = 3;
	private final int FRESHMEN_MINIMUM_UNITS = 15;
	private final int SOPHOMORE_MINIMUM_UNITS = 18;
	private final int JUNIOR_MINIMUM_UNITS = 18;
	private final int SENIOR_MINIMUM_UNITS = 0;
	
	EnrollmentSectionDao enrollmentSectionDao = new EnrollmentSectionDaoImpl();
	StudentDao studentDao = new StudentDaoImpl();
	
	public Integer getTotalEnlistedUnitsOfCurrentTerm(){
		
		Integer numberOfEnlistedSection = enrollmentSectionDao.countEnlistedSection();
		Integer totalEnlistedUnits = numberOfEnlistedSection * UNIT_PER_SUBJECT;
		
//		int academic_year = 
	
		return 0;
	}
	
	public BigDecimal calculateTuitionFeeOfCurrenctTerm(){
		
		return new BigDecimal("0");
	}
	
}
