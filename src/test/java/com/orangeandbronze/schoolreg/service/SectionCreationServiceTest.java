package com.orangeandbronze.schoolreg.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.orangeandbronze.schoolreg.dao.SectionDao;
import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;
import com.orangeandbronze.schoolreg.domain.Section;
import com.orangeandbronze.schoolreg.domain.Subject;

public class SectionCreationServiceTest {

		@Test
		public void testCheckTeacherScheduleAvailabilityReurnTrue(){
			
			//Prepare data for mockDao
			Integer facultyNumber = 30;
			Schedule schedule = new Schedule(Days.TF, Period.PM1);
			Section section = null;
					
			/* Mock the daos */
			SectionDao sectionDao = mock(SectionDao.class);
			when(sectionDao.fetchSectionByFacultyNumberAndSchedule(facultyNumber, schedule)).thenReturn(section);
			
			SectionCreationService sectionCreationService = new SectionCreationService();
			sectionCreationService.setSectionDao(sectionDao);
			
			boolean checkResult = sectionCreationService.checkTeacherScheduleAvailability(facultyNumber, schedule);
			
			assertEquals(true ,checkResult);
			
		}
		
		@Test
		public void testCheckTeacherScheduleAvailabilityReurnFalse(){
			
			//Prepare data for mockDao
			Integer facultyNumber = 30;
			Schedule schedule = new Schedule(Days.TF, Period.PM1);
			Subject subject = new Subject("MA9");
			Section section = new Section("MA9700WSPM", subject);
					
			/* Mock the daos */
			SectionDao sectionDao = mock(SectionDao.class);
			when(sectionDao.fetchSectionByFacultyNumberAndSchedule(facultyNumber, schedule)).thenReturn(section);
			
			SectionCreationService sectionCreationService = new SectionCreationService();
			sectionCreationService.setSectionDao(sectionDao);
			
			boolean checkResult = sectionCreationService.checkTeacherScheduleAvailability(facultyNumber, schedule);
			
			assertEquals(false ,checkResult);
			
		}
		
		

}
