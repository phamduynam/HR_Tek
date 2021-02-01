package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 17:51 29/01/2021
 */
@Getter
@Setter
public class JobRequirementDTO {

    private String jobRecruitmentId;

    private String jobTitle;

    private String level;

    private Float yearExperience;

    private String jobWorkSkills;

    private String jobPositionList;

    private boolean takeCareTransactionList;
}
