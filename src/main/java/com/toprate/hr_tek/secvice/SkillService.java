package com.toprate.hr_tek.secvice;

import com.toprate.hr_tek.model.Skill;
import com.toprate.hr_tek.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkill(){
        return skillRepository.findAll();
    }

    public Skill getSkillById(int id){
        return skillRepository.findById(id).get();
    }

    public Skill getSkillByName(String name) {
        return skillRepository.findBySkillName(name).get();
    }
}
