package com.programmers.io.repository;

import java.util.List;

import com.programmers.io.entities.Question;

/**
 * Question repository Custom.
 */
public interface QuestionRepositoryCustom{

	public List<Question> findRandomQuestions(Long categoryId, Long sectionId, int questionLimit);

}
