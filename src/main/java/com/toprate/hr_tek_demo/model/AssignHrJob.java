package com.toprate.hr_tek_demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "assign_hr_job")
public class AssignHrJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_hr_job_id")
    private int assignHrJobId;

    @Column(name = "description")
    private String description;
    // Fk
    @Column(name = "assign_hr_id")
    private int assignHrId;
    // Fk
    @Column(name = "job_recruitment_id")
    private String jobRecuitmentId;


}
