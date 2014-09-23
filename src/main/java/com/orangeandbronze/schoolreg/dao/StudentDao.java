package com.orangeandbronze.schoolreg.dao;

import java.util.List;

import com.orangeandbronze.schoolreg.domain.Student;

public interface StudentDao {
	
	List<Student> getAllStudents();
	Student getStudentById();

}