package com.bridgelabz.fundoonoteapp.user.service.implementation;

import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import com.bridgelabz.fundoonoteapp.user.model.User;
import com.bridgelabz.fundoonoteapp.user.repository.UserRepository;
import com.bridgelabz.fundoonoteapp.user.service.UserService;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

	@Autowired
	public UserRepository userRepository;

	public String login(User user) {

		String password = securePassword(user);
		System.out.println("------>" + password);
		System.out.println("**********" + user.getPassword());

		List<User> list = userRepository.findByEmailAndPassword(user.getEmail(), password);
		System.out.println("SIZE : " + list.size());

		if (list.size() != 0) {
			System.out.println("Sucessfull login");

			return "Welcome " + list.get(0).getName() + "Jwt--->" + generateToken();
		} else {
			System.out.println("wrong emailid or password");
			return "wrong email id or password";
		}
	}

	String token = generateToken();
	String output = verifyToken(token);

	@Override
	public User userReg(User user) {
		user.setPassword(securePassword(user));
		userRepository.save(user);
		return user;

	}

	public String securePassword(User user) {
		String password = user.getPassword();
		String generatedPassword = null;
		// String password = user.getPassword();
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String hashedPassword = passwordEncoder.encode(password);
		//
		// System.out.println(user.getPassword());
		//
		// return user.getPassword();
		// }
		// public String securePassword(User user) {
		// String password = user.getPassword();
		//
		// SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
		//
		// String encodedPassword = encoder.encode(password);
		// System.out.println(encodedPassword);
		// return encodedPassword;
		// }
		try {

			// Static getInstance method is called with hashing SHA
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// digest() method called
			// to calculate message digest of an input
			// and return array of byte
			// byte[] messageDigest = md.digest();

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, bytes);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			System.out.println(hashtext);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown" + " for incorrect algorithm: " + e);

			return null;
		}
	}

	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
	private static final byte[] secretBytes = secret.getEncoded();
	private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public String generateToken() {
		String id = UUID.randomUUID().toString().replace("-", "");
		Date now = new Date();
		Date exp = new Date(System.currentTimeMillis() + (1000 * 30)); // 30 seconds

		String token = Jwts.builder().setId(id).setIssuedAt(now).setNotBefore(now).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();

		return token;
	}

	public String verifyToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
		System.out.println("----------------------------");
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
		return token;
	}

	// public String jwtToken(String secretKey, String subject) {
	//
	// long nowMillis = System.currentTimeMillis();
	// Date now = new Date(nowMillis);
	//
	// JwtBuilder builder =
	// Jwts.builder().setSubject(subject).setIssuedAt(now).signWith(SignatureAlgorithm.HS256,
	// secretKey);
	//
	// return builder.compact();
	// }
	//
	// private static final Key secret =
	// MacProvider.generateKey(SignatureAlgorithm.HS256);
	// private static final byte[] secretBytes = secret.getEncoded();
	//
	// private static final String base64SecretBytes =
	// Base64.getEncoder().encodeToString(secretBytes);
	//
	// public Claims verifyToken(String builder) {
	// Claims claims =
	// Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(builder).getBody();
	// System.out.println("----------------------------");
	// System.out.println("ID: " + claims.getId());
	// System.out.println("Subject: " + claims.getSubject());
	// System.out.println("Issuer: " + claims.getIssuer());
	// System.out.println("Expiration: " + claims.getExpiration());
	// return claims;
	//
	//
	// }

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}

// public class JJWTDemo {

// private static final String secret = "MySecrete";
//
// private String generateToken(){
// String id = UUID.randomUUID().toString().replace("-", "");
// Date now = new Date();
// Date exp = new Date(System.currentTimeMillis() + (1000*30)); // 30 seconds
//
// String token = Jwts.builder()
// .setId(id)
// .setIssuedAt(now)
// .setNotBefore(now)
// .setExpiration(exp)
// .signWith(SignatureAlgorithm.HS256, secret)
// .compact();
//
// return token;
// }

/*
 * private void verifyToken(String token){ Claims claims = Jwts.parser().
 * setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
 * .parseClaimsJws(token).getBody();
 * System.out.println("----------------------------"); System.out.println("ID: "
 * + claims.getId()); System.out.println("Subject: " + claims.getSubject());
 * System.out.println("Issuer: " + claims.getIssuer());
 * System.out.println("Expiration: " + claims.getExpiration()); }
 * 
 * public void main(String[] args) { System.out.println(generateToken()); String
 * token = generateToken(); verifyToken(token); } }
 */

// }

// private static final Key secret =
// MacProvider.generateKey(SignatureAlgorithm.HS256);
// private static final byte[] secretBytes = secret.getEncoded();
// private static final String base64SecretBytes =
// Base64.getEncoder().encodeToString(secretBytes);
//
//
// private String createJWT(String password) {
//
// // The JWT signature algorithm we will be using to sign the token
// SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
// long nowMillis = System.currentTimeMillis();
// Date now = new Date(nowMillis);
//
// // We will sign our JWT with our ApiKey secret
// //byte[] apiKeySecretBytes =
// DatatypeConverter.parseBase64Binary(apiKey.getSecret());
// //Key signingKey = new SecretKeySpec(apiKeySecretBytes,
// signatureAlgorithm.getJcaName());
//
// // Let's set the JWT Claims
// @SuppressWarnings("deprecation")
// JwtBuilder builder =
// Jwts.builder().setSubject(password).setIssuedAt(now).signWith(SignatureAlgorithm.HS256,
// base64SecretBytes);
//
// // if it has been specified, let's add the expiration
//
// // Builds the JWT and serializes it to a compact, URL-safe string
// return builder.compact();
// }
//
// private void parseJWT(String jwt) {
//
// // This line will throw an exception if it is not a signed JWS (as expected)
// Claims claims =
// Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
// .parseClaimsJws(jwt).getBody();
// System.out.println("password: " + claims.getId());
//
// }
// }
