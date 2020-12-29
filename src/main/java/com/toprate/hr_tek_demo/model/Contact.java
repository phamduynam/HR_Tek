package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact")
public class Contact {
    @Id
    @Column(name = "candidate_id")
    private String candidateId;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "address")
    private String address;

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

    @Column(name = "levels")
    private String levels;

    @Column(name = "is_enable")
    private boolean is_enable;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<AssignHr> assignHrList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ContactWorkSkill> contactWorkSkillList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ContactPosition> contactPositionList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<TakeCareTransaction> takeCareTransactionList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<CV> cvList;

    @ManyToOne
    @JoinColumn(name = "user_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user;


    @Override
    public String toString() {
        return "Contact{" +
                "candidateId='" + candidateId + '\'' +
                ", candidateName='" + candidateName + '\'' +
                ", birthDay=" + birthDay +
                ", address='" + address + '\'' +
                ", linkCv='" + linkCv + '\'' +
                ", yearExperience=" + yearExperience +
                ", sex='" + sex + '\'' +
                ", isBlackList=" + isBlackList +
                ", workLocation='" + workLocation + '\'' +
                ", email_1='" + email_1 + '\'' +
                ", email_2='" + email_2 + '\'' +
                ", phone_1='" + phone_1 + '\'' +
                ", phone_2='" + phone_2 + '\'' +
                ", levels='" + levels + '\'' +
                '}';
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkCv() {
        return linkCv;
    }

    public void setLinkCv(String linkCv) {
        this.linkCv = linkCv;
    }

    public Float getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(Float yearExperience) {
        this.yearExperience = yearExperience;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getBlackList() {
        return isBlackList;
    }

    public void setBlackList(Boolean blackList) {
        isBlackList = blackList;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getEmail_1() {
        return email_1;
    }

    public void setEmail_1(String email_1) {
        this.email_1 = email_1;
    }

    public String getEmail_2() {
        return email_2;
    }

    public void setEmail_2(String email_2) {
        this.email_2 = email_2;
    }

    public String getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(String phone_1) {
        this.phone_1 = phone_1;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(String phone_2) {
        this.phone_2 = phone_2;
    }

    public List<AssignHr> getAssignHrList() {
        return assignHrList;
    }

    public void setAssignHrList(List<AssignHr> assignHrList) {
        this.assignHrList = assignHrList;
    }

    public List<ContactWorkSkill> getContactWorkSkillList() {
        return contactWorkSkillList;
    }

    public void setContactWorkSkillList(List<ContactWorkSkill> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

    public List<ContactPosition> getContactPositionList() {
        return contactPositionList;
    }

    public void setContactPositionList(List<ContactPosition> contactPositionList) {
        this.contactPositionList = contactPositionList;
    }

    public List<TakeCareTransaction> getTakeCareTransactionList() {
        return takeCareTransactionList;
    }

    public void setTakeCareTransactionList(List<TakeCareTransaction> takeCareTransactionList) {
        this.takeCareTransactionList = takeCareTransactionList;
    }

    public List<CV> getCvList() {
        return cvList;
    }

    public void setCvList(List<CV> cvList) {
        this.cvList = cvList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isIs_enable() {
        return is_enable;
    }

    public void setIs_enable(boolean is_enable) {
        this.is_enable = is_enable;
    }
}
