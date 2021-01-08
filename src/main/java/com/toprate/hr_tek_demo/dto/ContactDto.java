package com.toprate.hr_tek_demo.dto;

import com.toprate.hr_tek_demo.model.Position;
import com.toprate.hr_tek_demo.model.Skill;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class ContactDto {

    @NotNull(message = "Name can not be null !")
    @NotEmpty(message = "Name can not be empty !")
    @Size(max = 40,min=1, message = "Name should have 1-40 character !")
    private String candidateName;

    @NotNull(message = "Please Chose BirthDay")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @NotNull(message = "Address can not be null !")
    private String address;

    private String linkCv;

    @NotNull(message = "Year Experience can not be null !")
    private Float yearExperience;

    @NotNull(message = "Sex can not be null !")
    private String sex;

    private Boolean isBlackList;

    private String workLocation;
    @NotNull(message = "Email can not be null !")
    @Email
    private String email1;
    @Email
    private String email2;

    @NotNull(message ="Phone not null!")
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String phone1;

    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String phone2;

    @NotNull(message ="Please chose level")
    private String levels;

    private Skill[] skillList;

    private Position[] positionList;

    public Skill[] getSkillList() {
        return skillList;
    }

    public void setSkillList(Skill[] skillList) {
        this.skillList = skillList;
    }

    public Position[] getPositionList() {
        return positionList;
    }

    public void setPositionList(Position[] positionList) {
        this.positionList = positionList;
    }
}
