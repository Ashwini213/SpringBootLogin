package com.bridgelabz.fundoonoteapp.user.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonoteapp.user.model.User;

public interface UserService {
	public String login(User user);

	public String generateToken();

	public User userReg(User user);

	public String securePassword(User user);

	public String verifyToken(String secretKey);

	public String addUser(User user);
	public String update(HttpServletRequest request, User user);

}
