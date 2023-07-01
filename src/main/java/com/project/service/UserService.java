package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.model.User;
import com.project.model.UserCredentials;

public interface UserService {

	public List<User> getAll();
	public Optional<User> findUserByEmailAndPassword(String Email, String Password);
	public UserCredentials authenticateUser(String email,String password);
	public User addUser(User user);
	public void updateUser(User user);
	public void deleteUser();
	
//	public void deleteUser(String email, String password);
	public Optional<User> findById(int id);
//	public void deleteUser(String email);
}