package com.toprate.hr_tek.secvice;

import com.toprate.hr_tek.model.ContactWorkSkill;
import com.toprate.hr_tek.repository.ContactWorkSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactWorkSkillService {
    @Autowired
    private ContactWorkSkillRepository contactWorkSkillRepository;

    private ContactWorkSkill save( ContactWorkSkill contactWorkSkill){
        return contactWorkSkillRepository.save(contactWorkSkill);
    }
}
