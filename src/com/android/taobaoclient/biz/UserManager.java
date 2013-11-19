package com.android.taobaoclient.biz;

import com.android.taobaoclient.dao.IUserService;
import com.android.taobaoclient.dao.impl.UserService;
import com.android.taobaoclient.model.User;

public class UserManager {
	
	private IUserService daoIUserService;
	
	public UserManager(){
		daoIUserService = new UserService();
	}
	
	public void add(User user){
		
	}
	
	public User Login(String userName, String passWord){
		User user = daoIUserService.getUserById(userName);
		if(user != null){
			return user.getPassWord().equals(passWord) ? user : null;
		}
		
		return null;
	}
	
	public User Register(User user){
		try {
			daoIUserService.add(user);
			return user;
		} catch (Exception e) {
			return null;
		}
	}
}
