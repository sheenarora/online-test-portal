package com.programmers.io.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmers.io.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	//
	// public void question(QuestionRequest questionRequest){
	//
	// QuestionCategory abc =
	// questionRepository.findByQuestionCategoryName(questionRequest.getQuestionCategory());
	//
	// }

	// @Autowired
	// private QuestionRepository questionRepository;
	//
	// @Override
	// public long getCategoryId(Long questionId) {
	// Question question = questionRepository.findById(questionId).get();
	// Category category = question.getCategory();
	// long c = category.getId();
	// return c;
	// }
}
