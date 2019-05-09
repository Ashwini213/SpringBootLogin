package com.bridgelabz.fundoonoteapp.user.service.implementation;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonoteapp.user.model.User;
import com.bridgelabz.fundoonoteapp.user.repository.UserRepository;
import com.bridgelabz.fundoonoteapp.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public String login(User user) {

		List<User> list = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		System.out.println("SIZE : " + list.size());

		if (list.size() > 0 && list != null) {
			System.out.println("Sucessfull login");

			return "Welcome " + list.get(0).getName();
		} else {
			System.out.println("wrong emailid or password");
			return "wrong emailid or password";
		}
	}

	@Override
	public User userReg(User user) {
		return userRepository.save(user);
	}

	private String createJWT(String password) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(password).setIssuedAt(now).signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	private void parseJWT(String jwt) {

		// This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
				.parseClaimsJws(jwt).getBody();
		System.out.println("password: " + claims.getId());

	}
}
