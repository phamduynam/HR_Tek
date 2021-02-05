package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobWorkSkill;

import java.util.List;

public interface JobWorkSkillService {
    void save(String jobId, int skillId);

    List<JobWorkSkill> findAllByJobId(String id);

    void delete(JobWorkSkill jobWorkSkill);

    void saveAll(List<JobWorkSkill> jobWorkSkillList);
}
