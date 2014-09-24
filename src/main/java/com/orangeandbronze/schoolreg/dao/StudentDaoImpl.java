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
	public Student getById(int studentNumber) {
		String sql = "select pk from students where student_number = " + studentNumber;
		Student student;
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Integer studentId = rs.getInt("pk");
			student = new Student(studentId);
		}
		catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		return student;
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
}

