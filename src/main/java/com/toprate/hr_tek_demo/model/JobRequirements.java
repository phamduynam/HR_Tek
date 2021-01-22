package com.toprate.hr_tek_demo.model;

import com.toprate.hr_tek_demo.dto.JobDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(name = "levels")
    private String level;

    @Column(name = "year_experience")
    private Float yearExperience;

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

    public JobRequirements() {
        this.setEnable(1);
    }

    public JobDto convertToJobDto() {
        JobDto jobDto = new JobDto();
        jobDto.setJobRecruitmentId(this.jobRecruitmentId);
        jobDto.setJobTitle(this.jobTitle);
        jobDto.setQuantity(this.quantity);
        jobDto.setFormOfWork(this.formOfWork);
        jobDto.setDateStart(this.dateStart);
        jobDto.setDateEnd(this.dateEnd);
        jobDto.setStartSalary(this.startSalary);
        jobDto.setDescription(this.description);
        jobDto.setLevel(this.level);
        jobDto.setYearExperience(this.yearExperience);
        jobDto.setJobWorkSkills(this.jobWorkSkills);
        jobDto.setPartner(this.partner);
        jobDto.setLocation(this.location);
        jobDto.setUser(this.user);

        ArrayList<Position> listPosition = new ArrayList<>();
        if(this.getJobPositionList() != null){
            for (JobPosition jobPosition : this.getJobPositionList()) {
                listPosition.add(jobPosition.getPosition());
            }
        }
        jobDto.setPositionList(listPosition);

        return jobDto;
    }

    public JobRequirements(String jobRecruitmentId, String jobTitle, int quantity, String formOfWork, Date dateStart, Date dateEnd, float startSalary, float endSalary, String description, int enable, String level, Float yearExperience, List<AssignHrJob> assignHrJobList, List<JobWorkSkill> jobWorkSkills, List<JobPosition> jobPositionList, List<TakeCareTransaction> takeCareTransactionList, Partner partner, Location location, Users user) {
        this.jobRecruitmentId = jobRecruitmentId;
        this.jobTitle = jobTitle;
        this.quantity = quantity;
        this.formOfWork = formOfWork;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.startSalary = startSalary;
        this.endSalary = endSalary;
        this.description = description;
        this.enable = enable;
        this.level = level;
        this.yearExperience = yearExperience;
        this.assignHrJobList = assignHrJobList;
        this.jobWorkSkills = jobWorkSkills;
        this.jobPositionList = jobPositionList;
        this.takeCareTransactionList = takeCareTransactionList;
        this.partner = partner;
        this.location = location;
        this.user = user;
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
