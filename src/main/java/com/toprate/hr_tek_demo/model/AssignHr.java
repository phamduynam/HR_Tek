package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "assign_hr")
public class AssignHr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_hr_id")
    private int assignHrId;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;
    // FK
    @Column(name = "contact_candidate_id")
    private String contactCandidateId;
    // FK
    @Column(name = "manager_id")
    private String managerId;
    // FK
    @Column(name = "hr_id")
    private String hrId;

}
