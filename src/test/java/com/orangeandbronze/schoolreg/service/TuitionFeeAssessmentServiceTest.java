package com.orangeandbronze.schoolreg.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDao;
import com.orangeandbronze.schoolreg.dao.SectionDao;
import com.orangeandbronze.schoolreg.dao.StudentDao;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.ShorlarshipStatus;
import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.domain.Subject;
import com.orangeandbronze.schoolreg.domain.SubjectType;
import com.orangeandbronze.schoolreg.domain.Term;
import com.orangeandbronze.schoolreg.domain.TuitionFeeAssessment;

public class TuitionFeeAssessmentServiceTest {
	
	
	@Test
	public void testCheckTotalEnlistedUnitsResultTrue(){
		
		//Prepare test data
		Student student1 = new Student(71, 1, ShorlarshipStatus.NONE);
		Student student2 = new Student(72, 2, ShorlarshipStatus.NONE);
		Student student3 = new Student(73, 3, ShorlarshipStatus.NONE);
		Student student4 = new Student(74, 4, ShorlarshipStatus.NONE);
				
		/* Mock the daos */
		EnrollmentSectionDao enrollmentSectionDao = mock(EnrollmentSectionDao.class);
		when(enrollmentSectionDao.countEnlistedSection(71, Term.getCurrent())).thenReturn(new Integer("20"));
		when(enrollmentSectionDao.countEnlistedSection(72, Term.getCurrent())).thenReturn(new Integer("20"));
		when(enrollmentSectionDao.countEnlistedSection(73, Term.getCurrent())).thenReturn(new Integer("20"));
		when(enrollmentSectionDao.countEnlistedSection(74, Term.getCurrent())).thenReturn(new Integer("20"));
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		when(studentDao.getStudentByStudentNumber(72)).thenReturn(student2);
		when(studentDao.getStudentByStudentNumber(73)).thenReturn(student3);
		when(studentDao.getStudentByStudentNumber(74)).thenReturn(student4);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setEnrollmentSectionDao(enrollmentSectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		boolean checkResult1 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student1.getStudentNumber());
		boolean checkResult2 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student2.getStudentNumber());
		boolean checkResult3 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student3.getStudentNumber());
		boolean checkResult4 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student4.getStudentNumber());
		
		assertEquals(true, checkResult1);
		assertEquals(true, checkResult2);
		assertEquals(true, checkResult3);
		assertEquals(true, checkResult4);
		
	}
	
	@Test
	public void testCheckTotalEnlistedUnitsResultFalse(){
		
		//Prepare test data
		Student student1 = new Student(71, 1, ShorlarshipStatus.NONE);
		Student student2 = new Student(72, 2, ShorlarshipStatus.NONE);
		Student student3 = new Student(73, 3, ShorlarshipStatus.NONE);
		Student student4 = new Student(74, 4, ShorlarshipStatus.NONE);
				
		/* Mock the daos */
		EnrollmentSectionDao enrollmentSectionDao = mock(EnrollmentSectionDao.class);
		when(enrollmentSectionDao.countEnlistedSection(71, Term.getCurrent())).thenReturn(new Integer("1"));
		when(enrollmentSectionDao.countEnlistedSection(72, Term.getCurrent())).thenReturn(new Integer("1"));
		when(enrollmentSectionDao.countEnlistedSection(73, Term.getCurrent())).thenReturn(new Integer("1"));
		when(enrollmentSectionDao.countEnlistedSection(74, Term.getCurrent())).thenReturn(new Integer("0"));
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		when(studentDao.getStudentByStudentNumber(72)).thenReturn(student2);
		when(studentDao.getStudentByStudentNumber(73)).thenReturn(student3);
		when(studentDao.getStudentByStudentNumber(74)).thenReturn(student4);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setEnrollmentSectionDao(enrollmentSectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		boolean checkResult1 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student1.getStudentNumber());
		boolean checkResult2 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student2.getStudentNumber());
		boolean checkResult3 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student3.getStudentNumber());
		boolean checkResult4 = tuitionFeeAssessmentService.checkTotalEnlistedUnitsOfCurrentTermMoreThanMinimumLoad(student4.getStudentNumber());
		
		assertEquals(false, checkResult1);
		assertEquals(false, checkResult2);
		assertEquals(false, checkResult3);
		assertEquals(true, checkResult4);
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentOnlyUndergraduateSubjectNoneScholarship(){
		
		//Prepare test data
		String sectionNumber1 = "MA1UNDER";
		String sectionNumber2 = "MA2UNDER";
		String sectionNumber3 = "MA3UNDER";
		String sectionNumber4 = "PH1UNDER";
		String sectionNumber5 = "PH2UNDER";
		String sectionNumber6 = "CH1UNDER";
		String sectionNumber7 = "CH2UNDER";
		
		
		Subject subject1 = new Subject("MA1", SubjectType.UNDERGRADUATE);
		Subject subject2 = new Subject("MA2", SubjectType.UNDERGRADUATE);
		Subject subject3 = new Subject("MA3", SubjectType.UNDERGRADUATE);
		Subject subject4 = new Subject("PH1", SubjectType.UNDERGRADUATE);
		Subject subject5 = new Subject("PH2", SubjectType.UNDERGRADUATE);
		Subject subject6 = new Subject("CH1", SubjectType.UNDERGRADUATE);
		Subject subject7 = new Subject("CH2", SubjectType.UNDERGRADUATE);
		
		List<Section> sectionList = new ArrayList<>();
		sectionList.add(new Section(sectionNumber1, subject1));
		sectionList.add(new Section(sectionNumber2, subject2));
		sectionList.add(new Section(sectionNumber3, subject3));
		sectionList.add(new Section(sectionNumber4, subject4));
		sectionList.add(new Section(sectionNumber5, subject5));
		sectionList.add(new Section(sectionNumber6, subject6));
		sectionList.add(new Section(sectionNumber7, subject7));
		
		Student student1 = new Student(71, 1, ShorlarshipStatus.NONE);

		/* Mock the daos */
		SectionDao sectionDao = mock(SectionDao.class);
		when(sectionDao.fetchEnrollmentSectionOfCurrenctTerm(student1.getStudentNumber(), Term.getCurrent())).thenReturn(sectionList);
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setSectionDao(sectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		TuitionFeeAssessment tuitionFeeAssessment = tuitionFeeAssessmentService.createTuitionFeeAssessmentOfCurrenctTerm(student1.getStudentNumber());
		
		assertEquals(16000, tuitionFeeAssessment.getTotalTuitionFee());

		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentOnlyGraduateSubjectNoneScholarship(){
		
		//Prepare test data
		String sectionNumber1 = "MA1UNDER";
		String sectionNumber2 = "MA2UNDER";
		String sectionNumber3 = "MA3UNDER";
		String sectionNumber4 = "PH1UNDER";
		String sectionNumber5 = "PH2UNDER";
		String sectionNumber6 = "CH1UNDER";
		String sectionNumber7 = "CH2UNDER";
		
		
		Subject subject1 = new Subject("MA1", SubjectType.GRADUATE);
		Subject subject2 = new Subject("MA2", SubjectType.GRADUATE);
		Subject subject3 = new Subject("MA3", SubjectType.GRADUATE);
		Subject subject4 = new Subject("PH1", SubjectType.GRADUATE);
		Subject subject5 = new Subject("PH2", SubjectType.GRADUATE);
		Subject subject6 = new Subject("CH1", SubjectType.GRADUATE);
		Subject subject7 = new Subject("CH2", SubjectType.GRADUATE);
		
		List<Section> sectionList = new ArrayList<>();
		sectionList.add(new Section(sectionNumber1, subject1));
		sectionList.add(new Section(sectionNumber2, subject2));
		sectionList.add(new Section(sectionNumber3, subject3));
		sectionList.add(new Section(sectionNumber4, subject4));
		sectionList.add(new Section(sectionNumber5, subject5));
		sectionList.add(new Section(sectionNumber6, subject6));
		sectionList.add(new Section(sectionNumber7, subject7));
		
		Student student1 = new Student(71, 1, ShorlarshipStatus.NONE);

		/* Mock the daos */
		SectionDao sectionDao = mock(SectionDao.class);
		when(sectionDao.fetchEnrollmentSectionOfCurrenctTerm(student1.getStudentNumber(), Term.getCurrent())).thenReturn(sectionList);
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setSectionDao(sectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		TuitionFeeAssessment tuitionFeeAssessment = tuitionFeeAssessmentService.createTuitionFeeAssessmentOfCurrenctTerm(student1.getStudentNumber());
		
		assertEquals(30000, tuitionFeeAssessment.getTotalTuitionFee());

	}
	
	@Test
	public void testCreateTuitionFeeAssessmentUnderGraduateAndGraduateSubjectNoneScholarship(){
		
		//Prepare test data
		String sectionNumber1 = "MA1UNDER";
		String sectionNumber2 = "MA2UNDER";
		String sectionNumber3 = "MA3UNDER";
		String sectionNumber4 = "PH1UNDER";
		String sectionNumber5 = "PH2UNDER";
		String sectionNumber6 = "CH1UNDER";
		String sectionNumber7 = "CH2UNDER";
		
		
		Subject subject1 = new Subject("MA1", SubjectType.UNDERGRADUATE);
		Subject subject2 = new Subject("MA2", SubjectType.UNDERGRADUATE);
		Subject subject3 = new Subject("MA3", SubjectType.UNDERGRADUATE);
		Subject subject4 = new Subject("PH1", SubjectType.GRADUATE);
		Subject subject5 = new Subject("PH2", SubjectType.GRADUATE);
		Subject subject6 = new Subject("CH1", SubjectType.GRADUATE);
		Subject subject7 = new Subject("CH2", SubjectType.GRADUATE);
		
		List<Section> sectionList = new ArrayList<>();
		sectionList.add(new Section(sectionNumber1, subject1));
		sectionList.add(new Section(sectionNumber2, subject2));
		sectionList.add(new Section(sectionNumber3, subject3));
		sectionList.add(new Section(sectionNumber4, subject4));
		sectionList.add(new Section(sectionNumber5, subject5));
		sectionList.add(new Section(sectionNumber6, subject6));
		sectionList.add(new Section(sectionNumber7, subject7));
		
		Student student1 = new Student(71, 1, ShorlarshipStatus.NONE);

		/* Mock the daos */
		SectionDao sectionDao = mock(SectionDao.class);
		when(sectionDao.fetchEnrollmentSectionOfCurrenctTerm(student1.getStudentNumber(), Term.getCurrent())).thenReturn(sectionList);
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setSectionDao(sectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		TuitionFeeAssessment tuitionFeeAssessment = tuitionFeeAssessmentService.createTuitionFeeAssessmentOfCurrenctTerm(student1.getStudentNumber());
		
		assertEquals(24000, tuitionFeeAssessment.getTotalTuitionFee());
		
		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentUnderGraduateAndGraduateSubjectHalfScholarship(){
		
		//Prepare test data
		String sectionNumber1 = "MA1UNDER";
		String sectionNumber2 = "MA2UNDER";
		String sectionNumber3 = "MA3UNDER";
		String sectionNumber4 = "PH1UNDER";
		String sectionNumber5 = "PH2UNDER";
		String sectionNumber6 = "CH1UNDER";
		String sectionNumber7 = "CH2UNDER";
		
		
		Subject subject1 = new Subject("MA1", SubjectType.UNDERGRADUATE);
		Subject subject2 = new Subject("MA2", SubjectType.UNDERGRADUATE);
		Subject subject3 = new Subject("MA3", SubjectType.UNDERGRADUATE);
		Subject subject4 = new Subject("PH1", SubjectType.GRADUATE);
		Subject subject5 = new Subject("PH2", SubjectType.GRADUATE);
		Subject subject6 = new Subject("CH1", SubjectType.GRADUATE);
		Subject subject7 = new Subject("CH2", SubjectType.GRADUATE);
		
		List<Section> sectionList = new ArrayList<>();
		sectionList.add(new Section(sectionNumber1, subject1));
		sectionList.add(new Section(sectionNumber2, subject2));
		sectionList.add(new Section(sectionNumber3, subject3));
		sectionList.add(new Section(sectionNumber4, subject4));
		sectionList.add(new Section(sectionNumber5, subject5));
		sectionList.add(new Section(sectionNumber6, subject6));
		sectionList.add(new Section(sectionNumber7, subject7));
		
		Student student1 = new Student(71, 1, ShorlarshipStatus.HALF);

		/* Mock the daos */
		SectionDao sectionDao = mock(SectionDao.class);
		when(sectionDao.fetchEnrollmentSectionOfCurrenctTerm(student1.getStudentNumber(), Term.getCurrent())).thenReturn(sectionList);
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setSectionDao(sectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		TuitionFeeAssessment tuitionFeeAssessment = tuitionFeeAssessmentService.createTuitionFeeAssessmentOfCurrenctTerm(student1.getStudentNumber());
		
		assertEquals(13000, tuitionFeeAssessment.getTotalTuitionFee());
		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentUnderGraduateAndGraduateSubjectFullScholarship(){
		
		//Prepare test data
		String sectionNumber1 = "MA1UNDER";
		String sectionNumber2 = "MA2UNDER";
		String sectionNumber3 = "MA3UNDER";
		String sectionNumber4 = "PH1UNDER";
		String sectionNumber5 = "PH2UNDER";
		String sectionNumber6 = "CH1UNDER";
		String sectionNumber7 = "CH2UNDER";
		
		
		Subject subject1 = new Subject("MA1", SubjectType.UNDERGRADUATE);
		Subject subject2 = new Subject("MA2", SubjectType.UNDERGRADUATE);
		Subject subject3 = new Subject("MA3", SubjectType.UNDERGRADUATE);
		Subject subject4 = new Subject("PH1", SubjectType.GRADUATE);
		Subject subject5 = new Subject("PH2", SubjectType.GRADUATE);
		Subject subject6 = new Subject("CH1", SubjectType.GRADUATE);
		Subject subject7 = new Subject("CH2", SubjectType.GRADUATE);
		
		List<Section> sectionList = new ArrayList<>();
		sectionList.add(new Section(sectionNumber1, subject1));
		sectionList.add(new Section(sectionNumber2, subject2));
		sectionList.add(new Section(sectionNumber3, subject3));
		sectionList.add(new Section(sectionNumber4, subject4));
		sectionList.add(new Section(sectionNumber5, subject5));
		sectionList.add(new Section(sectionNumber6, subject6));
		sectionList.add(new Section(sectionNumber7, subject7));
		
		Student student1 = new Student(71, 1, ShorlarshipStatus.FULL);

		/* Mock the daos */
		SectionDao sectionDao = mock(SectionDao.class);
		when(sectionDao.fetchEnrollmentSectionOfCurrenctTerm(student1.getStudentNumber(), Term.getCurrent())).thenReturn(sectionList);
		
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getStudentByStudentNumber(71)).thenReturn(student1);
		
		TuitionFeeAssessmentService tuitionFeeAssessmentService = new TuitionFeeAssessmentService();
		tuitionFeeAssessmentService.setSectionDao(sectionDao);
		tuitionFeeAssessmentService.setStudentDao(studentDao);
		
		TuitionFeeAssessment tuitionFeeAssessment = tuitionFeeAssessmentService.createTuitionFeeAssessmentOfCurrenctTerm(student1.getStudentNumber());
		
		assertEquals(2000, tuitionFeeAssessment.getTotalTuitionFee());
		
	}

}
