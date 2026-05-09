	package com.example.demo;
	import jakarta.servlet.http.HttpSession;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	
	import com.example.demo.profilemodel;
	import com.example.demo.profileservice;
	
	@Controller
	public class profilecontroller {
		@Autowired
		profileservice profileService;
	
	    @GetMapping("/Profile")
	    public String showProfile(HttpSession session, Model model) {
	
	        // بناخد الـ username اللي اتحط في الـ Session لما عمل Login
	        String username = (String) session.getAttribute("username");
	
	        // بنروح للداتابيز ونجيب بيانات الـ resident ده
	        profilemodel resident = profileService.getByUsername(username);
	
	        // بنبعت الـ object ده للـ Thymeleaf علشان يعرضه في الـ HTML
	        model.addAttribute("resident", resident);
	
	        return "Profile"; // بيروح لـ src/main/resources/templates/profile.html
	    }
	}