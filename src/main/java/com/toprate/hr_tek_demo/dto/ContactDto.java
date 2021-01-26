package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.model.Position;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ContactDto {

    private String candidateId;

    private String candidateName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private String address;

    private String linkCv;

    private Float yearExperience;

    private String sex;

    private Boolean isBlackList;

    private String workLocation;

    private String email1;

    private String email2;

    private String phone1;

    private String phone2;

    private String levels;

    private boolean isEnable;

    private List<ContactWorkSkill> contactWorkSkillList;

    private List<Position> positionList;

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public List<ContactWorkSkill> getContactWorkSkillList() {
        return contactWorkSkillList;
    }

    public void setContactWorkSkillList(List<ContactWorkSkill> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

    public Contact convertToModel() {
        Contact contact = new Contact();
        contact.setCandidateId(this.getCandidateId());
        contact.setCandidateName(this.getCandidateName());
        contact.setAddress(this.getAddress());
        contact.setBirthDay(this.getBirthDay());
        contact.setWorkLocation(this.getWorkLocation());
        contact.setYearExperience(this.getYearExperience());
        contact.setLevels(this.getLevels());
        contact.setPhone1(this.getPhone1());
        contact.setPhone2(this.getPhone2());
        contact.setEmail1(this.getEmail1());
        contact.setEmail2(this.getEmail2());
        contact.setSex(this.getSex());
        contact.setLinkCv(this.getLinkCv());
        contact.setEnable(this.isEnable());
        contact.setBlackList(this.getBlackList());

        // Add skill not null
        List<ContactWorkSkill> contactWorkSkillList = new ArrayList<>();

        if(this.getContactWorkSkillList() != null){
            for (ContactWorkSkill contactWorkSkill : this.getContactWorkSkillList()) {
                if (contactWorkSkill.getSkill() != null) {
                    contactWorkSkillList.add(contactWorkSkill);
                }
            }
        }
        contact.setContactWorkSkillList(contactWorkSkillList);
        this.setContactWorkSkillList(contactWorkSkillList);

        List<ContactPosition> contactPositionList = new ArrayList<>();

        if(this.getPositionList() != null){
            for (Position position : this.positionList) {
                contactPositionList.add(new ContactPosition(position));
            }
        }

        contact.setContactPositionList(contactPositionList);
        return contact;
    }

    public ContactDto() {
        this.setEnable(true);
        this.setBlackList(false);
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

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
