package com.orangeandbronze.schoolreg.dao;

import com.orangeandbronze.schoolreg.domain.Student;

public class StudentDao {
	
	public Student getById(int studentId) {
		return new Student(studentId); // TODO Just a stub; implement actual as JDBC
	}

}
