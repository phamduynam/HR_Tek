package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.ContactPosition;
import com.toprate.hr_tek_demo.model.ContactWorkSkill;

import java.util.List;

public class SearchDto {


    Float yearExp;
    String Level;
    String blackList;
    List<ContactWorkSkill> contactWorkSkillList;
    List<ContactPosition> contactPositionList;
}
