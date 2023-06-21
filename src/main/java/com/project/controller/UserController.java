package com.project.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.User;
import com.project.model.UserCredentials;
import com.project.model.UserInfo;
import com.project.service.TokenGeneratorService;
import com.project.service.UserService;
import com.project.session.Session;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private TokenGeneratorService tokenGenerator;

	@GetMapping("/users/getAll")
	public List<User> getAll() {
		return service.getAll();
	}

	@PostMapping("/users/register")
	public void addUser(@RequestBody User user) {
		service.addUser(user);
	}

	@PostMapping("/users/login")
	public Map<String, String> userLogin(@RequestBody UserCredentials user) {

		Optional<User> u = service.findUserByEmailAndPassword(user.getEmail(), user.getPassword());

		if (u.isPresent()) {
			System.out.println("Login Successful");
			final UserCredentials user1 = this.service.authenticateUser(user.getEmail(), user.getPassword());
			
			Session session = Session.getSession();
			session.setUserId(u.get().getId());
			session.setEmail(user1.getEmail());
			session.setPassword(user1.getPassword());
			session.setRole(u.get().getRole());
			
			return this.tokenGenerator.generateToken(session);
		}
		// TODO
		// throw error
		return null;
	}

	@PutMapping("/users/update")
	public void updateUser(@RequestBody UserInfo user) {
//		Session session = Session.getSession();
//		User u = service.findById(session.getUserId()).get();
//		
//		u.setId(session.getUserId());
//		u.setEmail(user.getEmail());
//		u.setPassword(session.getPassword());
//		u.setFirstName(user.getFirstName());
//		u.setLastName(user.getLastName());
//		u.setContactNumber(user.getContactNumber());
//		u.setAddress(user.getAddress());
//		u.setRole(session.getRole());
		
		service.updateUser(user);
	}

	@DeleteMapping("/users/delete")
	public void deleteUser() {
		service.deleteUser();
	}
}