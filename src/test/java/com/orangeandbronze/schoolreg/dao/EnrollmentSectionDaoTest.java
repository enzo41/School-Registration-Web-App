package com.orangeandbronze.schoolreg.dao;

import java.io.FileReader;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Term;

public class EnrollmentSectionDaoTest extends DBTestCase {
	
	public EnrollmentSectionDaoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/school_registration"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileReader("src/test/EnrollmentSectionDaoTest.xml"));
	}
	
	
	@Test
	public void testCountEnlistedSection(){
		
		EnrollmentSectionDao enrollmentSection = new EnrollmentSectionDaoImpl();
		int studentNumber = 11;
		Term currentTerm = Term.getCurrent();
		
		Integer count = enrollmentSection.countEnlistedSection(studentNumber, currentTerm);
		
		assertEquals(7, count.intValue());
		
	}

}
