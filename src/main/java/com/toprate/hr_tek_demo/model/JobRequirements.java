package com.toprate.hr_tek_demo.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@SqlResultSetMapping(
        name = "findAllJob",
        classes = @ConstructorResult(
                targetClass = JobRequirements.class,
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
                targetClass = JobRequirements.class,
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
@Getter
@Setter
@Table(name = "job_recruitment")
public class JobRequirements {

    @Id
    @Column(name = "job_recruitment_id")
    private String jobRecruitmentId;

    @Column(name = "job_title")
    private String jobTitle;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "form_of_work")
    private String formOfWork;


    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "start_salary")
    private float startSalary;

    @Column(name = "end_salary")
    private float endSalary;

    @Column(name = "description")
    private String description;

    @Column(name = "enable")
    private int enable;

    @Transient
    private String position1;

    @Transient
    private String skill;

    @Transient
    private String address;

    @Transient
    private String partner1;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<AssignHrJob> assignHrJobList;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<JobWorkSkill> jobWorkSkills;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<JobPosition> jobPositionList;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<TakeCareTransaction> takeCareTransactionList;


    //FK
    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false) // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Partner partner;

    //FK
    @ManyToOne
    @JoinColumn(name = "location_city_id", nullable = false) // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Location location;

    //FK
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user;

    public JobRequirements() {

    }

    // construct lay du lieu cac job dang tuyen dung
    public JobRequirements(String jobRecruitmentId, String jobTitle, int quantity, String formOfWork, Date dateStart, Date dateEnd, String position1, String skill) {
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

    public JobRequirements(String jobRecruitmentId, String jobTitle, int quantity, String formOfWork, Date dateStart, Date dateEnd, float startSalary, float endSalary, String position1, String skill, String address, String partner1) {
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
