package com.programmers.io.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.Exam;
import com.programmers.io.entities.QuestionCategory;
import com.programmers.io.entities.Section;

/**
 * Section repository.
 *
 */
@Repository
public interface SectionRepository extends CrudRepository<Section, Long>{

	public Optional<Section> findById(Long id);
	
}
