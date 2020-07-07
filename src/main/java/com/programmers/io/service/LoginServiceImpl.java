package com.programmers.io.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.programmers.io.bean.LoginBean;
import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.Constant;
import com.programmers.io.entities.Exam;
import com.programmers.io.entities.User;
import com.programmers.io.repository.ExamDetailRepository;
import com.programmers.io.repository.ExamRepository;
import com.programmers.io.repository.QuestionRepository;
import com.programmers.io.repository.ResultRepository;
import com.programmers.io.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ExamRepository examRepository;

	@Autowired
	public ExamDetailRepository examDetailRepository;

	@Autowired
	public QuestionRepository questionRepository;
	
	@Autowired
	public ResultRepository resultRepository;

	public Map<String, String> login(LoginBean loginBean) throws Exception {

		Map<String, String> ClaimsMap = new HashMap<String, String>();

		// Exam
		String password = loginBean.getPassword();
		Exam exam = examRepository.findByPassword(password);
		ClaimsMap.put("ExamId", String.valueOf(exam.getId()));

		// user
		String emailId = loginBean.getEmailId();
		User user = userRepository.findByEmailIdIgnoreCase(emailId);
		ClaimsMap.put("UserId", String.valueOf(user.getId()));

		return ClaimsMap;
	}

	public StatusBean validateloginBean(String emailId, String password) throws Exception {
		StatusBean status = new StatusBean(HttpStatus.OK, "Valid Request");

		Exam exam = examRepository.findByPassword(password);
		if (exam == null) {
			status.setCode(HttpStatus.BAD_REQUEST);
			status.setMessage(Constant.INCORRECT_PASSWORD_MESSAGE);
		} else if (exam.getTimestamp() != null && exam.getExpiryHours()!= 0) {
			Date validDate = addHoursToJavaUtilDate(exam.getTimestamp(), exam.getExpiryHours());
			Date currentDate = new Date();
			System.out.println("currentDate " + currentDate.toString());
			System.out.println("validDate" + validDate.toString());
			if (currentDate.after(validDate)) {
				status.setCode(HttpStatus.BAD_REQUEST);
				status.setMessage(Constant.EXAM_EXPIRED_MESSAGE);
			}
		}
				

		User user = userRepository.findByEmailIdIgnoreCase(emailId);
		if (user == null) {
			status.setCode(HttpStatus.BAD_REQUEST);
			status.setMessage(Constant.INVALID_USER_ID_MESSAGE);
		}
		
		if(resultRepository.findByUserIdAndExamId(user.getId(), exam.getId()).isPresent()){
			status.setCode(HttpStatus.BAD_REQUEST);
			status.setMessage(Constant.USER_ALREADY_TAKEN_EXAM);
		}

		return status;
	}

	public Date addHoursToJavaUtilDate(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

}
