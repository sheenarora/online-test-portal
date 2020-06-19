package com.programmers.io.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.programmers.io.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByEmailIdIgnoreCase(String emailId);

	public User findByFirstName(String username);

	public Optional<User> findById(Long userId);
	
}
