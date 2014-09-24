package com.orangeandbronze.schoolreg.dao;

import java.util.List;

import com.orangeandbronze.schoolreg.domain.Student;

public interface StudentDao {
	
	List<Student> getAllStudents();
	Student getById(int studentNumber);
	List<Integer> getStudentsInt();

}

/*
public class StudentDao extends Dao {
	
	public Student getById(int studentId) {
		return new Student(studentId); // TODO Just a stub; implement actual as JDBC
	}

}*/