package com.orangeandbronze.schoolreg.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Faculty;

public class FacultyDaoTest {
	
	@Test
	public void testFetchAllFaculty(){
		
		FacultyDao facultyDao = new FacultyDaoImpl();
		List<Faculty> facultyList = facultyDao.fetchAllFaculty();
		
		assertEquals(5, facultyList.size());
	}

}