package com.orangeandbronze.schoolreg.service;

import java.util.Map;
import java.util.Set;

import com.orangeandbronze.schoolreg.domain.Section;

public class EnlistService {
	
	/** 
	 * @param studentNumber Student number of the student that the sections will attempt to be enlisted.
	 * @param sectionNumbers Section numbers of the sections to be enlisted with the student.
	 * @return EnlistmentResult contains which sections were successfully enlisted, and which were not. 
	 * Those that were not include the reason why they failed to enlist.
	 */
	public EnlistmentResult enlistSections(Integer studentNumber, String[] sectionNumbers) {
		// TODO Auto-generated method stub
		return null;		
	}
	
	public static class EnlistmentResult {
		
		private final Set<Section> successfullyEnlisted;		
		private final Map<Section, String> failedToEnlist;
		
		private EnlistmentResult(Set<Section> successfullyEnlisted, Map<Section, String> failedToEnlist) {
			this.successfullyEnlisted = successfullyEnlisted;
			this.failedToEnlist = failedToEnlist;
		}

		public Set<Section> getSuccessfullyEnlisted() {
			return successfullyEnlisted;
		}
		
		/** Returns a map with the sections that failed to enlist as keys, and the reason why it failed as values. **/
		public Map<Section, String> getFailedToEnlist() {
			return failedToEnlist;
		}
		
		
	}

}
