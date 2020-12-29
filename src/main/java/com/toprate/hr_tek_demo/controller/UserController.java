package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.excel.MyFile;
import com.toprate.hr_tek_demo.secvice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private ContactService contactService;


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
    public String getContactView(Model model){
        MyFile myFile = new MyFile();
        model.addAttribute("myFile",myFile);
        model.addAttribute("contacts", contactService.getAllContact());
        System.out.println();
        return "contact/contacts";
    }

}
