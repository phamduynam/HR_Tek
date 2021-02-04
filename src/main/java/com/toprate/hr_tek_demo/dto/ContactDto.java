package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
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

    private List<Skill> skillList;

    private List<TakeCareTransaction> takeCareTransactionList;

    private List<CV> cvList;



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
        contact.setBlackList(this.getIsBlackList());

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
        this.setIsBlackList(false);
    }

}
