package com.orangeandbronze.schoolreg.dao;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;

public class SectionDaoTest extends DBTestCase {

	public SectionDaoTest() {
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/school_registration"); 
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileReader("src/test/SectionDaoTest.xml"));
	}
	
	public void testGetAll() {
		Set<Section> expected = new HashSet<Section>() {
			{
				add(new Section("AAA111", new Subject("MATH53"), new Schedule(Days.MTH, Period.AM10)));
				add(new Section("BBB222", new Subject("COM1")));
				add(new Section("CCC333", new Subject("CS11")));
				add(new Section("DDD444", new Subject("PHILO1"), new Schedule(Days.TF, Period.PM4)));
				add(new Section("EEE555", new Subject("CS11")));
				add(new Section("GGG777", new Subject("MATH11")));
				add(new Section("ZZZ000", new Subject("CHEM11"), new Schedule(	Days.TF, Period.PM4)));
			}
		};
		SectionDao dao = new SectionDao();
		assertEquals(expected, dao.getAll());
	}
	
	public void testGetByIdSubjectNoPrerequisites() {
		SectionDao dao = new SectionDao();
		Section actual = dao.getById("BBB222");
		Section expected = new Section("BBB222", new Subject("COM1"));
		assertEquals(expected, actual);
		assertEquals(expected.getSubject(), actual.getSubject());
		assertEquals(expected.getInstructor(), actual.getInstructor());
		assertEquals(expected.getSchedule(), actual.getSchedule());
	}
	
	public void testGetByIdSubjectHasPrerequisites() {
		SectionDao dao = new SectionDao();
		Section actual = dao.getById("AAA111");
		final Subject math11 = new Subject("MATH11");
		final Subject math14 = new Subject("MATH14");
		final Set<Subject> expectedPrereq = new HashSet<Subject>() {{ add(math11); add(math14); }};
		Subject expectedSubject = new Subject("MATH53", expectedPrereq);
		Section expected = new Section("AAA111", expectedSubject, new Schedule(Days.MTH, Period.AM10));
		assertEquals(expected, actual);
		assertEquals(expected.getInstructor(), actual.getInstructor());
		assertEquals(expected.getSchedule(), actual.getSchedule());
		
		Subject actualSubject = actual.getSubject();
		assertEquals(expectedSubject, actualSubject);
		
		Set<Subject> actualPrereq = actualSubject.getPrerequisites();
		
		assertEquals(expectedPrereq, actualPrereq);
		
		
	}
	
	public void testFetchSectionByFacultyNumberAndScheduleResultNotNull(){
		SectionDao sectionDao = new SectionDao();
		int facultyNumber = 10;
		Schedule schedule = new Schedule(Days.MTH, Period.AM10);
		
		Section section = sectionDao.fetchSectionByFacultyNumberAndSchedule(facultyNumber, schedule);
		
		assertNotNull(section);
	}
	
	public void testFetchSectionByFacultyNumberAndScheduleResultNull(){
		SectionDao sectionDao = new SectionDao();
		int facultyNumber = 40;
		Schedule schedule = new Schedule(Days.MTH, Period.AM830);
		
		Section section = sectionDao.fetchSectionByFacultyNumberAndSchedule(facultyNumber, schedule);
		
		assertNull(section);
	}
	
	
	public void testGetMacPkExistRecordsInSections(){
		SectionDao sectionDao = new SectionDao();
		
		int maxPk = sectionDao.getMaxPk();
		
		assertEquals(12 , maxPk);
		
	}
	
	public void testCreateSection(){
		SectionDao sectionDao = new SectionDao();
		
		sectionDao.createSection(13, "EN1040WSAM", 11, 4, "WS AM1130");
		
		Section section = sectionDao.getById("EN1040WSAM");
		
		assertEquals("EN1" , section.getSubject().getSubjectId());
		assertEquals(new Integer("40") , section.getInstructor().getFacultyNumber());
		assertEquals("WS AM1130" , section.getSchedule().toString());

	}
	

}
