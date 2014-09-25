package com.orangeandbronze.schoolreg.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Term;

public class EnrollmentSectionDaoTest {
	
	@Test
	public void testCountEnlistedSection(){
		
		EnrollmentSectionDao enrollmentSection = new EnrollmentSectionDaoImpl();
		int studentNumber = 11;
		Term currentTerm = Term.getCurrent();
		
		Integer count = enrollmentSection.countEnlistedSection(studentNumber, currentTerm);
		
		assertEquals(7, count.intValue());
		
	}

}
