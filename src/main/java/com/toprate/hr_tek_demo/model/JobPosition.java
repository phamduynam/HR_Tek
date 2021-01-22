package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "job_position")
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_positon_id")
    private int jobPositionId;


    @Column(name = "description")
    private String description;


    //FK
    @ManyToOne
    @JoinColumn(name = "job_recruitment_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements jobRequirements;
    //FK
    @ManyToOne
    @JoinColumn(name = "position_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Position position;

    public JobPosition(Position position) {
        this.position = position;
    }
    public JobPosition(JobRequirements jobRequirement) {
        this.jobRequirements = jobRequirement;
    }

    public JobPosition(int jobPositionId, String description, JobRequirements jobRequirements, Position position) {
        this.jobPositionId = jobPositionId;
        this.description = description;
        this.jobRequirements = jobRequirements;
        this.position = position;
    }
}
