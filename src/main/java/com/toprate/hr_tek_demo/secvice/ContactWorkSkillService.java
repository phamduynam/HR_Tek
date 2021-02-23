package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;

import java.util.List;

public interface ContactWorkSkillService {

    List<ContactWorkSkill> getAllByContact(Contact contact);

    ContactWorkSkill getById(Integer id);

    void save(ContactWorkSkill contactWorkSkill);

    void saveList(List<ContactWorkSkill> contactWorkSkillsList);

    void deleteById(Integer integer);

    void delete(ContactWorkSkill contactWorkSkill);

}
