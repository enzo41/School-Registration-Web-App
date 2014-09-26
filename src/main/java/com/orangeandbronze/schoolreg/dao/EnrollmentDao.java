package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.domain.Enrollment;
import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.domain.Subject;
import com.orangeandbronze.schoolreg.domain.Term;

public class EnrollmentDao extends Dao {
	
	public Enrollment getBy(Integer enrollment_number, Student student, Term term) {
		return new Enrollment(enrollment_number,student,term);
	}
	
	public void save(Enrollment enrollment, Integer studentPk, Integer sectionPk, Integer enpk, Integer sepk, Integer espk){
		//initialize for enrollment tables update
		Integer enrollmentNumber = (enrollment.getEnrollmentNumber()+1);
		String term = enrollment.getTerm().toString();
		Integer nextEnPk = enpk + 1;
		
		//actual save to enrollments table
		String sql = "INSERT INTO enrollments(pk, enrollment_number, fk_students, term) values " +
						"(?,?,?,?)";
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nextEnPk);
			pstmt.setInt(2, enrollmentNumber);
			pstmt.setInt(3, studentPk);
			pstmt.setString(4, term);
			
			int r = pstmt.executeUpdate();
			
			if(r == 0){
				throw new SQLException();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		//initialization
		Integer nextSEPk = sepk + 1;
		sql = "INSERT INTO student_enrollments(pk, fk_student, fk_enrollment) values " +
				"(?,?,?)";
		//actual save to student_enrollments table
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nextSEPk);
			pstmt.setInt(2, studentPk);
			pstmt.setInt(3, nextEnPk);
			
			int r = pstmt.executeUpdate();
			
			if(r == 0){
				throw new SQLException();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		//initialization
		Integer nextESPk = espk + 1;
		sql = "INSERT INTO enrollment_sections(pk, fk_enrollment, fk_sections) values " +
				"(?,?,?)";
		//actual save to enrollment_sections table
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nextESPk);
			pstmt.setInt(2, nextEnPk);
			pstmt.setInt(3, sectionPk);
			
			int r = pstmt.executeUpdate();
			
			if(r == 0){
				throw new SQLException();
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
	}

	public Integer getMaxPk() {
		String sql = "select max(pk) from enrollments";
		Integer maxPk;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				maxPk = rs.getInt(1); 
			} else{
				maxPk = null;
			}
			 
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		return maxPk;
	}

	public Integer getMaxEnNo() {
		String sql = "select max(enrollment_number) from enrollments";
		Integer maxEnNo;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				maxEnNo = rs.getInt(1); 
			} else{
				maxEnNo = null;
			}
			 
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		return maxEnNo;
	}

	public Integer getMaxSEPk() {
		String sql = "select max(pk) from student_enrollments";
		Integer maxSEPk;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				maxSEPk = rs.getInt(1); 
			} else{
				maxSEPk = null;
			}
			 
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		return maxSEPk;
	}

	public Integer getMaxESPk() {
		String sql = "select max(pk) from enrollment_sections";
		Integer maxESPk;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				maxESPk = rs.getInt(1); 
			} else{
				maxESPk = null;
			}
			 
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		return maxESPk;
	}

	public boolean hasScheduleConflicts(String schedule, Integer studentPk, Term term) {
		String currentTerm = term.toString();
		Integer count=0;
		
		String sql = "SELECT sections.schedule FROM enrollments " +
		"INNER JOIN enrollment_sections ON enrollments.pk = enrollment_sections.fk_enrollment " +
		"INNER JOIN sections ON enrollment_sections.fk_sections = sections.pk " +
		"WHERE enrollments.fk_students = ? and enrollments.term = ?";
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentPk);
			pstmt.setString(2, currentTerm);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String fetch = rs.getString("schedule");
				if(schedule.equals(fetch)){
					count++;
				}
			}
			
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		if(count==0){
			return false;
		}
		else{
			return true;
		}
	}

	public boolean isSameSection(Integer sectionNumberPk, Integer studentPk, Term term) {
		String currentTerm = term.toString();
		Integer count=0;
		
		String sql = "SELECT enrollment_sections.fk_sections FROM enrollments INNER JOIN "+
				"enrollment_sections ON enrollments.pk = enrollment_sections.fk_enrollment "+
				"WHERE enrollments.fk_students = ? and enrollments.term = ?";
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentPk);
			pstmt.setString(2, currentTerm);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Integer fetch = rs.getInt("fk_sections");
				if(sectionNumberPk.equals(fetch)){
					count++;
				}
			}
			
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		if(count==0){
			return false;
		}
		else{
			return true;
		}
	}

	public boolean prereqNotTaken(Subject subject, Integer studentPk, Term current) {
		String subjectId = subject.getSubjectId();
		Integer subjectPk = 0;
		String term = current.toString();
		List<Integer> subjectsTaken = new ArrayList<>();
		Integer prerequisites = null;
		Integer noSubjTaken = 0;
		int x = 0;
		int y = 0;
		
		// Get pk of subject
		
		String sql = "SELECT pk FROM subjects WHERE subject_id = ?";
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subjectId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				subjectPk = rs.getInt("pk"); 
			} else{
				subjectPk = null;
			}
			
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		// Get subjects already taken
		
		sql = "SELECT subjects.pk FROM enrollments INNER JOIN enrollment_sections " +
				"ON enrollments.pk = enrollment_sections.fk_enrollment INNER JOIN sections " +
				"ON enrollment_sections.fk_sections = sections.pk INNER JOIN subjects " +
				"ON sections.fk_subject = subjects.pk " +
				"WHERE enrollments.fk_students = ? and enrollments.term<> ?";
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentPk);
			pstmt.setString(2, term);
			ResultSet rs = pstmt.executeQuery();
			
			int i = 0;
			while (rs.next()){
				Integer subjectTakenPk = rs.getInt("pk");
				subjectsTaken.add(subjectTakenPk);
				i++;
			}
			noSubjTaken = i;
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		
		//Get Prerequisites using subject.pk
		
		sql = "SELECT fk_prerequisite FROM subject_prerequisites where fk_subject = ?";
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subjectPk);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()){
				int i = 0;
				prerequisites = rs.getInt("fk_prerequisite");
				if(noSubjTaken!=0){
					while(noSubjTaken>i){
						if(prerequisites.equals(subjectsTaken.get(i))){
							y++;
						}
						i++;
					}
				}
				x++;
			}
			
			
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Enrollment data", e);
		}
		if(x==y) {
			return false;
		}
		else {
			return true;
		}
	}

}