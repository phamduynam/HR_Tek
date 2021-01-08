package com.toprate.hr_tek.model;


import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "assign_hr_id") // thông qua khóa ngoại assign_hr_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AssignHr assignHr;

    // Fk
    @ManyToOne
    @JoinColumn(name = "job_recruitment_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements jobRequirements;


}
