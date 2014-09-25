package com.orangeandbronze.schoolreg.domain;

import java.util.List;

public class TuitionFeeAssessment {
	
	private Student student;
	private Term term;
	private int numberOfEnlistedUndergraduateSubject;
	private int totalUndergraduateSubjctFee;
	private int numberOfEnlistedGraduateSubject;
	private int totalGraduateSubjectFee;
	private int totalSubjectTuitionFee;
	private int miscellaneousFee;
	private int totalTuitionFee;
	private List<Section> sectionList;

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public int getNumberOfEnlistedUndergraduateSubject() {
		return numberOfEnlistedUndergraduateSubject;
	}
	public void setNumberOfEnlistedUndergraduateSubject(
			int numberOfEnlistedUndergraduateSubject) {
		this.numberOfEnlistedUndergraduateSubject = numberOfEnlistedUndergraduateSubject;
	}
	public int getTotalUndergraduateSubjctFee() {
		return totalUndergraduateSubjctFee;
	}
	public void setTotalUndergraduateSubjctFee(int totalUndergraduateSubjctFee) {
		this.totalUndergraduateSubjctFee = totalUndergraduateSubjctFee;
	}
	public int getNumberOfEnlistedGraduateSubject() {
		return numberOfEnlistedGraduateSubject;
	}
	public void setNumberOfEnlistedGraduateSubject(
			int numberOfEnlistedGraduateSubject) {
		this.numberOfEnlistedGraduateSubject = numberOfEnlistedGraduateSubject;
	}
	public int getTotalGraduateSubjectFee() {
		return totalGraduateSubjectFee;
	}
	public void setTotalGraduateSubjectFee(int totalGraduateSubjectFee) {
		this.totalGraduateSubjectFee = totalGraduateSubjectFee;
	}
	public int getTotalSubjectTuitionFee() {
		return totalSubjectTuitionFee;
	}
	public void setTotalSubjectTuitionFee(int totalSubjectTuitionFee) {
		this.totalSubjectTuitionFee = totalSubjectTuitionFee;
	}
	public int getMiscellaneousFee() {
		return miscellaneousFee;
	}
	public void setMiscellaneousFee(int miscellaneousFee) {
		this.miscellaneousFee = miscellaneousFee;
	}
	public int getTotalTuitionFee() {
		return totalTuitionFee;
	}
	public void setTotalTuitionFee(int totalTuitionFee) {
		this.totalTuitionFee = totalTuitionFee;
	}
	public List<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

}
