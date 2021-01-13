package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.secvice.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Override
    public JobRequirements findJobById(String id) {
        return null;
    }

    @Override
    public void saveJob(JobRequirements jobRequirements) {
        jobRequirements.setEnable(1);
        jobRepository.save(jobRequirements);
    }

    @Override
    public List<JobRequirements> findAllJob() {
        return jobRepository.findAllJob();
    }
}
