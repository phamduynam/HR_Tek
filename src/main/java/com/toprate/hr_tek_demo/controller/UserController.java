package com.toprate.hr_tek_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


    // Quyền Admin
    @GetMapping("/account-managerment")
    public String getManageAccount(){
        return "user/accounts";
    }

    @GetMapping("/add-contact")
    public String getAddContact(){
        return "user/add-contact";
    }

    @GetMapping("/view-contacts")
    public String getContactView(){
        return "user/contacts";
    }
}
