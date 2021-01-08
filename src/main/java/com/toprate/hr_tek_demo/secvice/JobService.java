package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JobService {
    public List<JobRequirements> findAllJob();

    public List<JobRequirements> findAll();

    public JobRequirements getJobDetailById(String id);

    public Optional<JobRequirements> findJobById(String id);

    public void deleteJob(JobRequirements jobRequirements);

    public void saveJob(JobRequirements job);
}
