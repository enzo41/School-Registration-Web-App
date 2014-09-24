package com.orangeandbronze.schoolreg.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.orangeandbronze.schoolreg.dao.StudentDaoImpl;
import com.orangeandbronze.schoolreg.domain.Student;

class LoginService {

	private StudentDaoImpl studentDao = new StudentDaoImpl();
	
	// hardcoded values
	private final Map<Integer, User> users = new HashMap<Integer, User>() {
		{	
			User grady = new User(999, User.Type.ADMIN, "Grady Booch");
			put(grady.getUserId(), grady);
			User james = new User(998, User.Type.ADMIN, "James Rumbaugh");
			put(james.getUserId(), james);
			User ivar = new User(997, User.Type.ADMIN, "Ivar Jacobson");
			put(ivar.getUserId(), ivar);
			/*User erich = new User(444, User.Type.STUDENT, "Erich Gamma");
			put(erich.getUserId(), erich);
			User richard = new User(555, User.Type.STUDENT, "Richard Helm");
			put(richard.getUserId(), richard);
			User ralph = new User(777, User.Type.STUDENT, "Ralph Johnson");
			put(ralph.getUserId(), ralph);
			User john = new User(888, User.Type.STUDENT, "John Vlissides");
			put(john.getUserId(), john);*/
			
			//Create Hardcoded Names
			
			List<String> names = new ArrayList();
			names.add("Reid Herschel");
			names.add("Keele Zeibel");
			names.add("Farrah Oersteid");
			names.add("Richard Helm");
			names.add("Aurora Arte");

			//Get List of Students
			
			List<Integer> students = studentDao.getStudentsInt();
			
			//Create List of Users
			
			for (int i = 0; i < students.size(); i++) {
				User e = new User(students.get(i), User.Type.STUDENT,names.get(i));
				put(e.getUserId(),e);
			}
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
