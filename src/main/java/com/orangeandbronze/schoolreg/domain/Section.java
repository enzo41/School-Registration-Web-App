package com.orangeandbronze.schoolreg.domain;

import java.util.HashSet;
import java.util.Set;

public class Section extends Entity {
	
	public final static Section DOES_NOT_EXIST = new Section("Does Not Exist", null);

	private final String sectionNumber;
	private final Subject subject;
	private Faculty instructor;
	private Schedule schedule;

	public Section(String sectionNumber, Subject subject) {
		this.sectionNumber = sectionNumber;
		this.subject = subject;
		this.schedule = Schedule.TBA;
	}

	public Section(String sectionNumber, Subject subject, Schedule schedule) {
		this(sectionNumber, subject);
		this.schedule = schedule;
	}

	public String getSectionNumber() {
		return sectionNumber;
	}

	Subject getSubject() {
		return subject;
	}

	boolean hasConflict(Section other) {
		if (this.schedule == null) {
			return false;
		}
		return this.schedule.equals(other.schedule);
	}

	Set<Subject> getPrerequisites() {
		return subject.getPrerequisites();
	}

	public boolean hasAllPrerequisitesIn(Set<Enrollment> prevEnrollments) {
		Set<Subject> prerequisitesRequired = getPrerequisites();
		Set<Subject> prerequisitesTaken = new HashSet<>();
		for (Enrollment prevEnrollment : prevEnrollments) {
			Set<Section> prevSections = prevEnrollment.getSections();
			for (Section prevSection : prevSections){
				Subject subject = prevSection.getSubject();
				if (prerequisitesRequired.contains(subject)) {
					prerequisitesTaken.add(subject);
				}
			}
		}
		return prerequisitesRequired.equals(prerequisitesTaken);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sectionNumber == null) ? 0 : sectionNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (sectionNumber == null) {
			if (other.sectionNumber != null)
				return false;
		} else if (!sectionNumber.equals(other.sectionNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section " + sectionNumber + " {" + subject + " " + schedule + "} ";
	}

}
