package com.programmers.io.service;

import com.programmers.io.bean.ExamBean;
import com.programmers.io.bean.ResultBean;
import com.programmers.io.bean.StatusBean;
import com.programmers.io.entities.Result;

public interface ResultService {

	public StatusBean validateSubmitRequest(ExamBean examBean, String examId, String userId) throws Exception;

	public ResultBean calculateResult(ExamBean examBean, String examId, String userId) throws Exception;

	// Result saveResult(String userId, String examId);

	Result saveResult(Result result);
}
