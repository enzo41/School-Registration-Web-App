package com.orangeandbronze.schoolreg.auth;

import java.util.HashMap;
import java.util.Map;

class LoginService {

	private final Map<Integer, User> users = new HashMap<Integer, User>() {
		{	
			User grady = new User(111, User.Type.STUDENT, "Grady Booch");
			put(grady.getUserId(), grady);
			User james = new User(222, User.Type.STUDENT, "James Rumbaugh");
			put(james.getUserId(), james);
			User ivar = new User(333, User.Type.ADMIN, "Ivar Jacobson");
			put(ivar.getUserId(), ivar);
			
		}
	};

	User getUser(int userId) {
		return users.getOrDefault(userId, new User(null, User.Type.INVALID, null));
	}

}
