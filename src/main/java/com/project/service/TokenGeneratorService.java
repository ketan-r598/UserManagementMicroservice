package com.project.service;

import java.util.Map;

import com.project.model.*;

public interface TokenGeneratorService {
	Map<String,String> generateToken(UserCredentials user);

}
