package com.programmers.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.programmers.io.entities.Options;

/**
 * Option repository.
 *
 */
@Repository
public interface OptionsRepository extends CrudRepository<Options, Long> {

}
