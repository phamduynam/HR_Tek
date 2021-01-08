package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.secvice.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobRequirements> findAllJob() {
        return jobRepository.getAllJob();
    }

    @Override
    public List<JobRequirements> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public JobRequirements getJobDetailById(String id) {
        return jobRepository.getJobById(id);
    }

    @Override
    public Optional<JobRequirements> findJobById(String id) {
        return jobRepository.findById(id);
    }

    @Override
    public void deleteJob(JobRequirements job) {
        job.setEnable(0);
        jobRepository.save(job);
    }

    @Override
    public void saveJob(JobRequirements job) {
        jobRepository.save(job);
    }
}
