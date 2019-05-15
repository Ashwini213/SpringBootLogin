package com.bridgelabz.fundoonoteapp.user.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonoteapp.user.model.User;

public interface UserService {
	public String login(User user);

	public User update(String token, User user);

	public User userRegistration(User user, HttpServletRequest request);

	public String encryptedPassword(User user);

	public boolean delete(String token);

	public User getUserInfoByEmail(String email);

	public String sendMail(User user, String url, String subject);

	public Optional<User> findById(int id);

}