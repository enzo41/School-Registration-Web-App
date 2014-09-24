package com.orangeandbronze.schoolreg.dao;

import java.util.List;

import com.orangeandbronze.schoolreg.domain.Faculty;

public interface FacultyDao {
	
	List<Faculty> fetchAllFaculty();
	Integer getPkByFacultyNumber(int facultyNumber);
	

}
