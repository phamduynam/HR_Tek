package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionId;

    @Column(name = "position_name")
    private String positionName;


    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ContactPosition> contactPositionList;


    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<JobPosition> jobPositionList;


    public Position(int positionId) {
        this.positionId = positionId;
    }

    public Position(int positionId, String positionName, List<ContactPosition> contactPositionList, List<JobPosition> jobPositionList) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.contactPositionList = contactPositionList;
        this.jobPositionList = jobPositionList;
    }

    public Position(String positionName) {
        this.positionName = positionName;
    }

    public Position(List<ContactPosition> contactPositionList) {
        this.contactPositionList = contactPositionList;
    }

    public void addContactPosition(ContactPosition contactPosition){
        this.contactPositionList.add(contactPosition);
        contactPosition.setPosition(this);
    }

    public void removeContactPosition(ContactPosition contactPosition){
        this.contactPositionList.remove(contactPosition);
        contactPosition.setPosition(null);
    }

    public void deleteJobPosition(JobPosition jobPosition) {
        this.jobPositionList.remove(jobPosition);
        jobPosition.setPosition(null);
    }
}
