package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.orangeandbronze.schoolreg.domain.Enrollment;
import com.orangeandbronze.schoolreg.domain.Student;
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

}