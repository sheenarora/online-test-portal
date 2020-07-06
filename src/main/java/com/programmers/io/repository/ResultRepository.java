package com.programmers.io.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {

	public Optional<Result> findByUserIdAndExamId(long userId, long examId);
}
