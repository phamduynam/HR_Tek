package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class JobDto {

    private String jobRecruitmentId;

    private String jobTitle;

    private int quantity;

    private String formOfWork;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;

    private float startSalary;

    private float endSalary;

    private String description;

    private int enable;

    private String level;

    private Float yearExperience;

    private List<JobWorkSkill> jobWorkSkills;

    private List<Skill> skillList;

    private List<Position> positionList;

    private String hrName;

    private Partner partner;

    private Location location;

    private Users user;


    // constructor mac dinh
    public JobDto() {
        this.setEnable(1);
    }

    public JobRequirements convertToModel() {
        JobRequirements jobRequirement = new JobRequirements();
        jobRequirement.setJobRecruitmentId(this.jobRecruitmentId);
        jobRequirement.setJobTitle(this.jobTitle);
        jobRequirement.setQuantity(this.quantity);
        jobRequirement.setFormOfWork(this.formOfWork);
        jobRequirement.setDateStart(this.dateStart);
        jobRequirement.setDateEnd(this.dateEnd);
        jobRequirement.setStartSalary(this.startSalary);
        jobRequirement.setEndSalary(this.endSalary);
        jobRequirement.setDescription(this.description);
        jobRequirement.setEnable(this.enable);
        jobRequirement.setLevel(this.level);
        jobRequirement.setYearExperience(this.yearExperience);
        jobRequirement.setPartner(this.partner);
        jobRequirement.setLocation(this.location);
        jobRequirement.setUser(this.user);

        // Add skill not null
        List<JobWorkSkill> jobWorkSkillList = new ArrayList<>();

        if(this.getJobWorkSkills() != null){
            for (JobWorkSkill jobWorkSkill : this.getJobWorkSkills()) {
                if (jobWorkSkill.getSkill() != null) {
                    jobWorkSkillList.add(jobWorkSkill);
                }
            }
        }
        jobRequirement.setJobWorkSkills(jobWorkSkillList);
        this.setJobWorkSkills(jobWorkSkillList);

        List<JobPosition> jobPositionList = new ArrayList<>();
        if(this.getPositionList() != null){
            for (Position position : this.getPositionList()) {
                jobPositionList.add(new JobPosition(position));
            }
        }

        jobRequirement.setJobPositionList(jobPositionList);
        return jobRequirement;
    }

    public JobDto(String jobRecruitmentId, String jobTitle, String hrName) {
        this.jobRecruitmentId = jobRecruitmentId;
        this.jobTitle = jobTitle;
        this.hrName = hrName;
    }
}
