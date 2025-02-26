package com.codewithkeerthi.fullstackbackend.repository;

import com.codewithkeerthi.fullstackbackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}