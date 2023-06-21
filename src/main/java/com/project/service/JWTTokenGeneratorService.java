package com.project.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.model.UserCredentials;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenGeneratorService implements TokenGeneratorService  {
	
	@Value("${jwt.secret.key}") // to make available through out the application
	private String secretKey;

	@Override
	public Map<String, String> generateToken(UserCredentials user) {
		String token = Jwts.builder().setSubject(user.getEmail())
		.setIssuer("ContactAppIssuer")
		.setIssuedAt(new Date())
		.signWith(SignatureAlgorithm.HS256, secretKey)
		.compact();
		return Map.of("token",token);
	}

}
