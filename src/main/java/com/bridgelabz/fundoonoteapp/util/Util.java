package com.bridgelabz.fundoonoteapp.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class Util {
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
	private static final byte[] secretBytes = secret.getEncoded();
	private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public static String jwtToken(String secretKey, int id) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(id)).setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes);
		System.out.println("jwt token :" + builder.compact());
		String token = builder.compact();

		return token;
	}

	public static int tokenVerification(String token) {
		// This line will throw an exception if it is not a signed JWS (as expected)
		if (StringUtils.isEmpty(token)) {
		}
		Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
		System.out.println("ID******************: " + claims.getSubject());
		System.out.println("Id is varified :" + claims.getSubject());

		return Integer.parseInt(claims.getSubject());
	}

	public static String encryptedPassword(String password) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println("generated password :" + generatedPassword);

		return generatedPassword;

	}

}
