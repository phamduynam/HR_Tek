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

    public int getContactWorkSkillId() {
        return contactWorkSkillId;
    }

    public void setContactWorkSkillId(int contactWorkSkillId) {
        this.contactWorkSkillId = contactWorkSkillId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSkillYearExperience() {
        return skillYearExperience;
    }

    public void setSkillYearExperience(float skillYearExperience) {
        this.skillYearExperience = skillYearExperience;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}