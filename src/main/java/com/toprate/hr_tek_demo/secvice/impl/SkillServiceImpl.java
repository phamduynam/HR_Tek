package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Skill;
import com.toprate.hr_tek_demo.repository.SkillRepository;
import com.toprate.hr_tek_demo.secvice.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkill() {
        return skillRepository.findAll();
    }
}
