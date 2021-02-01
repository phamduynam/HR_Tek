package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.dto.SearchDto;
import com.toprate.hr_tek_demo.dto.SearchJobForContactDto;
import com.toprate.hr_tek_demo.excel.MyFile;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.secvice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private SkillServiceImpl skillService;

    @Autowired
    private StatusServiceImpl statusService;

    @Autowired
    private JobServiceImpl jobService;

    @GetMapping("/view-contacts")
    public String getContactView(Model model){
        MyFile myFile = new MyFile();
        model.addAttribute("myFile",myFile);
        model.addAttribute("contacts", contactService.getAllContactTrue());
        model.addAttribute("positions",positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        model.addAttribute("statusList",statusService.getAllStatus());
        model.addAttribute("searchDto",new SearchDto());
        return "/contact/contacts";
    }

    @GetMapping("/search")
    public String searchContact(@ModelAttribute("searchDto") SearchDto searchDto,Model model){
        MyFile myFile = new MyFile();
        model.addAttribute("myFile",myFile);
        model.addAttribute("contacts", contactService.search(searchDto));
        model.addAttribute("positions",positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        model.addAttribute("statusList",statusService.getAllStatus());
        model.addAttribute("searchDto",searchDto);
        return "/contact/contacts";
    }

    @GetMapping("/add")
    public ModelAndView showNewContactPage() {
        ModelAndView mav = new ModelAndView("contact/add");
        ContactDto contactDto = new ContactDto();
        ArrayList<ContactWorkSkill> listContactContactWorkSkill = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            listContactContactWorkSkill.add(new ContactWorkSkill());
        }
        contactDto.setContactWorkSkillList(listContactContactWorkSkill);
        mav.addObject("positions",positionService.getAllPosition());
        mav.addObject("skills",skillService.getAllSkill());
        mav.addObject("contactDto",contactDto);
        return mav;
    }
        
    @PostMapping("/save")
    public String saveContact(@ModelAttribute("contactDto") ContactDto contactDto) {
        Contact contact = contactDto.convertToModel();
        contactService.saveContact(contact);
        return "redirect:/contact/view-contacts";
    }

    @PostMapping("/update")
    public String updateContact(@ModelAttribute("contactDto") ContactDto contactDto){
        Contact contact = contactDto.convertToModel();
        contactService.updateContact(contact);
        return "redirect:/contact/view-contacts";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView viewDetailContact(@PathVariable String id, ModelAndView mav) {
        Contact contact = contactService.getContactById(id);
        ContactDto contactDto = contact.convertToDto();
        mav.setViewName("contact/view_detail");
        mav.addObject("positions", positionService.getAllPosition());
        mav.addObject("skills", skillService.getAllSkill());
        mav.addObject("contactDto", contactDto);
        return mav;
    }

    @GetMapping("/add-transaction/{id}")
    public ModelAndView addTransactionContact(@PathVariable String id, ModelAndView mav) {
        Contact contact = contactService.getContactById(id);
        ContactDto contactDto = contact.convertToDto();
        SearchJobForContactDto searchJobForContactDto = new SearchJobForContactDto("",false,false,false);
        mav.setViewName("contact/add_transaction");
        mav.addObject("contactDto",contactDto);
        mav.addObject("searchObject",searchJobForContactDto);

        return mav;
    }

    @GetMapping("/add-transaction/search/{id}")
    public ModelAndView searchJobForContact(@PathVariable String id, ModelAndView mav, SearchJobForContactDto searchJobForContactDto) {
        Contact contact = contactService.getContactById(id);
        ContactDto contactDto = contact.convertToDto();
        mav.setViewName("contact/add_transaction");
        mav.addObject("contactDto",contactDto);
        mav.addObject("searchObject",searchJobForContactDto);

        return mav;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView editContact(@PathVariable String id, ModelAndView mav) {
        Contact contact = contactService.getContactById(id);
        ContactDto contactDto = contact.convertToDto();
        mav.setViewName("contact/edit");
        mav.addObject("positions", positionService.getAllPosition());
        mav.addObject("skills", skillService.getAllSkill());
        mav.addObject("contactDto", contactDto);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        contact.setEnable(false);
        contactService.saveContact(contact);
        return "redirect:/contact/view-contacts";
    }

}
