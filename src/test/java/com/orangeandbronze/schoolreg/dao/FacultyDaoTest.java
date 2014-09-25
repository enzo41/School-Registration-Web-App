package com.orangeandbronze.schoolreg.dao;

import java.io.FileReader;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Faculty;

public class FacultyDaoTest extends DBTestCase {
	
	public FacultyDaoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/school_registration"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileReader("src/test/FacultyDaoTest.xml"));
	}
	
	
	@Test
	public void testFetchAllFaculty(){
		
		FacultyDao facultyDao = new FacultyDaoImpl();
		List<Faculty> facultyList = facultyDao.fetchAllFaculty();
		
		assertEquals(5, facultyList.size());
	}
	
	@Test
	public void testGetPkByFacultyNumber(){
		
		FacultyDao facultyDao = new FacultyDaoImpl();
		int pk = facultyDao.getPkByFacultyNumber(50);
		
		assertEquals(5, pk);
	}

}