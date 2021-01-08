package com.toprate.hr_tek.controller;

import com.toprate.hr_tek.excel.MyFile;
import com.toprate.hr_tek.secvice.ContactService;
import com.toprate.hr_tek.secvice.PositionService;
import com.toprate.hr_tek.secvice.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private SkillService skillService;

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
        model.addAttribute("contacts", contactService.getAllContactTrue());
        model.addAttribute("positions",positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        System.out.println();
        return "/contact/contacts";
    }

}
