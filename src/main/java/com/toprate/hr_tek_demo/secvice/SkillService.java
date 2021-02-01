package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.Skill;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SkillService {
    List<Skill> getAllSkill();

    Skill getSkillByName(String name);
}
