package com.zyka.spring.test.repositories;

import com.zyka.spring.test.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
