package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.model.UserCredentials;
import com.project.repository.UserRepository;
import com.project.session.Session;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public User addUser(User user) {
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		Optional<User> temp_user = repo.findByEmailAndPassword(email, password);
		
		if(temp_user.isPresent()) {
			// TODO
			// Add Exception
			return null;
		}
		
		repo.save(user);
		
		System.out.println("-------------------------------");
		System.out.println("+   User Saved Successfully   +");
		System.out.println("-------------------------------");
		return user;
	}

	@Override
	public void updateUser(User user) {
		Session session = Session.getSession();
		
		int id = session.getUserId();
//		String email = session.getEmail();
//		String password = session.getPassword();
//		String role = session.getRole();
		
//		Optional<User> temp_user = repo.findByEmail(email);
		Optional<User> temp_user = repo.findById(id);
		
		if(temp_user.isPresent()) {
//			User u = new User(id, email, password, user.getFirstName(), user.getLastName(), user.getContactNumber(), user.getAddress(), role);
			repo.save(temp_user.get());
			System.out.println("---------------------------------");
			System.out.println("+   User Updated Successfully   +");
			System.out.println("---------------------------------");
		}
		
		//TODO
		// throw Exception
	}

	@Override
	public void deleteUser() {
//		repo.deleteByEmailAndPassword(email, password);
		Session session = Session.getSession();
		repo.deleteById(session.getUserId());
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

	@Override
	public Optional<User> findById(int id) {
		Optional<User> u = repo.findById(id);
		
		if(u.isEmpty())
			return Optional.empty();
		
		return u;
	}
}
