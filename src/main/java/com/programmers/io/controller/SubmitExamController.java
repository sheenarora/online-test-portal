package com.programmers.io.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.io.bean.ExamBean;
import com.programmers.io.bean.ResultBean;
import com.programmers.io.bean.StatusBean;
import com.programmers.io.common.AppUtils;
import com.programmers.io.common.Constant;
import com.programmers.io.common.CustomException;
import com.programmers.io.service.LoginService;
import com.programmers.io.service.ResultService;

import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/submit")
public class SubmitExamController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubmitExamController.class);
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private ResultService resultService;

	@PostMapping
	@CrossOrigin(origins = "*")
	public ResultBean submitExam(@RequestBody ExamBean examBean, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("API call: /submit for user and exam :" + examBean.getUserName() +" and "+ examBean.getExamName());

		StatusBean status = new StatusBean();
		ResultBean resultBean = new ResultBean();
		List<ResultBean> resultResponseList = new ArrayList<ResultBean>();
		
		try{
			Claims claims = AppUtils.fetchClaimsFromToken(request);
			String examId = (String) claims.get("ExamId");
			String userId = (String) claims.get("UserId");
			status = resultService.validateSubmitRequest(examBean, examId, userId);
			if (status.getCode() == 200) {
				resultResponseList = resultService.calculateResult(examBean, examId, userId);
				resultBean.setResultResponseList(resultResponseList);
				status = new StatusBean(Constant.SUCCESS_CODE, Constant.SUCCESS_MESSAGE);
			}
		}catch (CustomException e) {
			e.printStackTrace();
			status.setCode(Constant.INTERNAL_SERVER_ERROR_CODE);
			status.setMessage(e.getMessage());
			LOGGER.error(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(Constant.INTERNAL_SERVER_ERROR_CODE);
			status.setMessage(e.getMessage());
			LOGGER.error(e.getMessage());
		}
		resultBean.setStatus(status);
		return resultBean;
	}

}
