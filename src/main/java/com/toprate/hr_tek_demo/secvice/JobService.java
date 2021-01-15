package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobRequirements;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public Optional<JobRequirements> findJobById(String id);

    public void saveJob(JobRequirements jobRequirements);

    public List<JobRequirements> findAllJob();
}
