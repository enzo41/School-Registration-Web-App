package com.orangeandbronze.schoolreg.dao;

import com.orangeandbronze.schoolreg.domain.Section;

public class SectionDao {

	public Section getById(String sectionNumber) {
		return new Section(sectionNumber, null); // TODO Just a stub; implement actual as JDBC
	}

}
