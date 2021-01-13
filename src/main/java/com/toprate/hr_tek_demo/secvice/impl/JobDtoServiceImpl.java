package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.dto.JobDto;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.repository.JobDtoRepository;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.secvice.JobDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobDtoServiceImpl implements JobDtoService {
    @Autowired
    private JobDtoRepository jobDtoRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobDto> findAllJob() {
        return jobDtoRepository.getAllJob();
    }

    @Override
    public List<JobDto> findAll() {
        return jobDtoRepository.findAll();
    }

    @Override
    public JobDto getJobDetailById(String id) {
        return jobDtoRepository.getJobById(id);
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
    public void saveJob(JobDto job) {
        jobDtoRepository.save(job);
    }
}
