package com.orangeandbronze.schoolreg.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Subject;

public class SubjectDaoTest {
	
	@Test
	public void testFetchAllSubject(){
		
		SubjectDao subjectDao = new SubjectDaoImpl();
		List<Subject> subjectList = subjectDao.fetchAllSubject();
		
		assertEquals(12, subjectList.size());
	}
	
	@Test
	public void testGetPkBySubjectId(){
		
		SubjectDao subjectDao = new SubjectDaoImpl();
		int pk = subjectDao.getPkBySubjectId("EN2");
		
		assertEquals(12, pk);
	}

}
