package com.programmers.io.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.Constant;
import com.programmers.io.entities.User;
import com.programmers.io.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Boolean save(User user) throws Exception {
		Boolean userExist = false;
		User userCheck = userRepository.findByEmailIdIgnoreCase(user.getEmailId());
		if (userCheck == null) {
			userRepository.save(user);
			userExist = true;
		}
		return userExist;
	}

	@Override
	public StatusBean validateUser(User user) throws Exception {
		StatusBean status = new StatusBean(HttpStatus.OK, "Valid Request");
		String emailId = user.getEmailId();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String gender = user.getGender();
		int age = user.getAge();
		String college = user.getCollege();
		String skills = user.getSkills();
		String degree = user.getDegree();
		int graduationYear = user.getGraduationYear();
		String mobileNumber = user.getMobileNumber();
		String country = user.getCountry();
		String state = user.getState();
		String city = user.getCity();
		double graduationPercentage = user.getGraduationPercentage();

		boolean isBadRequest = false;

		// firtsname
		if (firstName == null || firstName.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// lastname
		else if (lastName == null || lastName.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// gender
		else if (gender == null || gender.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// age
		else if (age == 0) {
			isBadRequest = true;
		}
		// college
		else if (college == null || college.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// skills
		else if (skills == null || skills.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// degree
		else if (degree == null || degree.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// graduation year
		else if (graduationYear == 0) {
			isBadRequest = true;
		}
		// mobile number
		else if (mobileNumber == null || mobileNumber.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// Country
		else if (country == null || country.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// State
		else if (state == null || state.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// City
		else if (city == null || city.equalsIgnoreCase("")) {
			isBadRequest = true;
		}
		// Graduation percentage
		else if (graduationPercentage == 0) {
			isBadRequest = true;
		}
		// email
		else if (emailId == null || emailId.equalsIgnoreCase("")) {
			isBadRequest = true;
		}

		// if isBadRequest is true then bad request otherwise check for
		// duplicacy of email
		if (isBadRequest == true) {
			status.setCode(HttpStatus.BAD_REQUEST);
			status.setMessage(Constant.BAD_REQUEST_MESSAGE + "Except middlename, all the fields are mandatory.");
		} else {
			User userExist = userRepository.findByEmailIdIgnoreCase(emailId);
			if (userExist != null) {
				status.setCode(HttpStatus.BAD_REQUEST);
				status.setMessage(Constant.DUPLICATE_EMAIL_MESSAGE);
			}
		}

		return status;
	}
}
