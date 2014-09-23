package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.domain.Subject;

public class SubjectDaoImpl extends Dao implements SubjectDao {
	
	@Override
	public List<Subject> fetchAllSubject() {
		
	String sql = "select pk, subject_id from subjects";
	List<Subject> subjectList = new ArrayList<>();

	try (Connection conn = getConnection()) {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String subjectId = rs.getString("subject_id");
			Subject subject = new Subject(subjectId);
			subjectList.add(subject);
		}
	} catch (SQLException e) {
		throw new DataAccessException("Something happend while trying to fetch Section data", e);
	}

	return subjectList;
	}

	@Override
	public int getPkBySubjectId() {
		// TODO Auto-generated method stub
		return 0;
	}

}

