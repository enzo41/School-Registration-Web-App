package com.orangeandbronze.schoolreg.service;

import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.dao.FacultyDao;
import com.orangeandbronze.schoolreg.dao.FacultyDaoImpl;
import com.orangeandbronze.schoolreg.dao.SubjectDao;
import com.orangeandbronze.schoolreg.dao.SubjectDaoImpl;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Subject;
import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Period;

public class SectionCreationService {
	
	FacultyDao facultyDao = new FacultyDaoImpl();
	SubjectDao subjectDao = new SubjectDaoImpl();
	
	public List<Faculty> fetchFacultyList(){
		List<Faculty> facultyList = facultyDao.fetchAllFaculty();
		return facultyList;
	};
	
	public List<Subject> fetchSubjectList(){
		List<Subject> subjectList = subjectDao.fetchAllSubject();
		return subjectList;
	};
	
	public List<Days> fetchDaysList(){
		
		List<Days> daysList = new ArrayList<>();
		daysList.add(Days.MTH);
		daysList.add(Days.TF);
		daysList.add(Days.WS);
		
		return daysList;
	}
	
	public List<Period> fetchPeriodList(){
		
		List<Period> periodList = new ArrayList<>();
		periodList.add(Period.AM830);
		periodList.add(Period.AM10);
		periodList.add(Period.AM1130);
		periodList.add(Period.PM1);
		periodList.add(Period.PM230);
		periodList.add(Period.PM4);
		
		return periodList;
		
	}
	
	
	
	
	public boolean checkTeacherScheduleAvailability(){
		return true;
	}
	
	public void createSection(){
		//Implement insert statement later
	}

}