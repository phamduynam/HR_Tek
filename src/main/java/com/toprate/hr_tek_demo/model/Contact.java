package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact")
public class Contact {
    @Id
    @Column(name = "candidate_id")
    private String assignHrId;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "birth_day")
    private Date nirthDay;

    @Column(name = "address")
    private String addess;

    @Column(name = "link_cv")
    private String linkCv;

    @Column(name = "year_experience")
    private Float yearExperience;

    @Column(name = "sex")
    private String sex;

    @Column(name = "is_black_list")
    private Boolean isBlackList;

    @Column(name = "work_location")
    private String workLocation;

    @Column(name = "email_1")
    private String email_1;

    @Column(name = "email_2")
    private String email_2;

    @Column(name = "phone_1")
    private String phone_1;

    @Column(name = "phone_2")
    private String phone_2;

    // FK
    @Column(name = "user_id")
    private String userId;

}
