package com.bridgelabz.fundoonoteapp.user.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.fundoonoteapp.user.model.LoginRequest;
import com.bridgelabz.fundoonoteapp.user.model.User;

public interface UserService {
	public String login(LoginRequest login);

	public User update(String token, User user) throws CustomException;

	public User userRegistration(User user, HttpServletRequest request);

	public boolean delete(String token);

	public User getUserByEmail(String email);

	public String sendMail(User user, String url, String subject);

	public Optional<User> findById(int id);

	public List<User> getDetails();

	public List<String> getAllUser(String token);

	public User getCoUserEmailId(int coUserId);

	public List<String> getEmails();

}