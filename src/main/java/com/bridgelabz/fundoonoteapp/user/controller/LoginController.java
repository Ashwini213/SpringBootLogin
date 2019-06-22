package com.bridgelabz.fundoonoteapp.user.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.LoginRequest;
import com.bridgelabz.fundoonoteapp.user.model.User;
import com.bridgelabz.fundoonoteapp.user.service.CustomException;
import com.bridgelabz.fundoonoteapp.user.service.UserService;
import com.bridgelabz.fundoonoteapp.util.Util;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSender mailSender;


	// SEND EMAIL
	@RequestMapping("/sendMail")
	public String sendMail(@RequestBody User user) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(user.getEmail());
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		mailSender.send(message);
		return "Mail Sent Success!";
	}

	@PostMapping(value = "/login")
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> Login(@RequestBody LoginRequest Login, HttpServletRequest request,
			HttpServletResponse response) {

		String token = userService.login(Login);

		if (token != null) {
			System.out.println("hmmm->" + token);
			response.setHeader("token", token);
			response.addHeader("Access-control-Allow-Headers", "*");
			response.addHeader("Access-control-Expose-Headers", "*");

			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<String>("inavlid details", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/user")
	// @RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user, HttpServletRequest request) throws CustomException {
		System.out.println("I am  token at update method :" + request.getHeader("token"));
		User result = userService.update(request.getHeader("token"), user);
		if (result != null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);
		} else
			return new ResponseEntity<User>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(value = "/user")
	// @RequestMapping(value = "/deleteuser", method = RequestMethod.DELETE)
	public void deleteUser(HttpServletRequest request) {

		System.out.println("I am  token at delete method :" + request.getHeader("token"));
		boolean b = userService.delete(request.getHeader("token"));
		System.out.println("-->" + b);

	}

	@PostMapping(value = "/forgotPassword")
	String forgotPassword(@RequestBody LoginRequest login, HttpServletRequest request) {
		User userInfo = userService.getUserByEmail(login.getEmail());
		System.out.println(userInfo);
		if (userInfo != null) {
			String token = Util.jwtToken("secretKey", userInfo.getId());

			StringBuffer requestUrl = request.getRequestURL();
			System.out.println(requestUrl);
			String forgotPasswordUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/"));
			forgotPasswordUrl = forgotPasswordUrl + "/resetpassword/" + "token=" + token;
			System.out.println(forgotPasswordUrl);
			String subject = "FOR FORGOT PASSWORD";

			userService.sendMail(userInfo, forgotPasswordUrl, subject);
			return "Mail Sent Successfully";
		} else
			return "not sent";
	}

	// @PostMapping(value = "/forgotPassword")
	// String forgotPassword(@RequestBody LoginRequest login, HttpServletRequest
	// request) {
	// Optional<LoginRequest> maybeUser =
	// userService.getUserByEmail(login.getEmailId());
	// if (maybeUser != null) {
	// String token = Util.jwtToken("secretKey", maybeUser.get());
	//
	// StringBuffer requestUrl = request.getRequestURL();
	// System.out.println(requestUrl);
	// String forgotPasswordUrl = requestUrl.substring(0,
	// requestUrl.lastIndexOf("/"));
	// forgotPasswordUrl = forgotPasswordUrl + "/resetpassword/" + "token=" + token;
	// System.out.println(forgotPasswordUrl);
	// String subject = "FOR FORGOT PASSWORD";
	//
	// userService.sendMail(maybeUser, forgotPasswordUrl, subject);
	// return "Mail Sent Successfully";
	// } else
	// return "not sent";
	// }

	@PutMapping(value = "/resetPassword")
	// @RequestMapping(value = "/resetpassword", method = RequestMethod.PUT)
	public void resetPassword(@RequestBody User user, HttpServletRequest request) throws CustomException {
		int id = Util.tokenVerification(request.getHeader("token"));

		if (id != 0) {

			Optional<User> userinfo = userService.findById(id);
			userinfo.isPresent();
			User usr = userinfo.get();
			usr.setPassword(user.getPassword());
			userService.update(request.getHeader("token"), usr);

		}

	}

	@PutMapping(value = "/activeStatus")
	// @RequestMapping(value = "/activestatus", method = RequestMethod.PUT)
	public void activeStatus(HttpServletRequest request) throws CustomException {
		int id = Util.tokenVerification(request.getHeader("token"));

		if (id != 0) {

			Optional<User> userInfo = userService.findById(id);
			User usr = userInfo.get();
			usr.setActivestatus("1");
			userService.update(request.getHeader("token"), usr);
		}
	}

	@GetMapping(value = "/getDetails")
	// @RequestMapping(value = "/fetchDetails", method = RequestMethod.GET)
	public List<User> getUser(HttpServletRequest request) {

		return userService.getDetails();
	}

	@GetMapping("/get-all-user/{token}")
	public ResponseEntity<?> getAllUser(@PathVariable("token") String token, HttpServletRequest request,
			HttpServletResponse resp) {
		List<String> emailIds = userService.getAllUser(token);
		if (emailIds != null)
			return new ResponseEntity<List<String>>(emailIds, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Went wrong", HttpStatus.CONFLICT);
	}

	@GetMapping("/get-coll-user/{emaiId}")
	public ResponseEntity<?> getCollabUser(@PathVariable("emaiId") String emaiId, @RequestHeader("token") String token,
			HttpServletRequest request, HttpServletResponse resp) {
		User coUser = userService.getUserByEmail(emaiId);
		if (coUser != null) {
			System.out.println(coUser);

			return new ResponseEntity<User>(coUser, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Went wrong", HttpStatus.CONFLICT);
	}

	@GetMapping("/get-user-email/{token}")
	public ResponseEntity<?> getCoUser(@PathVariable("token") String token, @RequestParam("coUserId") int coUserId,
			HttpServletRequest request, HttpServletResponse resp) {
		if (token != null) {
			User loggedInUser = userService.getCoUserEmailId(coUserId);
			if (loggedInUser != null) {
				return new ResponseEntity<User>(loggedInUser, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Went wrong", HttpStatus.CONFLICT);
	}

//	@GetMapping("/getemails")
//	public List<String> getEmails() {
//
//		return userService.getEmails();
//	}
}