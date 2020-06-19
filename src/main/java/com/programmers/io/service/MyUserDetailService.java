//package com.programmers.io.service;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
////import com.programmers.io.SaveRepository;
//
//import com.programmers.io.entities.User;
//import com.programmers.io.entities.UsersPrincipal;
//import com.programmers.io.repository.UserRepository;
//
//@Service
//@Transactional
//public class MyUserDetailService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userRepository.findByUsername(username);
//		if (user == null)
//			throw new UsernameNotFoundException("User not found");
//		return new UsersPrincipal(user);
//	}
//
//	public void saveMyUser(User user) {
//		try {
//			userRepository.save(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
