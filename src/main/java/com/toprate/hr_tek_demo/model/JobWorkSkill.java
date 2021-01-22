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
    @JoinColumn(name = "skill_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Skill skill;

    @ManyToOne
    @JoinColumn(name = "job_recruitment_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements jobRequirements;

    public JobWorkSkill(int jobworkSkillId, String description, float skillExperience, Skill skill, JobRequirements jobRequirements) {
        this.jobworkSkillId = jobworkSkillId;
        this.description = description;
        this.skillExperience = skillExperience;
        this.skill = skill;
        this.jobRequirements = jobRequirements;
    }
}
