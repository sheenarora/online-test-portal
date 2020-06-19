package com.programmers.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long>{

}
