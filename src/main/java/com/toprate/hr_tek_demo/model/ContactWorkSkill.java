package com.toprate.hr_tek_demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact_work_skill")
public class ContactWorkSkill {
    // FK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_candidate_id")
    private String contactCandidateId;

    @Column(name = "description")
    private String description;

    @Column(name = "skill_year_experience")
    private float skillYearExperience;

    // FK
    @Column(name = "skill_id")
    private int skillId;

    // FK
    @Column(name = "level_id")
    private int lavelId;

}
