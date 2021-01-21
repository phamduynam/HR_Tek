package com.toprate.hr_tek_demo.model;

import com.toprate.hr_tek_demo.dto.ContactDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.toprate.hr_tek_demo.generators.GeneratorContactId")
    @Column(name = "candidate_id")
    private String candidateId;

    @Column(name = "candidate_name")
    private String candidateName;

    @Column(name = "birth_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    private String email1;

    @Column(name = "email_2")
    private String email2;

    @Column(name = "phone_1")
    private String phone1;

    @Column(name = "phone_2")
    private String phone2;

    @Column(name = "levels")
    private String levels;

    @Column(name = "is_enable" ,columnDefinition = "boolean default true")
    private boolean isEnable;

    @Column
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,orphanRemoval=true) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<AssignHr> assignHrList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval=true)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ContactWorkSkill> contactWorkSkillList;


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL , orphanRemoval=true)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ContactPosition> contactPositionList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval=true)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<TakeCareTransaction> takeCareTransactionList;


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL , orphanRemoval=true)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<CV> cvList;

    @ManyToOne
    @JoinColumn(name = "user_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user;

    public ContactDto convertToDto() {
        ContactDto contactDto = new ContactDto();
        contactDto.setCandidateId(this.getCandidateId());
        contactDto.setCandidateName(this.getCandidateName());
        contactDto.setAddress(this.getAddress());
        contactDto.setBirthDay(this.getBirthDay());
        contactDto.setSex(this.getSex());
        contactDto.setYearExperience(this.getYearExperience());
        contactDto.setWorkLocation(this.getWorkLocation());
        contactDto.setPhone1(this.getPhone1());
        contactDto.setPhone2(this.getPhone2());
        contactDto.setEmail1(this.getEmail1());
        contactDto.setContactWorkSkillList(this.getContactWorkSkillList());
        contactDto.setEnable(this.isEnable());
        contactDto.setLevels(this.getLevels());

        ArrayList<Position> listPosition = new ArrayList<>();
        if(this.getContactPositionList() != null){
            for (ContactPosition contactPosition : contactPositionList) {
                listPosition.add(contactPosition.getPosition());
            }
        }

        contactDto.setPositionList(listPosition);

        return contactDto;
    }

    public void addContactWorkSkill(ContactWorkSkill contactWorkSkill){
        contactWorkSkill.setContact(this);
        this.contactWorkSkillList.add(contactWorkSkill);
    }

    public void addContactPosition(ContactPosition contactPosition){
        contactPosition.setContact(this);
        this.contactPositionList.add(contactPosition);
    }

    public void addCv(CV cv){
        this.cvList.add(cv);
    }

    public void addAssignHr(AssignHr assignHr) {
        this.assignHrList.add(assignHr);
    }

    public void addTakeCareTransaction(TakeCareTransaction takeCareTransaction) {
        this.takeCareTransactionList.add(takeCareTransaction);
    }

    public void removeContactWorkSkill(ContactWorkSkill contactWorkSkill) {
        contactWorkSkill.setContact(null);
        this.contactWorkSkillList.remove(contactWorkSkill);
    }

    public void removeContactPosition(ContactPosition contactPosition) {
        contactPosition.setContact(null);
        this.contactPositionList.remove(contactPosition);
    }

    public void removeTakeCareTransaction(TakeCareTransaction takeCareTransaction) {
        this.takeCareTransactionList.remove(takeCareTransaction);
    }

    public void removeAssignHr(AssignHr assignHr) {
        this.assignHrList.remove(assignHr);
    }

    public Contact(String candidateId, String candidateName, Date birthDay, String address, String linkCv, Float yearExperience, String sex, Boolean isBlackList, String workLocation, String email1, String email2, String phone1, String phone2, String levels, boolean isEnable, List<AssignHr> assignHrList, List<ContactWorkSkill> contactWorkSkillList, List<ContactPosition> contactPositionList, List<TakeCareTransaction> takeCareTransactionList, List<CV> cvList, Users user) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.birthDay = birthDay;
        this.address = address;
        this.linkCv = linkCv;
        this.yearExperience = yearExperience;
        this.sex = sex;
        this.isBlackList = isBlackList;
        this.workLocation = workLocation;
        this.email1 = email1;
        this.email2 = email2;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.levels = levels;
        this.isEnable = isEnable;
        this.assignHrList = assignHrList;
        this.contactWorkSkillList = contactWorkSkillList;
        this.contactPositionList = contactPositionList;
        this.takeCareTransactionList = takeCareTransactionList;
        this.cvList = cvList;
        this.user = user;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Contact() {
        this.setEnable(true);
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



}
