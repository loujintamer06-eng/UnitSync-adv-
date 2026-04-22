package com.residential.repository;

import com.residential.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findByDepartment(String department);

    List<Worker> findByStatus(Worker.WorkerStatus status);

    Optional<Worker> findByEmail(String email);

    List<Worker> findByShift(String shift);
}
