package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RPaymentController {

    @Autowired
    RPaymentService paymentService;

    @GetMapping("/payment")
    public String showPayments(HttpSession session, Model model) {
        Integer residentId = (Integer) session.getAttribute("residentId");
        if (residentId == null) return "redirect:/login";

        model.addAttribute("debts", paymentService.getPendingPayments(residentId));
        model.addAttribute("totalPending", paymentService.getTotalPending(residentId));

        return "payment";
    }

    @PostMapping("/payment/pay")
    public String confirmPayment(
            @RequestParam("paymentId") int paymentId,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName")  String lastName,
            @RequestParam("payMethod") String payMethod,
            @RequestParam(value = "cardNumber", required = false, defaultValue = "") String cardNumber,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        Integer residentId = (Integer) session.getAttribute("residentId");
        if (residentId == null) return "redirect:/login";

        String result = paymentService.confirmPayment(
            paymentId, residentId, firstName, lastName, payMethod, cardNumber
        );

        if (result.equals("Paid successfully")) {
            redirectAttrs.addFlashAttribute("successMsg", result);
        } else {
            redirectAttrs.addFlashAttribute("errorMsg", result);
        }

        return "redirect:/payment";
    }



}