package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;
import com.toprate.hr_tek_demo.model.Status;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class SearchDto {
    String keyWord;
    Status status;
    Float yearExp;
    String Level;
    String isBlackList;
    List<ContactWorkSkill> contactWorkSkillList;
    List<ContactPosition> contactPositionList;

    public SearchDto() {
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getYearExp() {
        return yearExp;
    }

    public void setYearExp(Float yearExp) {
        this.yearExp = yearExp;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getIsBlackList() {
        return isBlackList;
    }

    public void setIsBlackList(String isBlackList) {
        this.isBlackList = isBlackList;
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
}
