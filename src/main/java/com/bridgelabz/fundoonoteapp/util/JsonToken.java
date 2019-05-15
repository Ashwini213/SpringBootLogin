package com.bridgelabz.fundoonoteapp.util;

	public interface JsonToken {

		public String jwtToken(String secretKey, int id);

		public int tokenVerification(String token);
	}

