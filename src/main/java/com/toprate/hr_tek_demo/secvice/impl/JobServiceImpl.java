package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.JobPosition;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.JobWorkSkill;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.secvice.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobWorkSkillServiceImpl jobWorkSkillService;

    @Autowired
    private JobPositionServiceImpl jobPositionService;

    @Override
    public Optional<JobRequirements> findJobById(String id) {
        return jobRepository.findById(id);
    }

    @Override
    public void saveJob(JobRequirements job) {
        // luu job
        JobRequirements jobSave = jobRepository.saveAndFlush(job);

        // Luu job vao jobPositionList
        List<JobPosition> jobPositionList = job.getJobPositionList();
        for(JobPosition jobPosition : jobPositionList) {
            jobPosition.setJobRequirements(jobSave);
        }

        // Luu job vao jobWorkSkillList
        List<JobWorkSkill> jobWorkSkillList = job.getJobWorkSkills();
        for(JobWorkSkill jobWorkSkill : jobWorkSkillList) {
            jobWorkSkill.setJobRequirements(jobSave);
        }

        // luu toan bo jobPosition
        jobPositionService.saveAll(jobPositionList);

        // luu toan bo jobWorkSkill
        jobWorkSkillService.saveAll(jobWorkSkillList);

    }

    @Override
    public List<JobRequirements> findAllJob() {
        return jobRepository.findAllJob();
    }

    @Override
    public void updateJob(JobRequirements jobRequirement) {
        jobRepository.save(jobRequirement);
    }

    @Override
    public void deleteJob(JobRequirements job) {
        job.setEnable(0);
        jobRepository.save(job);
    }


    @Override
    public Page<JobRequirements> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return jobRepository.findAll(pageable);
    }
}
