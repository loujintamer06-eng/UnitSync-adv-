package com.example.unitsync.Controller;

import com.example.unitsync.Repository.UserRepo;
import com.example.unitsync.Repository.TaskRepo;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AuthController {
    
    private final UserRepo userRepository;
    public AuthController(UserRepo userRepository, TaskRepo taskRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        
        var userOptional = userRepository.findByUsername(name);
        
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "login";
        }
        
        User user = userOptional.get();
        
        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Wrong password");
            return "login";
        }
        
        session.setAttribute("user", user);
        
        if ("admin".equals(user.getRole())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/worker/dashboard";
        }
    }
    
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        
                            var existingUser = userRepository.findByUsername(name);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("worker");
        
        userRepository.save(user);
        
        return "redirect:/login";
    }

    @GetMapping("/worker/dashboard")
    public String workerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "worker-dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}