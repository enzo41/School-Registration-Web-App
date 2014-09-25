package com.orangeandbronze.schoolreg.dao;

import java.io.FileReader;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Subject;

public class SubjectDaoTest extends DBTestCase{
	
	public SubjectDaoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/school_registration"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileReader("src/test/SubjectDaoTest.xml"));
	}
	
	
	@Test
	public void testFetchAllSubject(){
		
		SubjectDao subjectDao = new SubjectDaoImpl();
		List<Subject> subjectList = subjectDao.fetchAllSubject();
		
		assertEquals(14, subjectList.size());
	}
	
	@Test
	public void testGetPkBySubjectId(){
		
		SubjectDao subjectDao = new SubjectDaoImpl();
		int pk = subjectDao.getPkBySubjectId("EN2");
		
		assertEquals(12, pk);
	}

}
