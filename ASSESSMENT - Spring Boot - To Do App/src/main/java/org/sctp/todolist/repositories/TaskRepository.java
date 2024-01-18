package org.sctp.todolist.repositories;

import org.sctp.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();

//    Optional<Task> findById(Integer id);
}

