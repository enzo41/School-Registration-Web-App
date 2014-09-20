package com.orangeandbronze.schoolreg.users;

import com.orangeandbronze.entity.Entity;

public class User extends Entity {
	public enum Type {STUDENT, ADMIN}
	
	/** If user is student, studentId is same as userId. **/
	private final int userId;
	private Type type;
	
	public User(int userId, Type type) {
		this.userId = userId;
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public Type getType() {
		return type;
	}
	
	
	
}
