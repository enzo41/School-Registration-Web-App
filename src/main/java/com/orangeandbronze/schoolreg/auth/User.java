package com.orangeandbronze.schoolreg.auth;

public class User {
	public enum Type {
		STUDENT, ADMIN, INVALID
	}

	/** If user is student, studentId is same as userId. **/
	private final Integer userId;
	private Type type;
	private String name;

	User(Integer userId, Type type, String name) {
		this.userId = userId;
		this.type = type;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	boolean isInvalid() {
		return type == Type.INVALID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
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
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", type=" + type + ", name=" + name
				+ "]";
	}

}
