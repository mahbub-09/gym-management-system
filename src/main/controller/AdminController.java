package com.GymManagement.GymManagement.controller;

import com.GymManagement.GymManagement.model.Item;
import com.GymManagement.GymManagement.model.Member;
import com.GymManagement.GymManagement.model.MemberDetailsDTO;
import com.GymManagement.GymManagement.repository.AdminRepository;
import com.GymManagement.GymManagement.repository.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("")
    public String showLoginForm() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if ("admin".equals(username) && "0000".equals(password)) {
            return "redirect:/admin/home";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "admin-login";
        }
    }

    @GetMapping("/home")
    public String showAdminHome() {
        return "admin-home";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }



//    @GetMapping()
//    public String adminHome(){
//        return "admin-home.html";
//    }

    //Show all item
    @GetMapping("/items")
    public String showAllItem(Model model){

        model.addAttribute("items", adminRepository.showItems());
        return "all-item";
    }

    //Add Item
    @GetMapping("/add-item")
    public String addItem(){

        return "add-item-form";
    }

    @PostMapping("/add-item")
    public String saveItem(@ModelAttribute Item item){
        adminRepository.saveItem(item);
        return "redirect:/admin/items";
    }

    //Delete item
    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable int id){
        adminRepository.deleteById(id);
        return"redirect:/admin/items";
    }

    //Update item quantity
    @GetMapping("/update-item/{id}")
    public String updateItem(@PathVariable int id,
                             Model model){
        Item item = adminRepository.getItemById(id);
        System.out.println(item);
        model.addAttribute("item", item);
        return "update-item-form";
    }

    @PostMapping("/update-item")
    public String updateItem(@ModelAttribute Item item){
        adminRepository.updateById(item);
        return "redirect:/admin/items";
    }

    //Show all members
    public String showAllMember(){
        return"all-member";
    }

    //Add member
    public String addMember(){
        return "add-member-form";
    }

    //delete member
    public String deleteMember(){
        return "all-member";
    }

    //Update member info
    public String updateMember(){
        return "update-member-form";
    }

    //--------------------------------

    @Autowired
    private MemberDAO memberDAO;



    @GetMapping("/members")
    public String viewAllMembers(Model model) {
        List<MemberDetailsDTO> members = memberDAO.getAllMemberDetails();
        model.addAttribute("members", members);
        return "members"; // this should match your Thymeleaf template
    }


    @GetMapping("/member/edit/{id}")
    public String editMember(@PathVariable int id, Model model) {
        model.addAttribute("member", memberDAO.getAllMemberDetails());
        return "admin/edit_member";
    }

    @PostMapping("/member/update")
    public String updateMember(@ModelAttribute Member member) {
        memberDAO.updateMember(member);
        return "redirect:/admin/members";
    }

    @GetMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable int id) {
        memberDAO.deleteMemberById(id);
        return "redirect:/admin/members";
    }
}
