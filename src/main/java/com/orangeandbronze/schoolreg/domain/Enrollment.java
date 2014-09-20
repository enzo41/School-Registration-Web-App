package com.orangeandbronze.schoolreg.domain;

import java.util.HashSet;
import java.util.Set;

import com.orangeandbronze.entity.Entity;

public class Enrollment extends Entity implements Comparable<Enrollment>{
	
	private final int enrollmentNumber;	
	private final Student student;
	private final Term term;
	private final Set<Section> sections = new HashSet<>();
	
	Enrollment(int enrollmentNumber, Student student, Term term) {
		this.enrollmentNumber = enrollmentNumber;
		this.student = student;
		this.term = term;
		if (!student.getEnrollments().contains(this)) {
			student.addEnrollement(this);
		}
	}
	
	Enrollment(int enrollmentNumber, Student student, Term term, Set<Section> enlistedSections) {
		this(enrollmentNumber, student, term);
		sections.addAll(enlistedSections);
	}
	
	public int getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public Student getStudent() {
		return student;
	}
	
	/** Returns a copy of the sections in this enrollment. **/
	public Set<Section> getSections() {
		return new HashSet<Section>(sections);
	}

	public void enlist(Section newSec) {
		for (Section current : sections) {
			if (current.hasConflict(newSec)) {
				throw new EnlistmentConflictException("Current Section: " + current + ", New Section: " + newSec);
			}
		}		
		
		if (!student.hasTakenPrerequisites(newSec, this)) {
			throw new MissingPrerequisitesException("");
		}
		sections.add(newSec);
		
	}

	@Override
	/** Compares based on Term **/
	public int compareTo(Enrollment other) {
		return this.term.compareTo(other.term);
	}
	
	
	

}
