package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.orangeandbronze.schoolreg.domain.Term;

public class EnrollmentSectionDaoImpl extends Dao implements
		EnrollmentSectionDao {
	
	@Override
	public Integer countEnlistedSection(Integer studentNumber, Term currentTerm) {
		
		String sql = "select	count(*) " +
					 "from		students		st, " +
					 			"enrollments	er, " +
					 			"enrollment_sections	es " +
					 "where	st.student_number	=	? " +
					 "and	er.fk_students		=	st.pk " +
					 "and	er.term				=	? " +
					 "and	es.fk_enrollment	=	er.pk";
		Integer count;
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentNumber);
			pstmt.setString(2, currentTerm.toString());

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1); 
			} else{
				count = null;
			}
	
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch EnrollmentSection data", e);
		}
		
		return count;
	}

}
