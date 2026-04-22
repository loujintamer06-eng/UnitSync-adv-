package com.residential.controller;

import com.residential.model.Worker;
import com.residential.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    // GET /api/workers → Get all workers
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

    // GET /api/workers/{id} → Get worker by ID
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/workers/department/{dept} → Get workers by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Worker>> getWorkersByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(workerService.getWorkersByDepartment(department));
    }

    // GET /api/workers/status/{status} → Get workers by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Worker>> getWorkersByStatus(@PathVariable Worker.WorkerStatus status) {
        return ResponseEntity.ok(workerService.getWorkersByStatus(status));
    }

    // GET /api/workers/shift/{shift} → Get workers by shift
    @GetMapping("/shift/{shift}")
    public ResponseEntity<List<Worker>> getWorkersByShift(@PathVariable String shift) {
        return ResponseEntity.ok(workerService.getWorkersByShift(shift));
    }

    // POST /api/workers → Create new worker
    @PostMapping
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
        Worker created = workerService.createWorker(worker);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/workers/{id} → Update worker
    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        try {
            return ResponseEntity.ok(workerService.updateWorker(id, worker));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH /api/workers/{id}/status → Update worker status only
    @PatchMapping("/{id}/status")
    public ResponseEntity<Worker> updateWorkerStatus(
            @PathVariable Long id,
            @RequestParam Worker.WorkerStatus status) {
        try {
            return ResponseEntity.ok(workerService.updateWorkerStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/workers/{id} → Delete worker
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
        try {
            workerService.deleteWorker(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
