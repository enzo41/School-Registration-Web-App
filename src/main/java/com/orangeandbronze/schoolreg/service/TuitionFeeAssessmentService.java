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
import com.orangeandbronze.schoolreg.domain.TuitionFeeAssessment;

public class TuitionFeeAssessmentService {
	private final int UNIT_PER_SUBJECT = 3;
	private final int FRESHMEN_MINIMUM_UNITS = 15;
	private final int SOPHOMORE_MINIMUM_UNITS = 18;
	private final int JUNIOR_MINIMUM_UNITS = 18;
	private final int SENIOR_MINIMUM_UNITS = 0;
	private final int UNDERGRADUATE_SUBJECT_FEE_PER_SECTION = 2000;
	private final int GRADUATE_SUBJECT_FEE_PER_SECTION = 4000;
	private final int MISCELLANEOUS_FEES = 2000;
	private final int PERCENTAGE_OF_HALF_SCHOLARSHIP_COVERAGE = 50;
	
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
	
	
	public TuitionFeeAssessment createTuitionFeeAssessmentOfCurrenctTerm(int studentNumber){

		//Fetch Enlisted Sections of current term
		List<Section> sectionListOfCurrenctTerm = sectionDao.fetchEnrollmentSectionOfCurrenctTerm(studentNumber, Term.getCurrent());
		
		//Count the number of enlisted undergraduate subject graduate subject
		int numberOfEnlistedUndergraduateSubject = 0;
		int numberOfEnlistedGraduateSubject = 0;
		
		for(Section section: sectionListOfCurrenctTerm){
			
			switch (section.getSubject().getSubjectType()){
			  case UNDERGRADUATE:
				  numberOfEnlistedUndergraduateSubject++;
				  break;
			  case GRADUATE:
				  numberOfEnlistedGraduateSubject++;
			}
		}
		
		int totalUndergraduateSubjctFee = numberOfEnlistedUndergraduateSubject * UNDERGRADUATE_SUBJECT_FEE_PER_SECTION;
		int totalGraduateSubjectFee = numberOfEnlistedGraduateSubject * GRADUATE_SUBJECT_FEE_PER_SECTION;
		
		//Check scholarship status
		Student student = studentDao.getStudentByStudentNumber(studentNumber);
		
		//Calculate tuition fee
		int totalSubjectTuitionFee = 0;
		switch(student.getShorlarshipStatus()){
		  case NONE:
			  totalSubjectTuitionFee = totalUndergraduateSubjctFee + totalGraduateSubjectFee;
		  case HALF:
			  totalSubjectTuitionFee = (totalUndergraduateSubjctFee + totalGraduateSubjectFee)	* PERCENTAGE_OF_HALF_SCHOLARSHIP_COVERAGE / 100;
		  case FULL:
			  totalSubjectTuitionFee = 0;
		}
		
		TuitionFeeAssessment tuitionFeeAssessment = new TuitionFeeAssessment();
		tuitionFeeAssessment.setStudent(student);
		tuitionFeeAssessment.setNumberOfEnlistedUndergraduateSubject(numberOfEnlistedUndergraduateSubject);
		tuitionFeeAssessment.setTotalUndergraduateSubjctFee(totalUndergraduateSubjctFee);
		tuitionFeeAssessment.setNumberOfEnlistedGraduateSubject(numberOfEnlistedGraduateSubject);
		tuitionFeeAssessment.setTotalGraduateSubjectFee(totalGraduateSubjectFee);
		tuitionFeeAssessment.setTotalSubjectTuitionFee(totalSubjectTuitionFee);
		tuitionFeeAssessment.setMiscellaneousFee(MISCELLANEOUS_FEES);
		tuitionFeeAssessment.setTotalTuitionFee(totalSubjectTuitionFee + MISCELLANEOUS_FEES);
		tuitionFeeAssessment.setSectionList(sectionListOfCurrenctTerm);
		
		return tuitionFeeAssessment;
	}
	
}
