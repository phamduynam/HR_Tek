package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "job_recruitment")
public class JobRequirements {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    //FK
    @Column(name = "partner_id")
    private int partnerId;

    // FK
    @Column(name = "location_city_id")
    private int locationCityId;

    //FK
    @Column(name = "user_id")
    private String userId;
}
