package com.orangeandbronze.schoolreg.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.orangeandbronze.schoolreg.dao.EnrollmentDao;
import com.orangeandbronze.schoolreg.dao.SectionDao;
import com.orangeandbronze.schoolreg.dao.StudentDaoImpl;
import com.orangeandbronze.schoolreg.domain.EnlistmentConflictException;
import com.orangeandbronze.schoolreg.domain.Enrollment;
import com.orangeandbronze.schoolreg.domain.MissingPrerequisitesException;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.domain.Term;

public class EnlistService {

	private StudentDaoImpl studentDao = new StudentDaoImpl();
	private SectionDao sectionDao = new SectionDao();
	private EnrollmentDao enrollmentDao = new EnrollmentDao();
	
	public Set<Section> getAllSections() {
		return sectionDao.getAll();
	}

	/**
	 * @param studentNumber
	 *            Student number of the student that the sections will attempt
	 *            to be enlisted.
	 * @param sectionNumbers
	 *            Section numbers of the sections to be enlisted with the
	 *            student.
	 * @return EnlistmentResult contains which sections were successfully
	 *         enlisted, and which were not. Those that were not include the
	 *         reason why they failed to enlist.
	 */
	public EnlistmentResult enlistSections(Integer studentNumber, String[] sectionNumbers) {
		String errorMessage=null;
		// Fetch domain objects from DB
		Student student = new Student(studentNumber);
		Integer studentPk = studentDao.getPkById(studentNumber);
		Integer sectionPk = 0;
		Section[] sections = new Section[sectionNumbers.length];
		for (int i = 0; i < sectionNumbers.length; i++) {
			sections[i] = sectionDao.getById(sectionNumbers[i]);
		}
		Integer currentEnNo = enrollmentDao.getMaxEnNo();
		Enrollment enrollment = enrollmentDao.getBy(currentEnNo, student, Term.getCurrent());

		// delegate work to domain model
		Set<Section> successfullyEnlisted = new HashSet<>();
		Map<Section, String> failedToEnlist = new HashMap<>();
		for (Section section : sections) {
			try {
				//enrollment.enlist(section);
				if(enrollmentDao.isSameSection(sectionDao.getSectionNumberPk(section.getSectionNumber().toString()), studentPk, Term.getCurrent())) {
					errorMessage = "Cannot enroll in the same section.";
					throw new EnlistmentConflictException("Current Section: " + section.getSectionNumber() + "has already been enlisted/taken.");
				}
				else if(enrollmentDao.hasScheduleConflicts(section.getSchedule().toString(), studentPk, Term.getCurrent())){
					errorMessage = "Schedule conflict for the chosen section.";
					throw new EnlistmentConflictException("Current Section: " + section.getSectionNumber() + "has schedule conflict.");
				}
				else if(enrollmentDao.prereqNotTaken(section.getSubject(), studentPk, Term.getCurrent())){
					errorMessage = "Prerequisite/s not yet taken.";
					throw new MissingPrerequisitesException("Current Section: " + section.getSectionNumber() + "has prerequisites not taken.");
				}
				else {
					successfullyEnlisted.add(section);
					sectionPk = sectionDao.getSectionNumberPk(section.getSectionNumber().toString());
				}
			} catch (EnlistmentConflictException e) {
				failedToEnlist.put(section, errorMessage);
			} catch (MissingPrerequisitesException e) {
				failedToEnlist.put(section, errorMessage);
			}
		}

		//Check if at least one successful enlistments
		
		if(successfullyEnlisted.isEmpty()==false) {
		//Get requirements for saving to database
		
		Integer currentEnPk = enrollmentDao.getMaxPk();
		Integer currentSEPk = enrollmentDao.getMaxSEPk();
		Integer currentESPk = enrollmentDao.getMaxESPk();
		
		// must successfully save result to database before returning result, otherwise, throws DataAccessException
		enrollmentDao.save(enrollment,studentPk,sectionPk,currentEnPk,currentSEPk,currentESPk);
		}
		return new EnlistmentResult(successfullyEnlisted, failedToEnlist);
	}

	public static class EnlistmentResult {

		private final Set<Section> successfullyEnlisted;
		private final Map<Section, String> failedToEnlist;

		EnlistmentResult(Set<Section> successfullyEnlisted,
				Map<Section, String> failedToEnlist) {
			this.successfullyEnlisted = successfullyEnlisted;
			this.failedToEnlist = failedToEnlist;
		}

		public Set<Section> getSuccessfullyEnlisted() {
			return successfullyEnlisted;
		}

		/**
		 * Returns a map with the sections that failed to enlist as keys, and
		 * the reason why it failed as values.
		 **/
		public Map<Section, String> getFailedToEnlist() {
			return failedToEnlist;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((failedToEnlist == null) ? 0 : failedToEnlist.hashCode());
			result = prime
					* result
					+ ((successfullyEnlisted == null) ? 0
							: successfullyEnlisted.hashCode());
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
			EnlistmentResult other = (EnlistmentResult) obj;
			if (failedToEnlist == null) {
				if (other.failedToEnlist != null)
					return false;
			} else if (!failedToEnlist.equals(other.failedToEnlist))
				return false;
			if (successfullyEnlisted == null) {
				if (other.successfullyEnlisted != null)
					return false;
			} else if (!successfullyEnlisted.equals(other.successfullyEnlisted))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "EnlistmentResult [successfullyEnlisted="
					+ successfullyEnlisted + ", failedToEnlist="
					+ failedToEnlist + "]";
		}

	}

	void setStudentDao(StudentDaoImpl studentDao) {
		this.studentDao = studentDao;
	}

	void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	void setEnrollmentDao(EnrollmentDao enrollmentDao) {
		this.enrollmentDao = enrollmentDao;
	}
	
	

}
