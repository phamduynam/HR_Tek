package com.toprate.hr_tek_demo.dto;

import com.nimbusds.jose.util.ArrayUtils;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.model.Skill;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
public class ContactDto {

    private String candidateId;

    @NotBlank(message = "Name can not be blank !")
    @Size(max = 40,min=2, message = "Name should have 2-40 character !")
    private String candidateName;

    @NotNull(message = "Please Chose BirthDay")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @NotBlank(message = "Address can not be blank !")
    private String address;

    private String linkCv;

    @NotNull(message = "Year Experience can not be null !")
    private Float yearExperience;

    @NotBlank(message = "Sex can not be Blank !")
    private String sex;

    private Boolean isBlackList;

    private String workLocation;

    @NotNull(message = "Email can not be null !")
    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email is invalid !")
    private String email1;

    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email is invalid !")
    private String email2;

    @NotBlank(message ="Phone 1 not blank!")
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String phone1;


    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String phone2;

    @NotNull(message ="Please chose level !")
    private String levels;

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

//    private Skill[] skillList;
//
//    private Float[] skillYearExp;
//
//    private String[] skillDes;
//
//    private Position[] positionList;
//
//    public void addContactWorkSkill(Skill skill,Float exp, String des){
//    }

//    public void addContactPosition(Position ps){
//        this.positionList[positionList.length] = ps;
//    }
//
//    public Skill[] getSkillList() {
//        return skillList;
//    }
//
//    public void setSkillList(Skill[] skillList) {
//        this.skillList = skillList;
//    }
//
//    public Float[] getSkillYearExp() {
//        return skillYearExp;
//    }
//
//    public void setSkillYearExp(Float[] skillYearExp) {
//        this.skillYearExp = skillYearExp;
//    }
//
//    public String[] getSkillDes() {
//        return skillDes;
//    }
//
//    public void setSkillDes(String[] skillDes) {
//        this.skillDes = skillDes;
//    }
//
//    public Position[] getPositionList() {
//        return positionList;
//    }
//
//    public void setPositionList(Position[] positionList) {
//        this.positionList = positionList;
//    }

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
}
