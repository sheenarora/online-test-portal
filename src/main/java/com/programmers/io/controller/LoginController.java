package com.programmers.io.controller;

import java.util.Map;

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

import com.programmers.io.bean.LoginBean;
import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.Constant;
import com.programmers.io.common.CustomException;
import com.programmers.io.securityConfig.JwtTokenUtil;
import com.programmers.io.service.LoginService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<Object> login(@RequestBody LoginBean loginBean) {
		LOGGER.info("API call: /login for request :" + loginBean);

		StatusBean status = new StatusBean();
		ResponseEntity<Object> responseEntity = null;
		System.out.println("Login : " + loginBean);

		String emailId = loginBean.getEmailId();
		String password = loginBean.getPassword();

		try {
			status = loginService.validateloginBean(emailId, password);
			if (status.getCode() == HttpStatus.OK) {
				Map<String, String> ClaimsMap = loginService.login(loginBean);
				String examId = ClaimsMap.get("ExamId");
				String userId = ClaimsMap.get("UserId");

				final String token = jwtTokenUtil.generateToken(emailId, userId, examId);
				status.setMessage(token);
				
			}
		} catch (CustomException e) {
			status.setCode(HttpStatus.FORBIDDEN);
			status.setMessage(e.getMessage());
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			status.setMessage(e.getMessage());
			LOGGER.error(e.getMessage());
		}
		responseEntity = new ResponseEntity<>(status, status.getCode());
		return responseEntity;
	}

}
