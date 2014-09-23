package com.orangeandbronze.schoolreg.dao;

import java.util.List;

import com.orangeandbronze.schoolreg.domain.Student;

/*public interface StudentDaoS {
	
	List<Student> getAllStudents();
	Student getById();

}*/

public class StudentDao extends Dao {
	
	public Student getById(int studentId) {
		return new Student(studentId); // TODO Just a stub; implement actual as JDBC
	}

}