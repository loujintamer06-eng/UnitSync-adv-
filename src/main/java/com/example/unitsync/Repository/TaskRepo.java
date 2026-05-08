package com.example.unitsync.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Model.Task;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByWorkerId(Long workerId);
    List<Task> findByWorkerIdAndStatusNot(Long workerId, String status);
    List<Task> findByWorkerIdAndStatus(Long workerId, String status);
    void deleteByIdAndWorkerId(Long id, Long workerId);
}