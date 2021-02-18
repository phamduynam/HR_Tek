package com.toprate.hr_tek_demo.model;


import lombok.*;

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
    @Column(name = "contact_work_skill_id")
    private int contactWorkSkillId;

    @Column(name = "description")
    private String description;

    @Column(name = "skill_year_experience")
    private float skillYearExperience;

    // FK
    @ManyToOne
    @JoinColumn(name = "skill_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Skill skill;

    //FK
    @ManyToOne
    @JoinColumn(name = "contact_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

    public ContactWorkSkill(Skill skill) {
        this.skill = skill;
    }

    public ContactWorkSkill(Skill skill, Contact contact) {
        this.contact = contact;
        this.skill = skill;
    }

    public ContactWorkSkill(Contact contact) {
        this.contact = contact;
    }

    public ContactWorkSkill(Skill skill, Float aFloat, String skillDe) {
        this.skill = skill;
        this.skillYearExperience = aFloat;
        this.description = skillDe;
    }

    @Override
    public String toString() {
        return "ContactWorkSkill{" +
                "contactWorkSkillId=" + contactWorkSkillId +
                ", description='" + description + '\'' +
                ", skillYearExperience=" + skillYearExperience +
                ", skill=" + skill +
                ", contact=" + contact +
                '}';
    }
}
