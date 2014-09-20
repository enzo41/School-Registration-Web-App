package com.orangeandbronze.schoolreg.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.orangeandbronze.entity.Entity;

public class Student extends Entity {
	
	private final Integer studentNumber;
	
	private final SortedSet<Enrollment> enrollments = new TreeSet<>();

	public Student(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public Student(Integer studentNumber, Set<Enrollment> enrollments) {
		this.studentNumber = studentNumber;
		this.enrollments.addAll(enrollments);
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	Set<Enrollment> getEnrollments() {
		return new HashSet<>(enrollments);
	}
	
	void addEnrollement(Enrollment e) {
		enrollments.add(e);
	}

	Set<Enrollment> getPreviousEnrollments(Enrollment e) {
		return enrollments.headSet(e);
	}

	public boolean hasTakenPrerequisites(Section newSec, Enrollment currentEnrollment) {
		Set<Enrollment> prevEnrollments = getPreviousEnrollments(currentEnrollment);
		return newSec.hasAllPrerequisitesIn(prevEnrollments);
	}
	
	

}
