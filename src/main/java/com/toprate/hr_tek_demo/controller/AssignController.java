package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.ContactService;
import com.toprate.hr_tek_demo.secvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AssignController {

    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

    @GetMapping("/assign/contact")
    public String getAssignContactView(Model model){
        // get all user has role Hr
        List<Users> usersList = userService.getAllByRole("ADMIN");

        model.addAttribute(usersList);

        return "/contact/assign_contact";
    }
}
