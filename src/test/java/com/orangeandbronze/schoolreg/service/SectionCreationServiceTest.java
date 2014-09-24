package com.orangeandbronze.schoolreg.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.orangeandbronze.schoolreg.domain.Days;
import com.orangeandbronze.schoolreg.domain.Period;
import com.orangeandbronze.schoolreg.domain.Schedule;

public class SectionCreationServiceTest {

		@Test
		public void testCheckTeacherScheduleAvailabilityReurnTrue(){
			
			SectionCreationService sectionCreationService = new SectionCreationService();
			int facultyNumber = 40;
			Schedule schedule = new Schedule(Days.MTH, Period.AM830);
			boolean isAvailable = sectionCreationService.checkTeacherScheduleAvailability(facultyNumber, schedule);
			
			assertEquals(true , isAvailable);
			
		}

}
