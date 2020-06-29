package com.programmers.io.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.QuestionCategory;

/**
 * Category repository.
 *
 */
@Repository
public interface QuestionCategoryRepository extends CrudRepository<QuestionCategory, Long> {

	public Optional<QuestionCategory> findById(Long id);

}
