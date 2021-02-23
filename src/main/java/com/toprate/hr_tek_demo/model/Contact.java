package com.toprate.hr_tek_demo.model;

import com.toprate.hr_tek_demo.dto.ContactDto;
import lombok.*;
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
@NoArgsConstructor
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

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AssignHrContact> assignHrContactList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ContactWorkSkill> contactWorkSkillList;


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL , orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ContactPosition> contactPositionList;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TakeCareTransaction> takeCareTransactionList;


    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL , orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CV> cvList;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
        contactDto.setEmail2(this.getEmail2());
        contactDto.setEnable(this.isEnable());
        contactDto.setLevels(this.getLevels());
        contactDto.setIsBlackList(this.getIsBlackList());
        contactDto.setContactWorkSkillList(this.getContactWorkSkillList());

        ArrayList<Skill> skillList = new ArrayList<>();
        if(this.getContactWorkSkillList() != null){
            for (ContactWorkSkill contactWorkSkill : contactWorkSkillList){
                skillList.add(contactWorkSkill.getSkill());
            }
        }

        ArrayList<Position> listPosition = new ArrayList<>();
        if(this.getContactPositionList() != null){
            for (ContactPosition contactPosition : contactPositionList) {
                listPosition.add(contactPosition.getPosition());
            }
        }

        contactDto.setPositionList(listPosition);
        contactDto.setSkillList(skillList);
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

    public void removeContactWorkSkill(ContactWorkSkill contactWorkSkill) {
        contactWorkSkill.setContact(null);
        this.contactWorkSkillList.remove(contactWorkSkill);
    }

    public void removeContactPosition(ContactPosition contactPosition) {
        contactPosition.setContact(null);
        this.contactPositionList.remove(contactPosition);
    }

    public Contact(String candidateId, String candidateName, Date birthDay, String address, String linkCv, Float yearExperience, String sex, Boolean isBlackList, String workLocation, String email1, String email2, String phone1, String phone2, String levels, boolean isEnable, List<AssignHrContact> assignHrContactList, List<ContactWorkSkill> contactWorkSkillList, List<ContactPosition> contactPositionList, List<TakeCareTransaction> takeCareTransactionList, List<CV> cvList, Users user) {
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
        this.contactWorkSkillList = contactWorkSkillList;
        this.contactPositionList = contactPositionList;
        this.takeCareTransactionList = takeCareTransactionList;
        this.cvList = cvList;
        this.user = user;
    }

}
