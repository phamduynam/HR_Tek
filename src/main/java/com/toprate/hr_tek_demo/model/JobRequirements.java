package com.toprate.hr_tek_demo.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "job_recruitment")
public class JobRequirements {

    @Id
    @GeneratedValue(generator = "my_generatorJob")
    @GenericGenerator(name = "my_generatorJob", strategy = "com.toprate.hr_tek_demo.generators.GeneratorJobId")
    @Column(name = "job_recruitment_id")
    private String jobRecruitmentId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "form_of_work")
    private String formOfWork;

    @Column(name = "date_start")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @Column(name = "date_end")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @Transient
    private int positionId;

    @Transient
    private int skillId;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khong sử dụng trong toString()
    private List<AssignHrJob> assignHrJobList;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khong sử dụng trong toString()
    private List<JobWorkSkill> jobWorkSkills;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khong sử dụng trong toString()
    private List<JobPosition> jobPositionList;

    @OneToMany(mappedBy = "jobRequirements", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khong sử dụng trong toString()
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

    public JobRequirements(String jobRecruitmentId) {
        this.jobRecruitmentId = jobRecruitmentId;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
