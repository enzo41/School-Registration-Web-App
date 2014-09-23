package com.orangeandbronze.schoolreg.service;

import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.dao.FacultyDao;
import com.orangeandbronze.schoolreg.dao.FacultyDaoImpl;
import com.orangeandbronze.schoolreg.dao.SubjectDao;
import com.orangeandbronze.schoolreg.dao.SubjectDaoImpl;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Subject;

public class SectionCreationService {
	
	FacultyDao facultyDao = new FacultyDaoImpl();
	SubjectDao subjectDao = new SubjectDaoImpl();
	
	public List<Faculty> fetchFacultyList(){
		List<Faculty> facultyList = facultyDao.fetchAllFaculty();
		return facultyList;
	};
	
	public List<Subject> fetchSubjectList(){
		List<Subject> subjectList = subjectDao.fetchAllSubject();
		return subjectList;
	};
	
	
	public boolean checkTeacherScheduleAvailability(){
		return true;
	}
	
	public void createSection(){
		//Implement insert statement later
	}

}