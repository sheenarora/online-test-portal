package com.programmers.io.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.programmers.io.entities.Exam;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long> {

	public Optional<Exam> findById(Long id);
	public Exam findByPassword(String password);

}
