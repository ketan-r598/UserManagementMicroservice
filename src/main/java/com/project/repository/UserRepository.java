package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByEmailAndPassword(String email, String password);
	public void deleteByEmailAndPassword(String email, String password);
	public Optional<User> findByEmail(String email);
//	public void deleteByEmail(String email);
}