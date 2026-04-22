package com.residential.service;

import com.residential.model.Worker;
import com.residential.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    // Get all workers
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    // Get worker by ID
    public Optional<Worker> getWorkerById(Long id) {
        return workerRepository.findById(id);
    }

    // Get workers by department
    public List<Worker> getWorkersByDepartment(String department) {
        return workerRepository.findByDepartment(department);
    }

    // Get workers by status
    public List<Worker> getWorkersByStatus(Worker.WorkerStatus status) {
        return workerRepository.findByStatus(status);
    }

    // Get workers by shift
    public List<Worker> getWorkersByShift(String shift) {
        return workerRepository.findByShift(shift);
    }

    // Create new worker
    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    // Update worker
    public Worker updateWorker(Long id, Worker updatedWorker) {
        return workerRepository.findById(id).map(worker -> {
            worker.setName(updatedWorker.getName());
            worker.setEmail(updatedWorker.getEmail());
            worker.setPhone(updatedWorker.getPhone());
            worker.setJobTitle(updatedWorker.getJobTitle());
            worker.setDepartment(updatedWorker.getDepartment());
            worker.setSalary(updatedWorker.getSalary());
            worker.setShift(updatedWorker.getShift());
            worker.setStatus(updatedWorker.getStatus());
            return workerRepository.save(worker);
        }).orElseThrow(() -> new RuntimeException("Worker not found with id: " + id));
    }

    // Update worker status
    public Worker updateWorkerStatus(Long id, Worker.WorkerStatus status) {
        return workerRepository.findById(id).map(worker -> {
            worker.setStatus(status);
            return workerRepository.save(worker);
        }).orElseThrow(() -> new RuntimeException("Worker not found with id: " + id));
    }

    // Delete worker
    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new RuntimeException("Worker not found with id: " + id);
        }
        workerRepository.deleteById(id);
    }
}
