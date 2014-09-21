package com.orangeandbronze.schoolreg.dao;

import java.util.HashSet;
import java.util.Set;

import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;

public class SectionDao {

	public Section getById(String sectionNumber) {
		// TODO Just a stub; implement actual as JDBC
		return new Section(sectionNumber, new Subject("xx PLACEHOLDER xx")); 
	}

	public Set<Section> getAll() {
		// TODO Just a stub; implement actual as JDBC
		Section aaa111 = new Section("AAA111", new Subject("MATH53"), new Schedule(Days.MTH, Period.AM10));
		Section bbb222 = new Section("BBB222", new Subject("COM1"));
		Section ccc333 = new Section("CCC333", new Subject("CS11"));
		Section ddd444 = new Section("DDD444", new Subject("PHILO1"), new Schedule(Days.TF, Period.PM4));
		Section eee555 = new Section("EEE555", new Subject("CS11")); 
		Section zzz000 = new Section("ZZZ000", new Subject("CHEM11"), new Schedule(Days.TF, Period.PM4));
		return new HashSet<Section>() {{
				add(aaa111); add(bbb222); add(ccc333); add(ddd444); add(eee555); add(zzz000);
		}};
	}

}
