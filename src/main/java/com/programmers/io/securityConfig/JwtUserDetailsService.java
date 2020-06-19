package com.programmers.io.securityConfig;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.programmers.io.entities.User;
import com.programmers.io.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = new User();
		if (userRepository.findByEmailIdIgnoreCase(userName) != null) {
			user = userRepository.findByEmailIdIgnoreCase(userName);
			return new org.springframework.security.core.userdetails.User(user.getEmailId(), "" , new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}

}
