package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@SqlResultSetMapping(
//        name = "findAllJob",
//        classes = @ConstructorResult(
//                targetClass = JobDto.class,
//                columns = {
//                        @ColumnResult(name="jobRecruitmentId", type=String.class),
//                        @ColumnResult(name="jobTitle", type=String.class),
//                        @ColumnResult(name="quantity", type=Integer.class),
//                        @ColumnResult(name="formOfWork", type=String.class),
//                        @ColumnResult(name="dateStart", type=Date.class),
//                        @ColumnResult(name="dateEnd", type=Date.class),
//                        @ColumnResult(name="position1", type=String.class),
//                        @ColumnResult(name="skill", type=String.class),
//                }
//        )
//)
//@SqlResultSetMapping(
//        name = "findJobDetail",
//        classes = @ConstructorResult(
//                targetClass = JobDto.class,
//                columns = {
//                        @ColumnResult(name="jobRecruitmentId", type=String.class),
//                        @ColumnResult(name="jobTitle", type=String.class),
//                        @ColumnResult(name="quantity", type=Integer.class),
//                        @ColumnResult(name="formOfWork", type=String.class),
//                        @ColumnResult(name="dateStart", type=Date.class),
//                        @ColumnResult(name="dateEnd", type=Date.class),
//                        @ColumnResult(name="startSalary", type=Float.class),
//                        @ColumnResult(name="endSalary", type=Float.class),
//                        @ColumnResult(name="position1", type=String.class),
//                        @ColumnResult(name="skill", type=String.class),
//                        @ColumnResult(name="address", type=String.class),
//                        @ColumnResult(name="partner1", type=String.class)
//                }
//        )
//)
//@SqlResultSetMapping(
//        name = "listJobDemo",
//        classes = @ConstructorResult(
//                targetClass = JobDto.class,
//                columns = {
//                        @ColumnResult(name="jobRecruitmentId", type=String.class),
//                        @ColumnResult(name="jobTitle", type=String.class),
//                        @ColumnResult(name="hrName", type=String.class),
//                }
//        )
//)
//@NamedNativeQueries({
//        @NamedNativeQuery(name = "findAllJob",
//                query = "SELECT " +
//                        "    j.job_recruitment_id as jobRecruitmentId, " +
//                        "    j.job_title as jobTitle, " +
//                        "    j.quantity as quantity, " +
//                        "    j.form_of_work as formOfWork, " +
//                        "    j.date_start as dateStart, " +
//                        "    j.date_end as dateEnd, " +
//                        "    p.position_name as position1, " +
//                        "    s.skill_name as skill " +
//                        "FROM " +
//                        "job_recruitment as j " +
//                        "JOIN partner as pn on j.partner_id=pn.partner_id " +
//                        "JOIN location as l on j.location_city_id=l.city_id " +
//                        "JOIN (job_work_skill as jw join skill as s on jw.skill_id=s.skill_id) on j.job_recruitment_id=jw.job_recruitment_id " +
//                        "JOIN (job_position as jp join position_user as p on jp.position_id=p.position_id) on j.job_recruitment_id=jp.job_recruitment_id " +
//                        "WHERE j.enable=1" ,
//                resultSetMapping="findAllJob"
//        ),
//        @NamedNativeQuery(name = "findJobDetail",
//                query = "SELECT " +
//                        "    j.job_recruitment_id as jobRecruitmentId, " +
//                        "    j.job_title as jobTitle, " +
//                        "    j.quantity as quantity, " +
//                        "    j.form_of_work as formOfWork, " +
//                        "    j.date_start as dateStart, " +
//                        "    j.date_end as dateEnd, " +
//                        "    j.start_salary as startSalary, " +
//                        "    j.end_salary as endSalary, " +
//                        "    p.position_name as position1, " +
//                        "    s.skill_name as skill, " +
//                        "    l.address as address, " +
//                        "    pn.partner_name as partner1 " +
//                        "FROM " +
//                        "job_recruitment as j " +
//                        "JOIN partner as pn on j.partner_id=pn.partner_id " +
//                        "JOIN location as l on j.location_city_id=l.city_id " +
//                        "JOIN (job_work_skill as jw join skill as s on jw.skill_id=s.skill_id) on j.job_recruitment_id=jw.job_recruitment_id " +
//                        "JOIN (job_position as jp join position_user as p on jp.position_id=p.position_id) on j.job_recruitment_id=jp.job_recruitment_id " +
//                        "WHERE j.job_recruitment_id = :id " ,
//                resultSetMapping="findJobDetail"
//        ),
//        @NamedNativeQuery(name = "listJobDemo",
//                query = "SELECT " +
//                        "    j.job_recruitment_id as jobRecruitmentId, " +
//                        "    j.job_title as jobTitle, " +
//                        "    u.name as hrName " +
//                        "FROM " +
//                        "    job_recruitment as j " +
//                        "JOIN user u on j.user_id=u.user_id " +
//                        "WHERE j.enable=1 " ,
//                resultSetMapping="listJobDemo"
//        )
//})

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


    // construct hien thi job len man hinh chinh
    public JobDto(String jobRecruitmentId, String jobTitle, String hrName) {
        this.jobRecruitmentId = jobRecruitmentId;
        this.jobTitle = jobTitle;
        this.hrName = hrName;
    }
}
