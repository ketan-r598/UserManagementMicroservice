package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.*;
import com.project.model.User;
import com.project.model.UserInfo;
import com.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public void addUser(User user) {
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		Optional<User> temp_user = repo.findByEmailAndPassword(email, password);
		
		if(temp_user.isPresent()) {
			// TODO
			// Add Exception
		}
		
		repo.save(user);
		
		System.out.println("-------------------------------");
		System.out.println("+   User Saved Successfully   +");
		System.out.println("-------------------------------");
	}

	@Override
	public void updateUser(UserInfo user) {
		
		String email = user.getEmail();
		String password = "";
		String role = "";
		
		Optional<User> temp_user = repo.findByEmail(email);
		
		if(temp_user.isPresent()) {
			User u = new User(email, password, user.getFirstName(), user.getLastName(), user.getContactNumber(), user.getAddress(), role);
			repo.save(u);
			System.out.println("---------------------------------");
			System.out.println("+   User Updated Successfully   +");
			System.out.println("---------------------------------");
		}
		
		//TODO
		// throw Exception
	}

	@Override
	public void deleteUser(String email, String password) {
		repo.deleteByEmailAndPassword(email, password);
		System.out.println("---------------------------------");
		System.out.println("+   User Deleted Successfully   +");
		System.out.println("---------------------------------");
	}

	@Override
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<User> findUserByEmailAndPassword(String Email, String Password) {
		return repo.findByEmailAndPassword(Email, Password);
	}
	
	@Override
	public UserCredentials authenticateUser(String email, String password) {
		final Optional<User> optionalUser = this.repo.findByEmailAndPassword(email, password);
		final UserCredentials u = new UserCredentials(email, password);
		if(optionalUser.isEmpty()) {
			// throw user not found exception
		}
		return u;
	}
}
