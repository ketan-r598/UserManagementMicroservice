package com.project.service;

import java.util.Map;

import com.project.session.Session;

public interface TokenGeneratorService {
	Map<String,String> generateToken(Session user);

}
