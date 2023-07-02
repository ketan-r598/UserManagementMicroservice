package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.User;
import com.project.model.UserCredentials;
import com.project.service.TokenGeneratorService;
import com.project.service.UserService;
import com.project.session.Session;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private TokenGeneratorService tokenGenerator;

	@GetMapping("getAll")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@GetMapping("getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		Optional<User> u = service.findById(id);
		
		if(u.isPresent()) {
			return ResponseEntity.ok(u.get());
		} else {
//			throw new Exception("User does not exist" + id);
			return ResponseEntity.ofNullable(null);
		}
//		return u.get();
	}
	@PostMapping("register")
	public User addUser(@RequestBody User user) {
		return service.addUser(user);
	}

//	@PostMapping("login")
//	public Map<String, String> userLogin(@RequestBody UserCredentials user) {
		@PostMapping("login")
		public HashMap<String, String> userLogin(@RequestBody UserCredentials user) {
		Optional<User> u = service.findUserByEmailAndPassword(user.getEmail(), user.getPassword());

		if (u.isPresent()) {
			System.out.println("Login Successful");
			final UserCredentials user1 = this.service.authenticateUser(user.getEmail(), user.getPassword());
			
			Session session = Session.getSession();
			session.setUserId(u.get().getId());
			session.setEmail(user1.getEmail());
			session.setPassword(user1.getPassword());
			session.setRole(u.get().getRole());
			
			HashMap<String, String> retVal = new HashMap<String,String>();
//			this.tokenGenerator.generateToken(session);
//			System.out.println(this.tokenGenerator.generateToken(session).get("token"));
			retVal.put("token", this.tokenGenerator.generateToken(session).get("token"));
//			System.out.println(u.get());
			retVal.put("userId",Integer.toString(u.get().getId()));
			retVal.put("userEmail", u.get().getEmail());
			retVal.put("password", u.get().getPassword());
			retVal.put("role", u.get().getRole());
//			System.out.println(retVal);
			return retVal;
		}
		// TODO
		// throw error
		return null;
	}

	@PutMapping("update")
	public ResponseEntity<Map<String, Boolean>> updateUser(@RequestBody User user) {
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
		Map<String, Boolean> response = new HashMap<>();
		response.put("updated",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("delete")
	public ResponseEntity<Map<String, Boolean>> deleteUser() {
		service.deleteUser();
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}