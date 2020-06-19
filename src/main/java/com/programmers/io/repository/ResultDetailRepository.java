package com.programmers.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.programmers.io.entities.Result;
import com.programmers.io.entities.ResultDetail;

@Repository
public interface ResultDetailRepository extends CrudRepository<ResultDetail, Long>{

}
