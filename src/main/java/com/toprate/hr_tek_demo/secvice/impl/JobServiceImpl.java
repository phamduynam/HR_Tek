package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.dto.ContactDto;
import com.toprate.hr_tek_demo.dto.JobRequirementDTO;
import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.dto.SearchJobForContactDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.repository.JobRepository;
import com.toprate.hr_tek_demo.repository.specification.JobSpecification;
import com.toprate.hr_tek_demo.secvice.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // luu 1 Job moi
    @Override
    public void saveJob(JobRequirements job) {
        // luu job
        JobRequirements jobSave = jobRepository.saveAndFlush(job);

        // Luu job vao jobPositionList
        List<JobPosition> jobPositionList = job.getJobPositionList();
        for (JobPosition jobPosition : jobPositionList) {
            jobPosition.setJobRequirements(jobSave);
        }

        // Luu job vao jobWorkSkillList
        List<JobWorkSkill> jobWorkSkillList = job.getJobWorkSkills();
        for (JobWorkSkill jobWorkSkill : jobWorkSkillList) {
            jobWorkSkill.setJobRequirements(jobSave);
        }

        // luu toan bo jobPosition
        jobPositionService.saveAll(jobPositionList);

        // luu toan bo jobWorkSkill
        jobWorkSkillService.saveAll(jobWorkSkillList);
    }

    // tim kiem tat ca Job
    @Override
    public List<JobRequirements> findAllJob() {
        return jobRepository.findAllJob();
    }

    // chinh sua 1 Job
    @Override
    public void updateJob(JobRequirements jobRequirement) {

        String id = jobRequirement.getJobRecruitmentId();
        JobRequirements jobExist = jobRepository.findById(id).get();

        // list jobWorkSkill co trong DB
        List<JobWorkSkill> jobWorkSkillListExist = new ArrayList<>(jobExist.getJobWorkSkills());
        List<JobWorkSkill> jobWorkSkillListNew = new ArrayList<>(jobRequirement.getJobWorkSkills());

        for (JobWorkSkill jobWorkSkillExist : jobWorkSkillListExist) {
            for (JobWorkSkill jobWorkSkillNew : jobWorkSkillListNew) {
                if (jobWorkSkillNew.getSkill().equals(jobWorkSkillExist.getSkill())) {
                    jobWorkSkillNew.setJobworkSkillId(jobWorkSkillExist.getJobworkSkillId());
                    break;
                }
            }
        }

        // list jobPosition co trong DB
        List<JobPosition> jobPositionListExist = new ArrayList<>(jobExist.getJobPositionList());
        List<JobPosition> jobPositionListNew = new ArrayList<>(jobRequirement.getJobPositionList());
        for (JobPosition jobPositionExist : jobPositionListExist) {
            for (JobPosition jobPositionNew : jobPositionListNew) {
                if (jobPositionNew.getPosition().equals(jobPositionExist.getPosition())) {
                    jobPositionNew.setJobPositionId(jobPositionExist.getJobPositionId());
                    break;
                }
            }
        }

        // xoa list jobWorkSKill
        for (JobWorkSkill jobWorkSkill : jobWorkSkillListExist) {
            jobExist.deleteJobWorkSkill(jobWorkSkill);
            jobWorkSkill.getSkill().deleteJobWorkSkill(jobWorkSkill);
        }

        // xoa list jobWorkPosition
        for (JobPosition jobPosition : jobPositionListExist) {
            jobExist.deleteJobPosition(jobPosition);
            jobPosition.getPosition().deleteJobPosition(jobPosition);
        }

        // them lai list moi
        for (JobWorkSkill jobWorkSkill : jobWorkSkillListNew) {
            jobExist.addJobWorkSkill(jobWorkSkill);
        }

        for (JobPosition jobPosition : jobPositionListNew) {
            jobExist.addJobPosition(jobPosition);
        }
        jobRepository.save(jobRequirement);
    }

    // Xoa 1 Job
    @Override
    public void deleteJob(JobRequirements job) {
        job.setEnable(0);
        jobRepository.save(job);
    }

    // phan trang
    @Override
    public Page<JobRequirements> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return jobRepository.findAll(pageable);
    }

    // tim kiem Job
    @Override
    public List<JobRequirements> searchJobByKeyword(SearchJobDto data) {
        return jobRepository.findAll(new JobSpecification().searchJob(data));
    }

    public List<JobRequirementDTO> searchJobMatchByContact(ContactDto contactDto, SearchJobForContactDto searchJobForContactDto) {

        List<Integer> idSkills = searchJobForContactDto.getSuitableSkill() ? contactDto.getContactWorkSkillList().stream().map(emp -> emp.getSkill().getSkillId()).collect(Collectors.toList()) : new ArrayList<>();
        String level = searchJobForContactDto.getSuitableLevel() ? contactDto.getLevels() : null;
        Float experience = searchJobForContactDto.getSuitableExp() ? contactDto.getYearExperience() : null;
        String keyword = searchJobForContactDto.getKeyWord();
        List<JobRequirements> jobRequirements = jobRepository.findAll(new JobSpecification().searchFilter(keyword, level, experience, idSkills));
        List<JobRequirementDTO> jobRequirementDTOs = jobRequirements.stream().map(s -> convertJobRequirementsToDTO(s, contactDto)).collect(Collectors.toList());
        return jobRequirementDTOs;
    }

    @Override
    public List<JobRequirements> searchJobByContact(ContactDto contactDto) {
        return jobRepository.findByTakeCareTransactionList_Contact_CandidateId(contactDto.getCandidateId());
    }

    private JobRequirementDTO convertJobRequirementsToDTO(JobRequirements jobRequirements, ContactDto contactDto) {
        JobRequirementDTO jobRequirementDTO = new JobRequirementDTO();
        jobRequirementDTO.setJobRecruitmentId(jobRequirements.getJobRecruitmentId());
        jobRequirementDTO.setJobTitle(jobRequirements.getJobTitle());
        jobRequirementDTO.setLevel(jobRequirements.getLevel());
        jobRequirementDTO.setYearExperience(jobRequirements.getYearExperience());
        jobRequirementDTO.setJobPositionList(jobRequirements.getJobPositionList().stream().map(e -> e.getPosition().getPositionName()).collect(Collectors.joining(", ")));
        jobRequirementDTO.setJobWorkSkills(jobRequirements.getJobWorkSkills().stream().map(e -> e.getSkill().getSkillName()).collect(Collectors.joining(", ")));
        jobRequirementDTO.setTakeCareTransactionList(jobRequirements.getTakeCareTransactionList().stream().filter(takeCareTransaction -> takeCareTransaction.getContact().getCandidateId().equals(contactDto.getCandidateId())).count() > 0);
        return jobRequirementDTO;
    }


    @Override
    public List<JobRequirements> searchJobForContact(Contact contact, SearchJobForContactDto searchJobForContactDto) {
        // Logic tìm kiếm
        String queryString = "SELECT j.* FROM job_recruitment j";

            // Phù hợp với kỹ năng
            if(searchJobForContactDto.getSuitableSkill()){
                queryString.concat(
                    "JOIN job_work_skill jwk ON j.job_recruitment_id = jwk.job_recruitment_id" +
                    "JOIN skill s ON s.skill_id = jwk.skill_id" +
                    "JOIN contact_work_skill cwk ON cwk.skill_id = s.skill_id" +
                    "JOIN contact c.contact_work_skill_id = cwk.contact_work_skill_id"
                );
            }

            // Phù hợp với trình độ
            if(searchJobForContactDto.getSuitableLevel()){
                queryString.concat("j.levels = " + "'" + contact.getLevels() + "'");
            }

            // Phù hợp với kinh nghiệm
            if(searchJobForContactDto.getSuitableExp()){
                queryString.concat("j.year_experience >= " + "'" +contact.getYearExperience() + "'");
            }

            queryString.concat("ORDER BY ");

        EntityManager entityManager = null;
        Query query = entityManager.createNativeQuery(queryString,JobRequirements.class);
            List<JobRequirements> jobRequirementsList = query.getResultList();

        return jobRequirementsList;
    }
}
