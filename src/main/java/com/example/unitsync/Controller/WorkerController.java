package com.example.unitsync.Controller;

import com.example.User;
import com.example.unitsync.Repository.TaskRepo;
import com.example.unitsync.Repository.UserRepo;
import com.example.unitsync.Repository.WorkerRepo;

import Model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WorkerController {
    private final TaskRepo taskRepository;
    private final UserRepo userRepository;
    private final WorkerRepo workerRepository;
    
    public WorkerController(TaskRepo taskRepository, 
                           UserRepo userRepository,
                           WorkerRepo workerRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.workerRepository = workerRepository;
    }
    
    @GetMapping("/worker/dashboard")
    public String dashboard(HttpSession session, Model model) {
      User user = (User) session.getAttribute("user");        if (user == null || !"worker".equals(user.getRole())) {
            return "redirect:/login";
        }
        
        List<Task> activeTasks = taskRepository.findByWorkerIdAndStatusNot(user.getId(), "done");
        List<Task> inProgressTasks = taskRepository.findByWorkerIdAndStatus(user.getId(), "in_progress");
        
        model.addAttribute("user", user);
        model.addAttribute("activeTasks", activeTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);
        
        return "worker-dashboard";
    }
    
    @PostMapping("/worker/tasks/add")
    public String addTask(@RequestParam String unit,
                          @RequestParam String issue,
                          @RequestParam String date,
                          HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        
        Task task = new Task();
        task.setWorkerId(user.getId());
        task.setUnit(unit);               
        task.setIssue(issue);             
        task.setDate(LocalDate.parse(date));
        task.setStatus("pending");
        
        taskRepository.save(task);
        
        return "redirect:/worker/dashboard";
    }
    
    @GetMapping("/worker/tasks/update")
    public String updateTaskStatus(@RequestParam Long id,
                                   @RequestParam String status,
                                   HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        
        taskRepository.findById(id).ifPresent(task -> {
            if (task.getWorkerId().equals(user.getId())) {
                task.setStatus(status);
                taskRepository.save(task);
            }
        });
        
        return "redirect:/worker/dashboard";
    }
}