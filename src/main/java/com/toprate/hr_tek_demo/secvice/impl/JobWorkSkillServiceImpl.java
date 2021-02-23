package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobWorkSkill;
import com.toprate.hr_tek_demo.repository.JobWorkSkillRepository;
import com.toprate.hr_tek_demo.secvice.JobWorkSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobWorkSkillServiceImpl implements JobWorkSkillService {
    @Autowired
    JobWorkSkillRepository jobWorkSkillRepository;

    @Override
    public void save(String jobId, int skillId) {
        JobWorkSkill jobWorkSkill = new JobWorkSkill();
//        jobWorkSkill.setJobRequirements(new JobRequirements(jobId));
//        jobWorkSkill.setSkill(new Skill(skillId));
        jobWorkSkillRepository.save(jobWorkSkill);
    }

    @Override
    public List<JobWorkSkill> findAllByJobId(String id) {
        return jobWorkSkillRepository.findAllByJobId(id);
    }

    @Override
    public void delete(JobWorkSkill jobWorkSkill) {
        jobWorkSkillRepository.delete(jobWorkSkill);
    }

    @Override
    public void saveAll(List<JobWorkSkill> jobWorkSkillList) {
        jobWorkSkillRepository.saveAll(jobWorkSkillList);
    }
}
