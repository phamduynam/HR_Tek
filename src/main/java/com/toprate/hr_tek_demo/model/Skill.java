package com.toprate.hr_tek_demo.model;

import lombok.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int skillId;

    @Column(name = "skill_name")
    private String skillName;


    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL,orphanRemoval=true) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ContactWorkSkill> contactWorkSkillList;



    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL,orphanRemoval=true) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<JobWorkSkill> jobWorkSkillList;

    public Skill() {
    }

    public Skill(int skillId) {
        this.skillId = skillId;
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public Skill(List<ContactWorkSkill> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<ContactWorkSkill> getContactWorkSkillList() {
        return contactWorkSkillList;
    }

    public void setContactWorkSkillList(List<ContactWorkSkill> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

    public List<JobWorkSkill> getJobWorkSkillList() {
        return jobWorkSkillList;
    }

    public void setJobWorkSkillList(List<JobWorkSkill> jobWorkSkillList) {
        this.jobWorkSkillList = jobWorkSkillList;
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
