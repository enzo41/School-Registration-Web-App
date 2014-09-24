package com.orangeandbronze.schoolreg.dao;

import java.util.List;

import com.orangeandbronze.schoolreg.domain.Student;

public interface StudentDao {
	
	List<Student> getAllStudents();
	Integer getPkById(int studentNumber);
	List<Integer> getStudentsInt();
	Integer getAcdemicYearByStudentNumber(int studentNumber);

}