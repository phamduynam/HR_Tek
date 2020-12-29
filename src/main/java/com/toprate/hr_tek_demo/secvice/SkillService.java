package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Skill;
import com.toprate.hr_tek_demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public Skill getSkillById(int id){
        return skillRepository.findById(id).get();
    }
}
