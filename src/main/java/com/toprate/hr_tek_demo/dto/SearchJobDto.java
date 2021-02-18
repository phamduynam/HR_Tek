package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.JobWorkSkill;
import com.toprate.hr_tek_demo.model.Position;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SearchJobDto {
    private Float yearExperience;

    private String level;

    private String location;

    private String partner;

    private List<Position> positionList;

    private List<JobWorkSkill> jobWorkSkillList;
}
