package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobWorkSkill;

import java.util.List;

public interface JobWorkSkillService {
    public void save(String jobId, int skillId);

    public List<JobWorkSkill> findAllByJobId(String id);

    public void delete(JobWorkSkill jobWorkSkill);

    public void saveAll(List<JobWorkSkill> jobWorkSkillList);
}
