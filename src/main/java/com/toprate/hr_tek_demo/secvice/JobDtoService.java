package com.toprate.hr_tek_demo.secvice;

import com.toprate.hr_tek_demo.dto.JobDto;
import com.toprate.hr_tek_demo.model.JobRequirements;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JobDtoService {
    public List<JobDto> findAllJob();

    public List<JobDto> findAll();

    public JobDto getJobDetailById(String id);

    public Optional<JobRequirements> findJobById(String id);

    public void deleteJob(JobRequirements job);

    public void saveJob(JobDto job);
}
