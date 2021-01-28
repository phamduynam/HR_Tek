package com.toprate.hr_tek_demo.dto;

public class SearchJobForContactDto {
    String keyWord;

    Boolean suitableSkill;

    Boolean suitableLevel;

    Boolean suitableExp;

    public SearchJobForContactDto(String keyWord, Boolean suitableSkill, Boolean suitableLevel, Boolean suitableExp) {
        this.keyWord = keyWord;
        this.suitableSkill = suitableSkill;
        this.suitableLevel = suitableLevel;
        this.suitableExp = suitableExp;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Boolean getSuitableSkill() {
        return suitableSkill;
    }

    public void setSuitableSkill(Boolean suitableSkill) {
        this.suitableSkill = suitableSkill;
    }

    public Boolean getSuitableLevel() {
        return suitableLevel;
    }

    public void setSuitableLevel(Boolean suitableLevel) {
        this.suitableLevel = suitableLevel;
    }

    public Boolean getSuitableExp() {
        return suitableExp;
    }

    public void setSuitableExp(Boolean suitableExp) {
        this.suitableExp = suitableExp;
    }
}
