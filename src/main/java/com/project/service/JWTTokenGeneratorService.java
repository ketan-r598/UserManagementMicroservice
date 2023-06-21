package com.project.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.session.Session;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenGeneratorService implements TokenGeneratorService  {
	
	@Value("${jwt.secret.key}") // to make available through out the application
	private String secretKey;

	@Override
	public Map<String, String> generateToken(Session user) {
		String token = Jwts
				.builder()
				.setSubject(user.getEmail())
				.claim("userId", user.getUserId())
				.claim("password", user.getPassword())
				.claim("role",user.getRole())
				.setIssuer("UserMicroservice")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
		
		System.out.println();
		System.out.println("userID = "+ user.getUserId());
		System.out.println("userEmail = "+user.getEmail());
		System.out.println("userPassword = "+user.getPassword());
		System.out.println("userRole = "+user.getRole());
		System.out.println();
		
		return Map.of("token",token);
	}
}