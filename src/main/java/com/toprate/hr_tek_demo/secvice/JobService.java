package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobRequirements;

import java.util.List;

public interface JobService {
    public JobRequirements findJobById(String id);

    public void saveJob(JobRequirements jobRequirements);

    public List<JobRequirements> findAllJob();
}
