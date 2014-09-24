package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.domain.Faculty;

public class FacultyDaoImpl extends Dao implements FacultyDao{	
	
	@Override
	public List<Faculty> fetchAllFaculty(){
		
		String sql = "select pk, faculty_number from faculty";
		List<Faculty> facultyList = new ArrayList<>();

		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int facultyNumber = rs.getInt("faculty_number");
				Faculty faculty = new Faculty(facultyNumber);
				facultyList.add(faculty);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}

		return facultyList;
		
	}

	@Override
	public Integer getPkByFacultyNumber(int facultyNumber) {
		String sql = "select pk from faculty where faculty_number = ?";
		Integer pk;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, facultyNumber);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				pk = rs.getInt(1); 
			} else{
				pk = null;
			}
	
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		
		return pk;
	}

}
