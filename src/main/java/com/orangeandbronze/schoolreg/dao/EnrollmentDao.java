package com.orangeandbronze.schoolreg.dao;

import com.orangeandbronze.schoolreg.domain.Enrollment;
import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.domain.Term;

public class EnrollmentDao {

	/** Should return a new Enrollment if none exists, or fetch an existing one. **/
	public Enrollment getBy(Student student, Term term) {
		return new Enrollment(777, student, term); // TODO Just a stub; implement actual as JDBC
	}

}
