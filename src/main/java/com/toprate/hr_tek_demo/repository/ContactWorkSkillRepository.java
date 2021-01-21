package com.toprate.hr_tek_demo.repository;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactWorkSkillRepository extends JpaRepository<ContactWorkSkill,Integer> {
    public List<ContactWorkSkill> findAllByContact(Contact contact);
}
