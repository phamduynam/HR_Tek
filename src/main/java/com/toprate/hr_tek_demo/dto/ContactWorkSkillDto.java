package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.ContactWorkSkill;

import java.util.ArrayList;
import java.util.List;

public class ContactWorkSkillDto {

    private List<ContactWorkSkill> contactWorkSkillList  = new ArrayList<>();

    public void addContactWorkSkill(ContactWorkSkill contactWorkSkill){
        this.contactWorkSkillList.add(contactWorkSkill);
    }

    public List<ContactWorkSkill> getContactWorkSkillList() {
        return contactWorkSkillList;
    }

    public void setContactWorkSkillList(List<ContactWorkSkill> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

}
