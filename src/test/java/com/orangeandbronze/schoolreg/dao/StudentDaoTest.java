package com.orangeandbronze.schoolreg.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Student;

public class StudentDaoTest {
	
	@Test
	public void testGetStudentByStudentNumber(){
		
		StudentDao studentDao = new StudentDaoImpl();
		int studentNumber = 11;
		
		Student student = studentDao.getStudentByStudentNumber(studentNumber);
		
		assertEquals(1, student.getAcademicYear().intValue());
		assertEquals("NONE", student.getShorlarshipStatus());
		
	}
	

}
