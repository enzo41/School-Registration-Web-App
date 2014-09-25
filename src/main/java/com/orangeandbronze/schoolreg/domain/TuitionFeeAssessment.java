package com.orangeandbronze.schoolreg.domain;

import java.util.List;

public class TuitionFeeAssessment {
	public final int UNIT_PER_SUBJECT = 3;
	public final int FRESHMEN_MINIMUM_UNITS = 15;
	public final int SOPHOMORE_MINIMUM_UNITS = 18;
	public final int JUNIOR_MINIMUM_UNITS = 18;
	public final int SENIOR_MINIMUM_UNITS = 0;
	public final int UNDERGRADUATE_SUBJECT_FEE_PER_SECTION = 2000;
	public final int GRADUATE_SUBJECT_FEE_PER_SECTION = 4000;
	public final int MISCELLANEOUS_FEES = 2000;
	public final int PERCENTAGE_OF_HALF_SCHOLARSHIP_COVERAGE = 50;
	
	private Student student;
	private Term term;
	private int numberOfEnlistedUndergraduateSubject;
	private int feePerUndergraduateSubject;
	private int totalUndergraduateSubjctFee;
	private int numberOfEnlistedGraduateSubject;
	private int feePerGraduateSubject;
	private int totalGraduateSubjectFee;
	private int totalSubjectTuitionFee;
	private int miscellaneousFee;
	private int totalTuitionFee;
	private List<Section> sectionList;

	public TuitionFeeAssessment(){
		this.feePerUndergraduateSubject = UNDERGRADUATE_SUBJECT_FEE_PER_SECTION;
		this.feePerGraduateSubject = GRADUATE_SUBJECT_FEE_PER_SECTION;
		this.miscellaneousFee = MISCELLANEOUS_FEES;
	}
	
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
	public int getFeePerUndergraduateSubject() {
		return feePerUndergraduateSubject;
	}
	public void setFeePerUndergraduateSubject(int feePerUndergraduateSubject) {
		this.feePerUndergraduateSubject = feePerUndergraduateSubject;
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
	public int getFeePerGraduateSubject() {
		return feePerGraduateSubject;
	}
	public void setFeePerGraduateSubject(int feePerGraduateSubject) {
		this.feePerGraduateSubject = feePerGraduateSubject;
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
