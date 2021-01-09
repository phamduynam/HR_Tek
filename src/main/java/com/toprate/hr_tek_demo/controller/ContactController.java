package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.dto.ContactWorkSkillDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.secvice.ContactService;
import com.toprate.hr_tek_demo.secvice.ContactWorkSkillService;
import com.toprate.hr_tek_demo.secvice.PositionService;
import com.toprate.hr_tek_demo.secvice.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private ContactWorkSkillService contactWorkSkillService;

    @Autowired
    private SkillService skillService;

    @GetMapping("/add")
    public String showNewContactPage(Model model) {
        ContactDto contactDto = new ContactDto();
        model.addAttribute("contactDto",contactDto);
        model.addAttribute("positions",positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "contact/add";
    }

    @PostMapping("/insert")
    public String saveContact(@Valid @ModelAttribute("contactDto")  ContactDto contactDto,
                              @ModelAttribute("contactWorkSkillDto") ContactWorkSkillDto contactWorkSkillDto,
                              @Valid BindingResult bindingResult,Model model   ) {

        if(bindingResult.hasErrors()){
            return "contact/add";
        }
        // ĐỌc sanh sách ContactWorkSkill
        List<ContactWorkSkill> skillList = contactWorkSkillDto.getContactWorkSkillList();
        // List chứa các ContactWorkSkill hợp lệ
        List<ContactWorkSkill> skillListFilter = new ArrayList<>();
        for(ContactWorkSkill contactWorkSkill : skillList){
            // Nếu danh sách đó khác null thì sẽ thêm vào list của contact
            if(contactWorkSkill.getSkill() != null){
                skillListFilter.add(contactWorkSkill);
            }
        }

        // Khởi tạo danh sách contactPosition
        Position[] listPosition = contactDto.getPositionList();
        List<ContactPosition> contactPositionList = new ArrayList<>();



        return "redirect:/view-contacts";
    }

    @PostMapping("/edit/{id}")
    public String editContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        contactService.saveContact(contact);
        return "redirect:/view-contacts";
    }


    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        contact.setEnable(false);
        contactService.saveContact(contact);
        return "redirect:/view-contacts";
    }
}
