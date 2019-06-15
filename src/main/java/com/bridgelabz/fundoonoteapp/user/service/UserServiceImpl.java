package com.bridgelabz.fundoonoteapp.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.Collaborator;
import com.bridgelabz.fundoonoteapp.user.model.LoginRequest;
import com.bridgelabz.fundoonoteapp.user.model.User;
import com.bridgelabz.fundoonoteapp.user.repository.CollaboratorRepo;
import com.bridgelabz.fundoonoteapp.user.repository.UserRepository;
import com.bridgelabz.fundoonoteapp.util.Util;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	public UserRepository userRep;
	
	@Autowired
	private CollaboratorRepo colRepo;

	@Autowired
	private JavaMailSender mailSender;

	String secretKey;
	String subject;

	@Override
	public String login(LoginRequest login) {
		String password = Util.encryptedPassword(login.getPassword());
		Optional<User> userList = userRep.findByEmailAndPassword(login.getEmail(), password);
		System.out.println(userList);
		if (userList.isPresent()) {
			System.out.println("Sucessful login");
			return Util.jwtToken(password, userList.get().getId());
		} else
			System.out.println("wrong Id or password");
		return null;

	}

	@Override
	public User update(String token, User user) throws CustomException {
		int verifiedUserId = Util.tokenVerification(token);

		Optional<User> maybeUser = userRep.findById(verifiedUserId);
		User presentUser = maybeUser.map(existingUser -> {
			existingUser.setEmail(user.getEmail() != null ? user.getEmail() : maybeUser.get().getEmail());
			existingUser.setPhonenumber(
					user.getPhonenumber() != null ? user.getPhonenumber() : maybeUser.get().getPhonenumber());
			existingUser.setName(user.getName() != null ? user.getName() : maybeUser.get().getName());
			existingUser.setPassword(user.getPassword() != null ? Util.encryptedPassword(user.getPassword())
					: maybeUser.get().getPassword());
			return existingUser;
		}).orElseThrow(() -> new CustomException("User Not Found"));

		return userRep.save(presentUser);
	}

	@Override
	public boolean delete(String token) {
		int varifiedUserId = Util.tokenVerification(token);
		Optional<User> maybeUser = userRep.findById(varifiedUserId);
		return maybeUser.map(existingUser -> {
			userRep.delete(existingUser);
			return true;
		}).orElseGet(() -> false);
	}

	@Override
	public User userRegistration(User user, HttpServletRequest request) {
		user.setPassword(Util.encryptedPassword(user.getPassword()));
		User newUser = userRep.save(user);
		// Optional<User> newUser = userRep.findById(user.getId());
		if (newUser != null) {
			System.out.println("Sucessfull reg");
			// Optional<User> maybeUser = userRep.findById(user.getId());
			String tokenGen = Util.jwtToken("secretKey", newUser.getId());
			User existingUser = newUser;
			StringBuffer requestUrl = request.getRequestURL();
			System.out.println(requestUrl);
			String activationUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/"));
			activationUrl = activationUrl + "/activestatus/" + "token=" + tokenGen;
			System.out.println(activationUrl);
			String subject = "User Activation";

			sendMail(existingUser, activationUrl, subject);
			// return "Mail Sent Successfully";
			return existingUser;

		} else {
			System.out.println("Not sucessful reg");
		}
		return null;
		// return user;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRep.findByEmail(email);

	}

	public String sendMail(User user, String urlPattern, String subject) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(user.getEmail());
			helper.setText(urlPattern);
			helper.setSubject(subject);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		mailSender.send(message);
		return "Mail Sent Success!";

	}

	public Optional<User> findById(int id) {
		return userRep.findById(id);

	}

	@Override
	public List<User> getDetails() {
		List<User> users = userRep.findAll();
		return users;
	}

	@Override
	public List<String> getAllUser(String token) {
		int userId=Util.tokenVerification(token);
		List<Collaborator> users = colRepo.findAllByOwnerId(userId);
		List<String> emailIds= new ArrayList<>();
		users.forEach(collaborator ->
		{
			emailIds.add((userRep.findById(collaborator.getAllocatedId())).get().getEmail());
		});
		return emailIds;
	}

	@Override
	public User getCoUserEmailId(int coUserId) {
		return userRep.findById(coUserId).get();
	}

}