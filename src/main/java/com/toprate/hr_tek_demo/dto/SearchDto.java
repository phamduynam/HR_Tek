package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.Status;
import lombok.Data;

import java.util.List;

@Data
public class SearchDto {
    String keyWord;
    Status status;
    Float yearExp;
    String Level;
    String isBlackList;
    List<Integer> contactWorkSkillList;
    List<Integer> contactPositionList;

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

    public List<Integer> getContactWorkSkillList() {
        return contactWorkSkillList;
    }

    public void setContactWorkSkillList(List<Integer> contactWorkSkillList) {
        this.contactWorkSkillList = contactWorkSkillList;
    }

    public List<Integer> getContactPositionList() {
        return contactPositionList;
    }

    public void setContactPositionList(List<Integer> contactPositionList) {
        this.contactPositionList = contactPositionList;
    }
}
