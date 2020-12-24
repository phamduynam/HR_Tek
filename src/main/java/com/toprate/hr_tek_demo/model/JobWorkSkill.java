package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "job_work_skill")
public class JobWorkSkill {
    //FK
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "job_recruitment_id")
    private String jobRecruitmentId;

    @Column(name = "description")
    private String description;

    @Column(name = "skill_experience")
    private float skillExperience;

    //FK
    @Column(name = "skill_id")
    private int skillId;

}
