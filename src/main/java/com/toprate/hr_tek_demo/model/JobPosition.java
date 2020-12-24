package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "job_position")
public class JobPosition {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "job_posotion_id")
    private int jobPositionId;


    @Column(name = "description")
    private String description;

    // FK
    @Column(name = "job_id")
    private String jobId;
    // FK

    @Column(name = "position_id")
    private int positionId;

}
