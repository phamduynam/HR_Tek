package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.repository.ContactWorkSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactWorkSkillService {
    @Autowired
    private ContactWorkSkillRepository contactWorkSkillRepository;
}
