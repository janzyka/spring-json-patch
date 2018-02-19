package com.zyka.spring.test.repositories;

import com.zyka.spring.test.model.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Long> {
}
