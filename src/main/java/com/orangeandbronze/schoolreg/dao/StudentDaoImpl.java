package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.domain.Student;
import com.orangeandbronze.schoolreg.dao.StudentDao;

public class StudentDaoImpl extends Dao implements StudentDao {
	
	// Getting the list of Students for the Login
	
	public List<Student> getAllStudents() {
		List<Student> students = null;

		String sql = "select pk, student_number from students";
		List<Student> studentList = new ArrayList<>();
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer studentId = rs.getInt("subject_id");
				Student student = new Student(studentId);
				studentList.add(student);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		return studentList;
	}
	
	public Student getStudentById(){
		return null;
	}

}

