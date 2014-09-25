package com.orangeandbronze.schoolreg.service;

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
	
	EnrollmentSectionDao enrollmentSectionDao = new EnrollmentSectionDaoImpl();
	StudentDao studentDao = new StudentDaoImpl();
	SectionDao sectionDao = new SectionDao();
	TuitionFeeAssessment tuitionFeeAssessment = new TuitionFeeAssessment();
	
	public boolean checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(int studentNumber){
		
		Integer numberOfEnlistedSection = enrollmentSectionDao.countEnlistedSection(studentNumber, Term.getCurrent());
		Integer totalEnlistedUnits = numberOfEnlistedSection * tuitionFeeAssessment.UNIT_PER_SUBJECT;
		
		Student student = studentDao.getStudentByStudentNumber(studentNumber);
		boolean isGreaterEqualMinimum = false;
		
		switch (student.getAcademicYear()) {
        case 1:  isGreaterEqualMinimum = (totalEnlistedUnits >= tuitionFeeAssessment.FRESHMEN_MINIMUM_UNITS);
                 break;
        case 2:  isGreaterEqualMinimum = (totalEnlistedUnits >= tuitionFeeAssessment.SOPHOMORE_MINIMUM_UNITS);
                 break;
        case 3:  isGreaterEqualMinimum = (totalEnlistedUnits >= tuitionFeeAssessment.JUNIOR_MINIMUM_UNITS);
                 break;
        case 4:  isGreaterEqualMinimum = (totalEnlistedUnits >= tuitionFeeAssessment.SENIOR_MINIMUM_UNITS);
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
				  break;
			}
		}
		
		int totalUndergraduateSubjctFee = numberOfEnlistedUndergraduateSubject * tuitionFeeAssessment.UNDERGRADUATE_SUBJECT_FEE_PER_SECTION;
		int totalGraduateSubjectFee = numberOfEnlistedGraduateSubject * tuitionFeeAssessment.GRADUATE_SUBJECT_FEE_PER_SECTION;
		
		//Check scholarship status
		Student student = studentDao.getStudentByStudentNumber(studentNumber);
		
		//Calculate tuition fee
		int totalSubjectTuitionFee = 0;
		switch(student.getShorlarshipStatus()){
		  case NONE:
			  totalSubjectTuitionFee = totalUndergraduateSubjctFee + totalGraduateSubjectFee;
			  break;
		  case HALF:
			  totalSubjectTuitionFee = (totalUndergraduateSubjctFee + totalGraduateSubjectFee)	* tuitionFeeAssessment.PERCENTAGE_OF_HALF_SCHOLARSHIP_COVERAGE / 100;
			  break;
		  case FULL:
			  totalSubjectTuitionFee = 0;
			  break;
		}
		
		tuitionFeeAssessment.setStudent(student);
		tuitionFeeAssessment.setNumberOfEnlistedUndergraduateSubject(numberOfEnlistedUndergraduateSubject);
		tuitionFeeAssessment.setTotalUndergraduateSubjctFee(totalUndergraduateSubjctFee);
		tuitionFeeAssessment.setNumberOfEnlistedGraduateSubject(numberOfEnlistedGraduateSubject);
		tuitionFeeAssessment.setTotalGraduateSubjectFee(totalGraduateSubjectFee);
		tuitionFeeAssessment.setTotalSubjectTuitionFee(totalSubjectTuitionFee);
		tuitionFeeAssessment.setTotalTuitionFee(totalSubjectTuitionFee + tuitionFeeAssessment.MISCELLANEOUS_FEES);
		tuitionFeeAssessment.setSectionList(sectionListOfCurrenctTerm);
		
		return tuitionFeeAssessment;
	}
	
	public String getTuitionFeeAssessmentErrorMessage(){
		String errorMessage =	"Your enlisted units are less than minimum load.<br>" +
								"Please enlist sections first. The minimum load of tuition fee assessment is the following:<br>" +
								"Freshmen  : " + tuitionFeeAssessment.FRESHMEN_MINIMUM_UNITS + " units<br>" +
								"Sophomore : " + tuitionFeeAssessment.SOPHOMORE_MINIMUM_UNITS + " unuts<br>" +
								"Junior    : " + tuitionFeeAssessment.JUNIOR_MINIMUM_UNITS + " units";
		return errorMessage;
		
	}
	
}
