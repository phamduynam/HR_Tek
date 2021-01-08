package com.toprate.hr_tek.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionId;

    @Column(name = "position_name")
    private String positionName;


    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<ContactPosition> contactPositionList;


    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<JobPosition> jobPositionList;

    public Position() {
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


    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public List<ContactPosition> getContactPositionList() {
        return contactPositionList;
    }

    public void setContactPositionList(List<ContactPosition> contactPositionList) {
        this.contactPositionList = contactPositionList;
    }

    public List<JobPosition> getJobPositionList() {
        return jobPositionList;
    }

    public void setJobPositionList(List<JobPosition> jobPositionList) {
        this.jobPositionList = jobPositionList;
    }
}
