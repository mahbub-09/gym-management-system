package com.GymManagement.GymManagement.controller;

import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.model.Subscription;
import com.GymManagement.GymManagement.service.GymService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @Autowired
    private GymService gymService;

    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) return "redirect:/login";

        Subscription subscription = gymService.getMemberSubscription(member.getMemberId());
        model.addAttribute("member", member);
        model.addAttribute("subscription", subscription);
        return "member-profile";
    }

    @GetMapping("/subscribe")
    public String showSubscriptionForm() {
        return "subscription-form";
    }

    @PostMapping("/subscribe")
    public String saveSubscription(@RequestParam int slotId,
                                   @RequestParam String startMonth,
                                   @RequestParam String endMonth,
                                   HttpSession session,
                                   Model model) {
        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login";
        }

        Subscription sub = new Subscription();
        sub.setMemberId(member.getMemberId());
        sub.setSlotId(slotId);
        sub.setStartMonth(startMonth);
        sub.setEndMonth(endMonth);

        try {
            gymService.addSubscription(sub);
            model.addAttribute("message", "Subscription saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to save subscription: " + e.getMessage());
            return "subscription-form"; // stay on the form
        }

        return "redirect:/profile";
    }

}
