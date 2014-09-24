package com.orangeandbronze.schoolreg.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Test;

import com.orangeandbronze.schoolreg.dao.*;
import com.orangeandbronze.schoolreg.domain.*;
import com.orangeandbronze.schoolreg.service.EnlistService.EnlistmentResult;

public class EnlistServiceTest {

	@Test
	public void enlistSections() {
		
		/* parameters */
		Integer studentNumber = 123;		
		String[] sectionNumbers = {"AAA111", "BBB222", "CCC333", "DDD444", "EEE555"};
		
		/* domain model */
		Student student = new Student(studentNumber);		
		final Section alreadyEnlisted = new Section("ZZZ000", new Subject("CHEM11"), new Schedule(Days.TF, Period.PM4));
		final Enrollment currentEnrollment = new Enrollment(143, student, Term.getCurrent(), new HashSet<Section>() {{ add(alreadyEnlisted); }});
		// three sections should pass
		final Section bbb222 = new Section(sectionNumbers[1], new Subject("COM1"));
		final Section ccc333 = new Section(sectionNumbers[2], new Subject("CS11"));
		final Section eee555 = new Section(sectionNumbers[4], new Subject("CS11"));
		final Set<Section> successfullyEnlisted = new HashSet<Section>() {{ add(bbb222); add(ccc333); add(eee555); }}; 
		
		// Map of failed enlistments
		final Map<Section, String> failedToEnlist = new HashMap<>();
		
		// one section should have schedule conflict
		final Section ddd444 = new Section(sectionNumbers[3], new Subject("PHILO1"), new Schedule(Days.TF, Period.PM4));
		failedToEnlist.put(ddd444, "Conflict with sections already enlisted.");
		
		// one section should have problems with prerequistes
		final Subject math11 = new Subject("MATH11");
		final Subject math14 = new Subject("MATH14");
		final Set<Subject> prerequisitesToMath53 = new HashSet<Subject>() {{ add(math11); add(math14); }};
		final Subject math53 = new Subject("MATH53", prerequisitesToMath53);
		final Section aaa111 = new Section(sectionNumbers[0], math53, new Schedule(Days.MTH, Period.AM10));
		// only enrolled previously in Math11 but not Math14
		final Set<Section> previousSections = new HashSet<Section>() {{
			add(new Section("GGG777", math11));
		}};
		final Enrollment previousEnrollment = new Enrollment(100, student, Term.Y2012_1ST, previousSections);
		failedToEnlist.put(aaa111, "Missing prerequisite/s.");
		
		/* Mock the daos */
		StudentDao studentDao = mock(StudentDao.class);
		when(studentDao.getById(studentNumber)).thenReturn(student);
		SectionDao sectionDao = mock(SectionDao.class);
		when(sectionDao.getById(sectionNumbers[0])).thenReturn(aaa111);
		when(sectionDao.getById(sectionNumbers[1])).thenReturn(bbb222);
		when(sectionDao.getById(sectionNumbers[2])).thenReturn(ccc333);
		when(sectionDao.getById(sectionNumbers[3])).thenReturn(ddd444);
		when(sectionDao.getById(sectionNumbers[4])).thenReturn(eee555);
		EnrollmentDao enrollmentDao = mock(EnrollmentDao.class);
		when(enrollmentDao.getBy(student, Term.getCurrent())).thenReturn(currentEnrollment);
		when(enrollmentDao.getBy(student, Term.Y2012_1ST)).thenReturn(previousEnrollment);
		
		EnlistService service = new EnlistService();
//		Temporary commentout in order to clear error
//		service.setStudentDao(studentDao);
		service.setSectionDao(sectionDao);
		service.setEnrollmentDao(enrollmentDao);
		
		/* Moment of Truth! */		
		EnlistmentResult result = service.enlistSections(studentNumber, sectionNumbers);		
		EnlistmentResult expected = new EnlistService.EnlistmentResult(successfullyEnlisted, failedToEnlist);
		assertEquals(expected, result);	// this test will break if exception messages change
	}

}
