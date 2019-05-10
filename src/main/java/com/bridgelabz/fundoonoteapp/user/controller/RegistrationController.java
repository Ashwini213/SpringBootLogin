package com.bridgelabz.fundoonoteapp.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonoteapp.user.model.User;
import com.bridgelabz.fundoonoteapp.user.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	UserService userService;
	
	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 * 
	 * @Autowired private UserDetailsManager userDetailsManager;
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public User createStudent(@RequestBody User user) {
	
		return userService.userReg(user);
	}
	/*
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegister(@ModelAttribute("user") User userRegistrationObject) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		String encodedPassword = bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());

		User user = new User(userRegistrationObject.getName(), encodedPassword, authorities);
		jdbcUserDetailsManager.createUser(user);
		return new ModelAndView("redirect:/login");
	}*/

//	@RequestMapping("/registration")
//	public String test(Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		return "registration";
//	}

	/*
	 * @PostMapping(value = "/registration") // @RequestMapping(value =
	 * "/registration", method = RequestMethod.POST) public String
	 * testPost(@Valid @ModelAttribute("user") User user, BindingResult result,
	 * Model model) { if (result.hasErrors()) { return "registerSuccess";
	 * 
	 * } String hashedPassword = passwordEncoder.encode(user.getPassword());
	 * 
	 * Collection<? extends GrantedAuthority> roles = Arrays.asList(new
	 * SimpleGrantedAuthority("ROLE_USER"));
	 * 
	 * UserDetails userDetails = new User(user.getName(), hashedPassword, roles);
	 * userDetailsManager.createUser(userDetails); return "registerSuccess";
	 * 
	 * }
	 */
}