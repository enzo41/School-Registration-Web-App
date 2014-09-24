package com.orangeandbronze.schoolreg.dao;

import com.orangeandbronze.schoolreg.domain.Term;

public interface EnrollmentSectionDao {
	
	Integer countEnlistedSection(Integer studentNumber, Term currentTerm);

}
