package com.orangeandbronze.schoolreg.dao;

import java.io.FileReader;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Student;

public class StudentDaoTest extends DBTestCase{
	
	public StudentDaoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/school_registration"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileReader("src/test/StudentDaoTest.xml"));
	}
	
	
	
	@Test
	public void testGetStudentByStudentNumber(){
		
		StudentDao studentDao = new StudentDaoImpl();
		int studentNumber = 11;
		
		Student student = studentDao.getStudentByStudentNumber(studentNumber);
		
		assertEquals(1, student.getAcademicYear().intValue());
		assertEquals("HALF", student.getShorlarshipStatus().toString());
		
	}
	

}
