package com.programmers.io.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.Question;

/**
 * Question repository Custom.
 */
public interface QuestionRepositoryCustom{

	public List<Question> findRandomQuestions(Long categoryId, Long sectionId, int questionLimit);

}