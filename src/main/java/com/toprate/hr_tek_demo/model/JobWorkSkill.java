package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "job_work_skill")
public class JobWorkSkill {
    //FK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_work_skill_id")
    private int jobworkSkillId;

    @Column(name = "description")
    private String description;

    @Column(name = "skill_experience")
    private float skillExperience;

    //FK
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false) // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "job_recruitment_id", nullable = false) // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements jobRequirements;
}
