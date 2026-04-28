package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResidentController {

    @Autowired
    ResidentService residentService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    AmenityService amenityService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("residents", residentService.getAll());
        model.addAttribute("payments", paymentService.getAllPayments());
        model.addAttribute("amenities", amenityService.getAll());
        return "index";
    }

    @GetMapping("/residents/all")
    @ResponseBody
    public List<ResidentModel> getAll() {
        return residentService.getAll();
    }

    @PostMapping("/residents/add")
    @ResponseBody
    public String addResident(@RequestBody ResidentModel resident) {
        return residentService.addResident(resident);
    }

    @PutMapping("/residents/update/{resid}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable int resid, @PathVariable String status) {
        return residentService.updateStatus(resid, status);
    }

    @DeleteMapping("/residents/delete/{resid}")
    @ResponseBody
    public String deleteResident(@PathVariable int resid) {
        return residentService.deleteResident(resid);
    }
}