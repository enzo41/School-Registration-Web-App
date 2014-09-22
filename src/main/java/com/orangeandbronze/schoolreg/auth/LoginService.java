package com.orangeandbronze.schoolreg.auth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LoginService {

	// hardcoded values
	private final Map<Integer, User> users = new HashMap<Integer, User>() {
		{	
			User grady = new User(111, User.Type.ADMIN, "Grady Booch");
			put(grady.getUserId(), grady);
			User james = new User(222, User.Type.ADMIN, "James Rumbaugh");
			put(james.getUserId(), james);
			User ivar = new User(333, User.Type.ADMIN, "Ivar Jacobson");
			put(ivar.getUserId(), ivar);
			User erich = new User(444, User.Type.STUDENT, "Erich Gamma");
			put(erich.getUserId(), erich);
			User richard = new User(555, User.Type.STUDENT, "Richard Helm");
			put(richard.getUserId(), richard);
			User ralph = new User(777, User.Type.STUDENT, "Ralph Johnson");
			put(ralph.getUserId(), ralph);
			User john = new User(888, User.Type.STUDENT, "John Vlissides");
			put(john.getUserId(), john);
		}
	};

	User getUser(int userId) {
		User user = users.get(userId);
		return user != null ? user : new User(null, User.Type.INVALID, null);
	}

	Set<User> getUsers() {
		return new HashSet<>(users.values());
	}

}
