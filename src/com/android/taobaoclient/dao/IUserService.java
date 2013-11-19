package com.android.taobaoclient.dao;

import com.android.taobaoclient.model.User;

public interface IUserService {
	
	public void add(User user);
	
	public User getUserById(String id);
}
