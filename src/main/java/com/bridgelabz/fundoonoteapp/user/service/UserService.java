package com.bridgelabz.fundoonoteapp.user.service;

import com.bridgelabz.fundoonoteapp.user.model.User;


public interface UserService {
	public String login(User user);
	public  String generateToken();

	public User userReg(User user);
	public String securePassword(User user);
	public User saveUser(User user);
	public String verifyToken(String secretKey);

}
