package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int skillId;

    @Column(name = "skill_name")
    private String skillName;


    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL,orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ContactWorkSkill> contactWorkSkillList;



    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL,orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<JobWorkSkill> jobWorkSkillList;

    public Skill(int skillId) {
        this.skillId = skillId;
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public Skill(List<ContactWorkSkill> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

    public void removeContactWorkSkill(ContactWorkSkill contactWorkSkill){
        contactWorkSkill.setContact(null);
        this.contactWorkSkillList.remove(contactWorkSkill);
    }

    public void deleteJobWorkSkill(JobWorkSkill jobWorkSkill) {
        jobWorkSkill.setJobRequirements(null);
        this.contactWorkSkillList.remove(jobWorkSkill);
    }

    public void addContactWorkSkill(ContactWorkSkill contactWorkSkill){
        contactWorkSkill.setSkill(this);
        this.contactWorkSkillList.add(contactWorkSkill);
    }
}
