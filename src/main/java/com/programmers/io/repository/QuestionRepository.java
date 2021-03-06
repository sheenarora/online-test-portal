package com.programmers.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.Question;

/**
 * Question repository.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {

	

}
