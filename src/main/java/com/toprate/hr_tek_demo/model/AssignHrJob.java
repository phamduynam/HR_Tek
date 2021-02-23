package com.toprate.hr_tek_demo.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "description")
    private String description;

    // Fk
    @ManyToOne
    @JoinColumn(name = "job_recruitment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements jobRequirements;

    // FK
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user_manager;

    // FK
    @ManyToOne
    @JoinColumn(name = "hr_id") // thông qua khóa ngoại hr_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user_hr;

}
