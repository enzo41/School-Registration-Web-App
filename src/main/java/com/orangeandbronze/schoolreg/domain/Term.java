package com.orangeandbronze.schoolreg.domain;

/** Over-simplified representation of academic term. Not to emulated in a real system. **/
public enum Term {
	Y2012_1ST, Y2012_2ND, Y2013_1ST, Y2013_2ND, Y2014_1ST;
	
	/** Returns the last term in the enum. **/
	public  static Term getCurrent() {
		return values()[values().length - 1];
	}
}
