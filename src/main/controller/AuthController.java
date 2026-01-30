package com.GymManagement.GymManagement.controller;

import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.service.GymService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private GymService gymService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register-member";
    }

    @PostMapping("/register")
    public String registerMember(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {
        System.out.println("Registering: " + name + ", " + email);

        try {
            if (gymService.memberExists(email)) {
                model.addAttribute("error", "Email already exists.");
                return "register-member";
            }

            Member member = new Member();
            member.setName(name);
            member.setEmail(email);
            member.setPassword(password);

            gymService.registerMember(member);
            model.addAttribute("message", "Registration successful. Please log in.");
        } catch (Exception e) {
            e.printStackTrace(); // Log exact cause in terminal
            model.addAttribute("error", "Something went wrong: " + e.getMessage());
        }

        return "register-member";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Member member = gymService.login(email, password);
        if (member != null) {
            session.setAttribute("member", member);
            return "redirect:/profile";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
