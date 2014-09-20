package com.orangeandbronze.schoolreg.domain;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class StudentTest {

	@Test
	public void getPreviousEnrollments() {
		Student student = new Student(123);
		Calendar cal = Calendar.getInstance();
		cal.set(2012, Calendar.AUGUST, 15);
		Date start1 = cal.getTime();
		cal.set(2012, Calendar.DECEMBER, 15);
		Date end1 = cal.getTime();
		Enrollment enrollment1 = new Enrollment(1, student, new Term(start1, end1));
		cal.set(2013, Calendar.JANUARY, 15);
		Date start2 = cal.getTime();
		cal.set(2013, Calendar.MAY, 15);
		Date end2 = cal.getTime();
		Enrollment enrollment2 = new Enrollment(2, student, new Term(start2, end2));
		cal.set(2013, Calendar.AUGUST, 15);
		Date start3 = cal.getTime();
		cal.set(2013, Calendar.DECEMBER, 15);
		Date end3 = cal.getTime();
		Enrollment enrollment3 = new Enrollment(3, student, new Term(start3, end3));
		cal.set(2013, Calendar.JANUARY, 15);
		Date start4 = cal.getTime();
		cal.set(2013, Calendar.MAY, 15);
		Date end4 = cal.getTime();
		Enrollment enrollment4 = new Enrollment(4, student, new Term(start4, end4));
		
		Set<Enrollment> previous = student.getPreviousEnrollments(enrollment3);
		Set<Enrollment> expected = new HashSet<Enrollment>() {{ add(enrollment1); add(enrollment2); }};
		assertEquals(expected, previous);
	}

}
