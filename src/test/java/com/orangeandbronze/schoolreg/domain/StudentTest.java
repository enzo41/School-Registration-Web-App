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
		Enrollment enrollment1 = new Enrollment(1, student, Term.Y2012_1ST);
		Enrollment enrollment2 = new Enrollment(2, student, Term.Y2012_2ND);
		Enrollment enrollment3 = new Enrollment(3, student, Term.Y2013_1ST);
		Enrollment enrollment4 = new Enrollment(4, student, Term.Y2013_2ND);
		
		Set<Enrollment> previous = student.getPreviousEnrollmentsTo(enrollment3);
		Set<Enrollment> expected = new HashSet<Enrollment>() {{ add(enrollment1); add(enrollment2); }};
		assertEquals(expected, previous);
	}

}
