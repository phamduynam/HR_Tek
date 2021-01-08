package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {
//    @Autowired
//    private ContactService contactService;
//
//    @Autowired
//    private PositionService positionService;
//
//    @Autowired
//    private SkillService skillService;
//
//    @GetMapping("/add")
//    public ModelAndView showNewContactPage() {
//        ModelAndView mav = new ModelAndView("contact/add");
//        Contact contact = new Contact();
//
//        ContactDto contactDto = new ContactDto();
//        mav.addObject("contact",contact);
//        mav.addObject("positions",positionService.getAllPosition());
//        mav.addObject("skills",skillService.getAllSkill());
//        mav.addObject("contactDto",contactDto);
//        return mav;
//    }
//
//    @PostMapping("/save")
//    public String saveContact(@ModelAttribute("contact") Contact contact,@ModelAttribute("contactDto") ContactDto contactDto) {
//
//        // Khởi tạo danh sách contactWorkSkill
//        Skill[] listSkill = contactDto.getSkillList();
//        List<ContactWorkSkill> contactListSkill = new ArrayList<>();
//        System.out.println("Danh sach skill truoc thi them: " + contactListSkill);
//        for( int i = 0 ; i < listSkill.length; i ++){
//            contactListSkill.add(new ContactWorkSkill(listSkill[i], contact));
//        }
//        System.out.println("Danh sach skill sau khi them: "+ contactListSkill);
//        Position[] listPosition = contactDto.getPositionList();
//        List<ContactPosition> contactPositionList = new ArrayList<>();
//        System.out.println("List position Before : " + contactPositionList);
//        for(int i = 0 ; i < listPosition.length; i ++){
//            contactPositionList.add(new ContactPosition(contact,listPosition[i]));
//        }
//        System.out.println("List position After : " + contactPositionList);
//        contact.setContactPositionList(contactPositionList);
//        contact.setContactWorkSkillList(contactListSkill);
//        contact.setEnable(true);
//        contactService.saveContact(contact);
//        return "redirect:/view-contacts";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editContact(@PathVariable String id) {
//        Contact contact = contactService.getContactById(id);
//        contactService.saveContact(contact);
//        return "redirect:/view-contacts";
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String deleteContact(@PathVariable String id) {
//        Contact contact = contactService.getContactById(id);
//        contact.setEnable(false);
//        contactService.saveContact(contact);
//        return "redirect:/view-contacts";
//    }
}
