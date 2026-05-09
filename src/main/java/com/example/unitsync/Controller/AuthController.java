package com.example.unitsync.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.unitsync.Model.User;
import com.example.unitsync.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService userService;
    
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model,
                        HttpSession session) {
        var user = userService.findByUsername(username);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
        
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        cookie.setPath("/");
        response.addCookie(cookie);
        
        request.getSession().setAttribute("user", user.get());
        
        String role = user.get().getRole();
        if (role.equals("admin")) return "redirect:/admin/dashboard";
        if (role.equals("worker")) return "redirect:/worker/dashboard";
        return "redirect:/resident/dashboard";
    }
    
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role,
                           @RequestParam String email,
                           @RequestParam String phone,
                           Model model) {

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            model.addAttribute("error", "Invalid email format");
            return "register";
        }
    
        if (!phone.matches("^\\+20[0-9]{9}$")) {
            model.addAttribute("error", "Invalid phone number. Must be 13 digits starting with +20");
            return "register";
        }
        
        if (password.length() < 8) {
            model.addAttribute("error", "Password must be at least 8 characters");
            return "register";
        }
        
        if (userService.usernameExists(username)) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        user.setPhone(phone);
        userService.save(user);
        
        model.addAttribute("success", "Account created! Please login.");
        return "login";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
    
    @GetMapping("/worker/dashboard")
    public String workerDashboard() {
        return "worker-dashboard";
    }
    
    @GetMapping("/resident/dashboard")
    public String residentDashboard() {
        return "resident-dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        
        model.addAttribute("username", username);
        return "dashboard";
    }
}