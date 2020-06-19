package com.programmers.io.service;

import com.programmers.io.bean.StatusBean;
import com.programmers.io.entities.User;

public interface UserService {

	/**
	 * keshav
	 * @param user
	 * @return
	 */
	Boolean save(User user) throws Exception;

	StatusBean validateUser(User user) throws Exception;
	

	
}
