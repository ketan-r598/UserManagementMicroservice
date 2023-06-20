package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.model.User;
import com.project.model.UserInfo;

public interface UserService {

	public List<User> getAll();
	public Optional<User> findUserByEmailAndPassword(String Email, String Password);
	public void addUser(User user);
	public void updateUser(UserInfo user);
	public void deleteUser(String email, String password);
}