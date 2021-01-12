package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.Partner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(
        name = "findAllJob",
        classes = @ConstructorResult(
                targetClass = JobDto.class,
                columns = {
                        @ColumnResult(name="jobRecruitmentId", type=String.class),
                        @ColumnResult(name="jobTitle", type=String.class),
                        @ColumnResult(name="quantity", type=Integer.class),
                        @ColumnResult(name="formOfWork", type=String.class),
                        @ColumnResult(name="dateStart", type=Date.class),
                        @ColumnResult(name="dateEnd", type=Date.class),
                        @ColumnResult(name="position1", type=String.class),
                        @ColumnResult(name="skill", type=String.class),
                }
        )
)

@SqlResultSetMapping(
        name = "findJobDetail",
        classes = @ConstructorResult(
                targetClass = JobDto.class,
                columns = {
                        @ColumnResult(name="jobRecruitmentId", type=String.class),
                        @ColumnResult(name="jobTitle", type=String.class),
                        @ColumnResult(name="quantity", type=Integer.class),
                        @ColumnResult(name="formOfWork", type=String.class),
                        @ColumnResult(name="dateStart", type=Date.class),
                        @ColumnResult(name="dateEnd", type=Date.class),
                        @ColumnResult(name="startSalary", type=Float.class),
                        @ColumnResult(name="endSalary", type=Float.class),
                        @ColumnResult(name="position1", type=String.class),
                        @ColumnResult(name="skill", type=String.class),
                        @ColumnResult(name="address", type=String.class),
                        @ColumnResult(name="partner1", type=String.class)
                }
        )
)


@NamedNativeQueries({
        @NamedNativeQuery(name = "findAllJob",
                query = "SELECT " +
                        "    j.job_recruitment_id as jobRecruitmentId, " +
                        "    j.job_title as jobTitle, " +
                        "    j.quantity as quantity, " +
                        "    j.form_of_work as formOfWork, " +
                        "    j.date_start as dateStart, " +
                        "    j.date_end as dateEnd, " +
                        "    p.position_name as position1, " +
                        "    s.skill_name as skill " +
                        "FROM " +
                        "job_recruitment as j " +
                        "JOIN partner as pn on j.partner_id=pn.partner_id " +
                        "JOIN location as l on j.location_city_id=l.city_id " +
                        "JOIN (job_work_skill as jw join skill as s on jw.skill_id=s.skill_id) on j.job_recruitment_id=jw.job_recruitment_id " +
                        "JOIN (job_position as jp join position_user as p on jp.position_id=p.position_id) on j.job_recruitment_id=jp.job_recruitment_id " +
                        "WHERE j.enable=1" ,
                resultSetMapping="findAllJob"
        ),
        @NamedNativeQuery(name = "findJobDetail",
                query = "SELECT " +
                        "    j.job_recruitment_id as jobRecruitmentId, " +
                        "    j.job_title as jobTitle, " +
                        "    j.quantity as quantity, " +
                        "    j.form_of_work as formOfWork, " +
                        "    j.date_start as dateStart, " +
                        "    j.date_end as dateEnd, " +
                        "    j.start_salary as startSalary, " +
                        "    j.end_salary as endSalary, " +
                        "    p.position_name as position1, " +
                        "    s.skill_name as skill, " +
                        "    l.address as address, " +
                        "    pn.partner_name as partner1 " +
                        "FROM " +
                        "job_recruitment as j " +
                        "JOIN partner as pn on j.partner_id=pn.partner_id " +
                        "JOIN location as l on j.location_city_id=l.city_id " +
                        "JOIN (job_work_skill as jw join skill as s on jw.skill_id=s.skill_id) on j.job_recruitment_id=jw.job_recruitment_id " +
                        "JOIN (job_position as jp join position_user as p on jp.position_id=p.position_id) on j.job_recruitment_id=jp.job_recruitment_id " +
                        "WHERE j.job_recruitment_id = :id " ,
                resultSetMapping="findJobDetail"
        )
})

@Entity
@Data
@Getter
@Setter
public class JobDto {
    @Id
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

    private String position1;

    private String skill;

    private String address;

    private String partner1;

    private int positionId;

    private int skillId;


    // constructor mac dinh
    public JobDto() {}

    // construct lay du lieu toan bo cac job dang tuyen dung
    public JobDto(String jobRecruitmentId, String jobTitle, int quantity, String formOfWork, Date dateStart, Date dateEnd, String position1, String skill) {
        this.jobRecruitmentId = jobRecruitmentId;
        this.jobTitle = jobTitle;
        this.quantity = quantity;
        this.formOfWork = formOfWork;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.position1 = position1;
        this.skill = skill;
    }

    // construct lay du lieu chi tiet 1 job

    public JobDto(String jobRecruitmentId, String jobTitle, int quantity, String formOfWork, Date dateStart, Date dateEnd, float startSalary, float endSalary, String position1, String skill, String address, String partner1) {
        this.jobRecruitmentId = jobRecruitmentId;
        this.jobTitle = jobTitle;
        this.quantity = quantity;
        this.formOfWork = formOfWork;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.startSalary = startSalary;
        this.endSalary = endSalary;
        this.position1 = position1;
        this.skill = skill;
        this.address = address;
        this.partner1 = partner1;
    }
}
