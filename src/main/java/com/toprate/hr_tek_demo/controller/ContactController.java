package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.secvice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    //    @PostMapping("/import")
//    public String importAll(@RequestParam("myFile") MultipartFile myFile) {
//
//        contactService.saveContactInSheet(myFile);
//
//        System.out.println("Finish Add Data");
//
//        return "user/contacts";
//    }
    @RequestMapping("/add")
    public String showNewContactPage(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact/add";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        contactService.saveContact(contact);
        return "/contact/contacts";
    }

    @PostMapping("/edit")
    public String editContact(@ModelAttribute("contact") Contact contact) {
        contactService.saveContact(contact);
        return "/contact/edit";
    }
}
