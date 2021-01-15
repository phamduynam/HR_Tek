package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.excel.MyFile;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.secvice.impl.ContactServiceImpl;
import com.toprate.hr_tek_demo.secvice.impl.PositionServiceImpl;
import com.toprate.hr_tek_demo.secvice.impl.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private SkillServiceImpl skillService;


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

    @GetMapping("/add")
    public ModelAndView showNewContactPage() {
        ModelAndView mav = new ModelAndView("contact/add");
        ContactDto contactDto = new ContactDto();
        ArrayList<ContactWorkSkill> listContactContactWorkSkill = new ArrayList<>();
        for(int i = 1 ; i <= 10 ; i ++){
            listContactContactWorkSkill.add(new ContactWorkSkill());
        }
        contactDto.setContactWorkSkillList(listContactContactWorkSkill);
        mav.addObject("positions",positionService.getAllPosition());
        mav.addObject("skills",skillService.getAllSkill());
        mav.addObject("contactDto",contactDto);
        return mav;
    }

    @PostMapping("/save")
    public String saveContact(@Valid @ModelAttribute("contactDto") ContactDto contactDto,
                              Errors errors,Model model) {

        if (null != errors && errors.getErrorCount() > 0){
            return "contact/add";
        }
        return "redirect:/view-contacts";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editContact(@PathVariable String id,ModelAndView mav) {
        Contact contact = contactService.getContactById(id);
        ContactDto contactDto = contactService.convertContactToDto(contact);
        mav.setViewName("contact/edit");
        mav.addObject("positions",positionService.getAllPosition());
        mav.addObject("skills",skillService.getAllSkill());
        mav.addObject("contactDto",contactDto);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable String id) {
        Contact contact = contactService.getContactById(id);
        contact.setEnable(false);
        contactService.saveContact(contact);
        return "redirect:/view-contacts";
    }
}
