package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.JobWorkSkill;
import com.toprate.hr_tek_demo.model.Skill;
import com.toprate.hr_tek_demo.repository.JobWorkSkillRepository;
import com.toprate.hr_tek_demo.secvice.JobWorkSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobWorkSkillServiceImpl implements JobWorkSkillService {
    @Autowired
    private JobWorkSkillRepository jobWorkSkillRepository;
    @Override
    public void save(String jobId, int skillId) {
        JobWorkSkill jobWorkSkill = new JobWorkSkill();
        jobWorkSkill.setJobRequirements(new JobRequirements(jobId));
        jobWorkSkill.setSkill(new Skill(skillId));
        jobWorkSkillRepository.save(jobWorkSkill);
    }
}