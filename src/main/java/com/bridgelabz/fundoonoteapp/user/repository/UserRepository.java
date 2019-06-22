package com.bridgelabz.fundoonoteapp.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonoteapp.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findById(int id);

	boolean deleteById(int verifiedUserId);

	User findByEmail(String email);

	public List<User> findAll();
	
	Optional<User> findUserById(int id);
	 

}