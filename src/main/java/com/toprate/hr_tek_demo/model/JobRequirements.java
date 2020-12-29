package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "job_recruitment")
public class JobRequirements {

    @Id
    @Column(name = "job_recruiment_id")
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
    @JoinColumn(name = "partner_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Partner partner;

    //FK
    @ManyToOne
    @JoinColumn(name = "location_city_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Location location;

    //FK
    @ManyToOne
    @JoinColumn(name = "user_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user;
}
