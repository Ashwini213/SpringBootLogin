package com.bridgelabz.fundoonoteapp.user.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.User;
import com.bridgelabz.fundoonoteapp.user.service.UserService;

@RestController

public class LoginController {
	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/sendMail")
	public String sendMail(@RequestBody User user) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(user.getEmail());
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String geteUserByLogin(@RequestBody User user) {

		return userService.login(user);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, User user) {

		return userService.update(request, user);

	}
}