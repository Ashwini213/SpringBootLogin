package com.bridgelabz.fundoonoteapp.user.service;

import com.bridgelabz.fundoonoteapp.user.model.User;

public interface UserService {
	public String login(User user);

	public User userReg(User user);
}
