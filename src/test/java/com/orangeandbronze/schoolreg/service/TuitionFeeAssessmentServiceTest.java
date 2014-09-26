package com.orangeandbronze.schoolreg.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.orangeandbronze.schoolreg.dao.EnrollmentSectionDao;
import com.orangeandbronze.schoolreg.dao.StudentDao;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.ShorlarshipStatus;
import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.domain.Term;

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
		List<Section> sectionList = new ArrayList<>();
		Section section1 = new ;
		
		
		
		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentOnlyGraduateSubjectNoneScholarship(){
		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentUnderGraduateAndGraduateSubjectNoneScholarship(){
		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentUnderGraduateAndGraduateSubjectHalfScholarship(){
		
	}
	
	@Test
	public void testCreateTuitionFeeAssessmentUnderGraduateAndGraduateSubjectFullScholarship(){
		
	}

}
