package com.programmers.io.service;

import com.programmers.io.bean.ExamBean;
import com.programmers.io.bean.LoginBean;
import com.programmers.io.bean.StatusBean;

public interface GetQuestionsService {
	
	StatusBean validateExamBean(ExamBean examBean)  throws Exception;

	ExamBean getQuestions(ExamBean examBean, String examId, String userId) throws Exception;


}
