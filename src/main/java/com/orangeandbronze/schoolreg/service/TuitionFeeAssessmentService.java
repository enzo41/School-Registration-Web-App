package com.orangeandbronze.schoolreg.service;

import java.math.BigDecimal;

import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDao;
import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDaoImpl;
import com.orangeandbronze.schoolreg.dao.StudentDao;
import com.orangeandbronze.schoolreg.dao.StudentDaoImpl;
import com.orangeandbronze.schoolreg.domain.Student;

public class TuitionFeeAssessmentService {
	private final int UNIT_PER_SUBJECT = 3;
	private final int FRESHMEN_MINIMUM_UNITS = 15;
	private final int SOPHOMORE_MINIMUM_UNITS = 18;
	private final int JUNIOR_MINIMUM_UNITS = 18;
	private final int SENIOR_MINIMUM_UNITS = 0;
	
	EnrollmentSectionDao enrollmentSectionDao = new EnrollmentSectionDaoImpl();
	StudentDao studentDao = new StudentDaoImpl();
	
	public boolean checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(int studentNumber){
		
		Integer numberOfEnlistedSection = enrollmentSectionDao.countEnlistedSection();
		Integer totalEnlistedUnits = numberOfEnlistedSection * UNIT_PER_SUBJECT;
		
		Integer academicYear = studentDao.getAcdemicYearByStudentNumber(studentNumber);
		boolean isGreaterThanMinimum = false;
		
		switch (academicYear) {
        case 1:  isGreaterThanMinimum = (totalEnlistedUnits > FRESHMEN_MINIMUM_UNITS);
                 break;
        case 2:  isGreaterThanMinimum = (totalEnlistedUnits > SOPHOMORE_MINIMUM_UNITS);
                 break;
        case 3:  isGreaterThanMinimum = (totalEnlistedUnits > JUNIOR_MINIMUM_UNITS);
                 break;
        case 4:  isGreaterThanMinimum = (totalEnlistedUnits > SENIOR_MINIMUM_UNITS);
                 break;
		}
		
		return isGreaterThanMinimum;
	}
	
	
	public BigDecimal calculateTuitionFeeOfCurrenctTerm(){
		
		return new BigDecimal("0");
	}
	
}
