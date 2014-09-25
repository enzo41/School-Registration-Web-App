package com.orangeandbronze.schoolreg.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Student extends Entity {
	
	private final Integer studentNumber;
	private Integer academicYear;
	private ShorlarshipStatus shorlarshipStatus;

	private final SortedSet<Enrollment> enrollments = new TreeSet<>();

	public Student(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public Student(Integer studentNumber, Set<Enrollment> enrollments) {
		this.studentNumber = studentNumber;
		this.enrollments.addAll(enrollments);
	}

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public ShorlarshipStatus getShorlarshipStatus() {
		return shorlarshipStatus;
	}

	public void setShorlarshipStatus(ShorlarshipStatus shorlarshipStatus) {
		this.shorlarshipStatus = shorlarshipStatus;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	Set<Enrollment> getEnrollments() {
		return new HashSet<>(enrollments);
	}
	
	void add(Enrollment e) {
		enrollments.add(e);
	}

	Set<Enrollment> getPreviousEnrollmentsTo(Enrollment e) {
		return enrollments.headSet(e);
	}

	public boolean hasTakenPrerequisites(Section newSec, Enrollment currentEnrollment) {
		Set<Enrollment> prevEnrollments = getPreviousEnrollmentsTo(currentEnrollment);
		return newSec.hasAllPrerequisitesIn(prevEnrollments);
	}
	
	

}
