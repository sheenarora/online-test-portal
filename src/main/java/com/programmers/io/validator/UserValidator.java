//package com.programmers.io.validator;
//
//import javax.validation.Validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//
//import com.programmers.io.entities.User;
//import com.programmers.io.service.UserService;
//
//@Component
//public abstract class UserValidator implements Validator{
//	
//	@Autowired
//	private UserService userService;
//	
//	public boolean supports(Class<?> arg0) {
//		return User.class.equals(arg0);
//	}
//	
//	public void Validate(Object object, Errors errors) {
//		User user = (User) object;
//		if(userService.findByAdharNumber(user.getAdharNumber()) != null) {
//			errors.rejectValue("adhar", "User.adhar.exists");
//		}
//	}
//
//	
//}
