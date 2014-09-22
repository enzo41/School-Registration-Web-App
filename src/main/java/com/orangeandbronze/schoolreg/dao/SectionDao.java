package com.orangeandbronze.schoolreg.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;

public class SectionDao {

	public SectionDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // load driver class into
													// JVM
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("Problem while loading JDBC driver.",
					e);
		}
	}

	private Map<String, Section> allSections = new HashMap<String, Section>() {
		{
			put("AAA111", new Section("AAA111", new Subject("MATH53"),
					new Schedule(Days.MTH, Period.AM10)));
			put("BBB222", new Section("BBB222", new Subject("COM1")));
			put("CCC333", new Section("CCC333", new Subject("CS11")));
			put("DDD444", new Section("DDD444", new Subject("PHILO1"),
					new Schedule(Days.TF, Period.PM4)));
			put("EEE555", new Section("EEE555", new Subject("CS11")));
			put("GGG777", new Section("GGG777", new Subject("MATH11")));
			put("ZZZ000", new Section("ZZZ000", new Subject("CHEM11"),
					new Schedule(Days.TF, Period.PM4)));
		}
	};

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/school_registration", "root", "");
	}

	public Section getById(String sectionNumber) {

		String sql = "SELECT sections.*, subjects.subject_id, faculty.faculty_number, subject_prerequisites.fk_prerequisite FROM sections "
				+ "INNER JOIN subjects ON sections.fk_subject = subjects.pk "
				+ "INNER JOIN faculty ON sections.fk_faculty = faculty.pk "
				+ "LEFT JOIN subject_prerequisites ON subjects.pk = subject_prerequisites.fk_subject "
				+ "WHERE section_number = ? ";

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
						// Set private key so we can map it to a DB row later on
						// for saving/updating.
						Field facultyPk = Subject.class.getSuperclass()
								.getDeclaredField("primaryKey");
						facultyPk.setAccessible(true);
						facultyPk.set(instructor, fkFaculty);
					} else { // TBA
						instructor = Faculty.TBA;
					}

				}
				
				// TODO Try to handle with just one SQL call instead of multiple
				// get prereqs
				int fkPrerequisite = rs.getInt("fk_prerequisite");
				PreparedStatement prereqPstmt = conn
						.prepareStatement("SELECT subject_id FROM subjects WHERE pk = ?");
				prereqPstmt.setInt(1, fkPrerequisite);
				ResultSet rsPrereq = prereqPstmt.executeQuery();

				// TODO handle deep recursive prereqs

				while (rsPrereq.next()) {
					Subject prereq = new Subject(
							rsPrereq.getString("subject_id"));
					// Set private key so we can map it to a DB row later on for
					// saving/updating.
					Field subjectPk = Subject.class.getSuperclass()
							.getDeclaredField("primaryKey");
					subjectPk.setAccessible(true);
					subjectPk.set(prereq, fkPrerequisite);
					prereqs.add(prereq);
				}

			}
			Subject subject = new Subject(subjectId, prereqs);
			// Set private key so we can map it to a DB row later on for
			// saving/updating.
			Field subjectPk = Subject.class.getSuperclass().getDeclaredField("primaryKey");
			subjectPk.setAccessible(true);
			subjectPk.set(subject, fkSubject);

			String[] dayPeriod = scheduleString.split("\\s+");
			Schedule schedule = dayPeriod.length > 1 ? new Schedule(
					Days.valueOf(dayPeriod[0]), Period.valueOf(dayPeriod[1]))
					: Schedule.TBA;
			section = new Section(sectionNumber, subject, schedule, instructor);
			Field sectionPk = Section.class.getSuperclass().getDeclaredField(
					"primaryKey"); // Set private key so we can map it to a DB
									// row later on for saving/updating.
			sectionPk.setAccessible(true);
			sectionPk.set(section, pk);

		} catch (SQLException e) {
			throw new DataAccessException(
					"Something happend while trying to fetch Section data", e);
		} catch (ReflectiveOperationException e) {
			throw new DataAccessException(
					"Something happend seting section primary key via reflection.",
					e);
		}

		return section;

	}

	public Set<Section> getAll() {
		// TODO Just a stub; implement actual as JDBC
		return new HashSet<Section>(allSections.values());
	}

}
