package com.android.taobaoclient.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.android.taobaoclient.dao.IUserService;
import com.android.taobaoclient.model.User;

public class UserService implements IUserService {

	private Map<String, User> users = null;
	
	public UserService(){
		users = new HashMap<String, User>();
		users.put("admin", new User("admin", "admin"));
	}
	
	@Override
	public void add(User user) {
		users.put(user.getUserName(), user);
	}

	@Override
	public User getUserById(String id) {
		return users.get(id);
	}

}
