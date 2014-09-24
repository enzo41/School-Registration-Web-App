package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.domain.Student;

public class StudentDaoImpl extends Dao implements StudentDao {
	
	// Getting the list of Students for the Login
	@Override
	public List<Student> getAllStudents() {

		String sql = "select pk, student_number from students";
		List<Student> studentList = new ArrayList<>();
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer studentId = rs.getInt("student_number");
				Student student = new Student(studentId);
				studentList.add(student);
			}
		}
		catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		return studentList;
}

	//Changed getById to send back pk instead of student_number
	
	@Override
	public Integer getPkById(int studentNumber) {
		String sql = "select pk from students where student_number = " + studentNumber;
		Integer studentId;
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			studentId = rs.getInt("pk");
		}
		catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		return studentId;
	}
	
	@Override
	public List<Integer> getStudentsInt() {

		String sql = "select student_number from students";
		List<Integer> studentList = new ArrayList<>();
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer studentId = rs.getInt("student_number");
				studentList.add(studentId);
			}
		}
		catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		return studentList;
}

	@Override
	public Integer getAcdemicYearByStudentNumber(int studentNumber){
		
		String studentsQuery = "select academic_year, schorlarship from students where student_number = ?";
		Integer academicYear = null;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(studentsQuery);
			pstmt.setInt(1, studentNumber);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				academicYear = rs.getInt("academic_year");
			}
			
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		
		return academicYear;
	}
}

