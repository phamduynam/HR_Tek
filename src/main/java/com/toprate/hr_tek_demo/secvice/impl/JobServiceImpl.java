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

import java.util.ArrayList;
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

        String id = jobRequirement.getJobRecruitmentId();

        JobRequirements jobExist = jobRepository.findById(id).get();

        // list jobWorkSkill co trong DB
        List<JobWorkSkill> jobWorkSkillListExist = new ArrayList<>(jobExist.getJobWorkSkills());
        List<JobWorkSkill> jobWorkSkillListNew = new ArrayList<>(jobRequirement.getJobWorkSkills());

        for(JobWorkSkill jobWorkSkillExist : jobWorkSkillListExist) {
            for(JobWorkSkill jobWorkSkillNew : jobWorkSkillListNew) {
                if(jobWorkSkillNew.getSkill().equals(jobWorkSkillExist.getSkill())) {
                    jobWorkSkillNew.setJobworkSkillId(jobWorkSkillExist.getJobworkSkillId());
                    break;
                }
            }
        }

        // list jobPosition co trong DB
        List<JobPosition> jobPositionListExist = new ArrayList<>(jobExist.getJobPositionList());
        List<JobPosition> jobPositionListNew = new ArrayList<>(jobRequirement.getJobPositionList());
        for(JobPosition jobPositionExist : jobPositionListExist) {
            for(JobPosition  jobPositionNew : jobPositionListNew) {
                if(jobPositionNew.getPosition().equals(jobPositionExist.getPosition())) {
                    jobPositionNew.setJobPositionId(jobPositionExist.getJobPositionId());
                    break;
                }
            }
        }

        // xoa list jobWorkSKill
        for(JobWorkSkill jobWorkSkill : jobWorkSkillListExist) {
            jobExist.deleteJobWorkSkill(jobWorkSkill);
            jobWorkSkill.getSkill().deleteJobWorkSkill(jobWorkSkill);
        }

        // xoa list jobWorkPosition
        for(JobPosition jobPosition : jobPositionListExist) {
            jobExist.deleteJobPosition(jobPosition);
            jobPosition.getPosition().deleteJobPosition(jobPosition);
        }

        // them lai list moi
        for(JobWorkSkill jobWorkSkill : jobWorkSkillListNew) {
            jobExist.addJobWorkSkill(jobWorkSkill);
        }

        for(JobPosition jobPosition : jobPositionListNew) {
            jobExist.addJobPosition(jobPosition);
        }

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
