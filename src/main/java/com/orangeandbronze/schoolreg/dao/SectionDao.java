package com.orangeandbronze.schoolreg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;

// TODO Factor out duplicate code
public class SectionDao extends Dao {

	public Section getById(String sectionNumber) {

		String sql = "SELECT sections.*, subjects.subject_id, faculty.faculty_number, subject_prerequisites.fk_prerequisite FROM sections "
				+ "INNER JOIN subjects ON sections.fk_subject = subjects.pk " + "INNER JOIN faculty ON sections.fk_faculty = faculty.pk "
				+ "LEFT JOIN subject_prerequisites ON subjects.pk = subject_prerequisites.fk_subject " + "WHERE section_number = ? ";

		Section section = null;

		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sectionNumber);
			ResultSet rs = pstmt.executeQuery();

			long pk = 0;
			long fkSubject = 0;
			String scheduleString = null;
			String subjectId = null;

			Faculty instructor = null;

			Set<Subject> prereqs = new HashSet<>();
			while (rs.next()) {
				if (rs.isFirst()) {
					pk = rs.getInt("pk");
					fkSubject = rs.getInt("fk_subject");
					long fkFaculty = rs.getInt("fk_faculty");
					scheduleString = rs.getString("schedule");
					subjectId = rs.getString("subject_id");
					int facultyNum = rs.getInt("faculty_number");

					if (fkFaculty != 0) { // not TBA
						instructor = new Faculty(facultyNum);
						setPrivateKey(instructor, fkFaculty);
					} else { // TBA
						instructor = Faculty.TBA;
					}

				}

				// TODO Try to handle with just one SQL call instead of multiple
				// TODO handle deep recursive prereqs

				// get prereqs
				int fkPrerequisite = rs.getInt("fk_prerequisite");
				PreparedStatement prereqPstmt = conn.prepareStatement("SELECT subject_id FROM subjects WHERE pk = ?");
				prereqPstmt.setInt(1, fkPrerequisite);
				ResultSet rsPrereq = prereqPstmt.executeQuery();

				while (rsPrereq.next()) {
					Subject prereq = new Subject(rsPrereq.getString("subject_id"));
					setPrivateKey(prereq, fkPrerequisite);
					prereqs.add(prereq);
				}

			}
			Subject subject = new Subject(subjectId, prereqs);
			setPrivateKey(subject, fkSubject);

			String[] dayPeriod = scheduleString.split("\\s+");
			Schedule schedule = dayPeriod.length > 1 ? new Schedule(Days.valueOf(dayPeriod[0]), Period.valueOf(dayPeriod[1])) : Schedule.TBA;
			section = new Section(sectionNumber, subject, schedule, instructor);
			setPrivateKey(section, pk);

		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}

		return section;

	}

	// TODO Fetch prerequisites
	public Set<Section> getAll() {
		String sql = "SELECT sections.*, subjects.subject_id, faculty.faculty_number FROM sections "
				+ "INNER JOIN subjects ON sections.fk_subject = subjects.pk " + "INNER JOIN faculty ON sections.fk_faculty = faculty.pk ";

		Set<Section> sections = new HashSet<>();

		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				long pk = rs.getInt("pk");
				String sectionNumber = rs.getString("section_number");
				long fkSubject = rs.getInt("fk_subject");
				long fkFaculty = rs.getInt("fk_faculty");
				String scheduleString = rs.getString("schedule");
				String subjectId = rs.getString("subject_id");
				int facultyNum = rs.getInt("faculty_number");
				Faculty instructor;

				if (fkFaculty != 0) { // not TBA
					instructor = new Faculty(facultyNum);
					setPrivateKey(instructor, fkFaculty);
				} else { // TBA
					instructor = Faculty.TBA;
				}

				Subject subject = new Subject(subjectId);
				setPrivateKey(subject, fkSubject);

				String[] dayPeriod = scheduleString.split("\\s+");
				Schedule schedule = dayPeriod.length > 1 ? new Schedule(Days.valueOf(dayPeriod[0]), Period.valueOf(dayPeriod[1])) : Schedule.TBA;
				Section section = new Section(sectionNumber, subject, schedule, instructor);
				setPrivateKey(section, pk);

				sections.add(new Section(sectionNumber, subject, schedule, instructor));
			}

		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}

		return sections;
	}
	
	public Section fetchSectionByFacultyNumberAndSchedule(int facultyNumber, Schedule schedule){
		
		String sql = "select	sc.section_number, " +
					 			"sb.subject_id " +
					 "from		sections	sc, " +
					 			"faculty	fc, " +
					 			"subjects	sb " +
					 "where		sc.schedule = ? " +
					 "and		fc.pk	=	sc.fk_faculty " +
					 "and		fc.faculty_number = ? " +
					 "and		sb.pk	=	sc.fk_subject"; 
		
		try (Connection conn = getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule.toString());
			pstmt.setInt(2, facultyNumber);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()){
				String sectionNumber = rs.getString("section_number");
				Subject subject = new Subject(rs.getString("subject_id"));
				Section section = new Section(sectionNumber, subject, schedule, new Faculty(facultyNumber));
				
				return section;
			} else {
				return null;
			}	
		} catch (SQLException e) {
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
	}
	
	public Integer getMaxPk(){
		
		String sql = "select max(pk) from sections";
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
			throw new DataAccessException("Something happend while trying to fetch Section data", e);
		}
		
		return maxPk;
	}
	
	public void createSection(int pk, String sectionNumber, int subjectPk, int facultyPk, String scheduleString){
		
	}
	
	

}
