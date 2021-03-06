package com.programmers.io.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.Constant;
import com.programmers.io.common.CustomException;
import com.programmers.io.entities.User;
import com.programmers.io.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/register")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<Object> save(@RequestBody User user) {
		LOGGER.info("API call: /register for request :" + user);
		StatusBean status = new StatusBean();
		ResponseEntity<Object> responseEntity = null;
		try{

			status = userService.validateUser(user);
			if(status.getCode()==HttpStatus.OK){
				if (userService.save(user)) {
					status.setCode(HttpStatus.OK);
					status.setMessage(Constant.SUCCESS_MESSAGE);
					LOGGER.info("User:" + user.getEmailId() +" succesfully registered.");
					
				} else {
					throw new Exception("User:" + user.getEmailId() +" registration failed.") ;
				}
			}
		}
		catch (CustomException e) {
			status.setCode(HttpStatus.FORBIDDEN);
			status.setMessage(e.getMessage());
			LOGGER.error(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			status.setMessage(e.getMessage());
			LOGGER.error(e.getMessage());
		}
		responseEntity = new ResponseEntity<>(status, status.getCode());
		return responseEntity;
	}
}