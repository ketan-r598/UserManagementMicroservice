package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import com.project.model.User;
import com.project.model.UserCredentials;
import com.project.model.UserInfo;
import com.project.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users/getAll")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/users/register")
	public void addUser(@RequestBody User user) {
		service.addUser(user);
	}
	
	@PostMapping("/users/login")
	public void userLogin(@RequestBody UserCredentials user) {
		
		Optional<User> u = service.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if(u.isPresent()) {
			System.out.println("Login Successful");
		}
		
		// TODO
		// throw error
	}
	
	@PutMapping("/users/update")
	public void updateUser(@RequestBody UserInfo user) {
		service.updateUser(user);
	}
	
	@DeleteMapping("/users/delete/{email}/{password}")
	public void deleteUser(@PathVariable String email, @PathVariable String password) {
		service.deleteUser(email, password);
	}
}