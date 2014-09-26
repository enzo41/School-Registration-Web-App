package com.orangeandbronze.schoolreg.service;

import java.util.ArrayList;
import java.util.List;

import com.orangeandbronze.schoolreg.dao.FacultyDao;
import com.orangeandbronze.schoolreg.dao.FacultyDaoImpl;
import com.orangeandbronze.schoolreg.dao.SectionDao;
import com.orangeandbronze.schoolreg.dao.SubjectDao;
import com.orangeandbronze.schoolreg.dao.SubjectDaoImpl;
import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Faculty;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;

public class SectionCreationService {
	
	FacultyDao facultyDao = new FacultyDaoImpl();
	SubjectDao subjectDao = new SubjectDaoImpl();
	SectionDao sectionDao = new SectionDao();
	
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
	
	public boolean checkTeacherScheduleAvailability(int facultyNumber, Schedule schedule){
		
		Section section = sectionDao.fetchSectionByFacultyNumberAndSchedule(facultyNumber, schedule);
		
		if(section == null){
			return true;
		} else{
			return false;
		}
	}
	
	public Section createSection(String sectionNumber, int facultyNumber, String subjectId, Schedule schedule) throws DataNotFoundException{
		
		Integer currenetMaxPkNumber = sectionDao.getMaxPk();
		if(currenetMaxPkNumber == null){
			currenetMaxPkNumber = 0;
		}
		
		Integer subjectPk = subjectDao.getPkBySubjectId(subjectId);
		Integer facultyPk = facultyDao.getPkByFacultyNumber(facultyNumber);
		
		if((subjectPk == null) || (facultyPk == null)){
			String errorMessage = "Sorry for your inconvenience. System admin is taking care of this. Try again in 1 hour.";
			throw new DataNotFoundException(errorMessage);
		}
		
		sectionDao.createSection((currenetMaxPkNumber+1), sectionNumber, subjectPk, facultyPk, schedule.toString());
		
		return sectionDao.getById(sectionNumber);
		
	}
	
	public String getsectionCreationErrorMessage(){
		String errorMessage = "Teacher is unavailable at the specified schedule. Teacher has a class.";
		return errorMessage;
	}

	public void setFacultyDao(FacultyDao facultyDao) {
		this.facultyDao = facultyDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}
	
	

}