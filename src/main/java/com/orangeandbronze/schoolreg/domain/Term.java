package com.orangeandbronze.schoolreg.domain;

import java.util.Date;

public class Term implements Comparable<Term>{
	
	private final Date start;
	private final Date end;	

	Term(Date start, Date end) {
		if (start.after(end) || start.equals(end)) {
			throw new IllegalArgumentException("Start date cannot be same or after end date. Start Date: " + start + ", End Date: : " + end);
		}
		this.start = start;
		this.end = end;
	}

	@Override
	/** Compares based on start date **/
	public int compareTo(Term other) {
		return this.start.compareTo(other.start);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Term [start=" + start + ", end=" + end + "]";
	}
	
	
	
	

}
