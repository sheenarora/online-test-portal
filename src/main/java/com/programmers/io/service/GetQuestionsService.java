package com.programmers.io.service;

import com.programmers.io.bean.ExamBean;

public interface GetQuestionsService {

	ExamBean getQuestions(String examId, String userId) throws Exception;

}
