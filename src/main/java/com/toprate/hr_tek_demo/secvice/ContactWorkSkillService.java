package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;

import java.util.List;

public interface ContactWorkSkillService {
    public List<ContactWorkSkill> getAllByContact(Contact contact);

    public ContactWorkSkill getById(Integer id);

    public void save(ContactWorkSkill contactWorkSkill);

    public void saveList(List<ContactWorkSkill> contactWorkSkillsList);

    public void deleteById(Integer integer);

    public void delete(ContactWorkSkill contactWorkSkill);

}
