package com.programmers.io.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.io.bean.ExamBean;
import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.AppUtils;
import com.programmers.io.common.Constant;
import com.programmers.io.common.CustomException;
import com.programmers.io.service.GetQuestionsService;

import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/getQuestions")
public class GetQuestionsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetQuestionsController.class);

	@Autowired
	private GetQuestionsService getQuestionsService;

	@GetMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getQuestions(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("API call: /getQuestions");

		StatusBean status = new StatusBean();
		ExamBean examBean = new ExamBean();

		try {
			Claims claims = AppUtils.fetchClaimsFromToken(request);
			String examId = (String) claims.get("ExamId");
			String userId = (String) claims.get("UserId");

			examBean = getQuestionsService.getQuestions(examId, userId);
			status.setCode(Constant.SUCCESS_CODE);
			status.setMessage(Constant.SUCCESS_MESSAGE);
			examBean.setStatus(status);

		} catch (CustomException e) {
			status.setCode(Constant.CUSTOM_ERROR_CODE);
			status.setMessage(e.getMessage());
			examBean = new ExamBean();
			examBean.setStatus(status);
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			status.setCode(Constant.INTERNAL_SERVER_ERROR_CODE);
			status.setMessage(e.getMessage());
			examBean = new ExamBean();
			examBean.setStatus(status);
			LOGGER.error(e.getMessage());
		}
		return ResponseEntity.ok(examBean);
	}

}
