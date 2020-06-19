package com.programmers.io.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.programmers.io.entities.Exam;
import com.programmers.io.entities.ExamDetail;

@Repository
public interface ExamDetailRepository extends CrudRepository<ExamDetail, Long> {

	//public List<ExamDetail> findAllByExam(Exam exam);
	public Optional<ExamDetail> findById(Long id);

}
