package com.programmers.io.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.io.entities.Question;

/**
 * Question repository.
 */

@Repository
@Transactional(readOnly = true)
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Find random question list by category id and limit.
	 *
	 * @param categoryId
	 * @return List<Question>
	 */
	 @Override
	public List<Question> findRandomQuestions(Long categoryId, Long sectionId, int questionLimit){
		 String query = "SELECT q FROM Question q WHERE q.questionCategory.id = " + categoryId + " and q.section.id = " + sectionId +  " ORDER BY function('RAND')";
		 return entityManager.createQuery(query, Question.class).setMaxResults(questionLimit).getResultList();
	 }

	
}
