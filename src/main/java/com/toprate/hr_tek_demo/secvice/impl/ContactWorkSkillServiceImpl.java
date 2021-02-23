package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.repository.ContactWorkSkillRepository;
import com.toprate.hr_tek_demo.secvice.ContactWorkSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactWorkSkillServiceImpl implements ContactWorkSkillService {
    @Autowired
    ContactWorkSkillRepository contactWorkSkillRepository;

    @Override
    public List<ContactWorkSkill> getAllByContact(Contact contact) {
        return contactWorkSkillRepository.findAllByContact(contact);
    }

    @Override
    public ContactWorkSkill getById(Integer id) {
        return contactWorkSkillRepository.findById(id).get();
    }

    @Override
    public void save(ContactWorkSkill contactWorkSkill) {
        contactWorkSkillRepository.save(contactWorkSkill);
    }

    @Override
    public void saveList(List<ContactWorkSkill> contactWorkSkillsList) {
        contactWorkSkillRepository.saveAll(contactWorkSkillsList);
    }

    @Override
    public void deleteById(Integer integer) {
        contactWorkSkillRepository.deleteById(integer);
    }

    @Override
    public void delete(ContactWorkSkill contactWorkSkill) {
        contactWorkSkillRepository.delete(contactWorkSkill);
    }

}
