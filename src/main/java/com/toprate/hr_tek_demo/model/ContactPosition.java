package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact_position")
public class ContactPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_position_id")
    private int contactPositionId;

    @Column(name = "description")
    private String description;

    // FK
    @Column(name = "contact_candidate_id")
    private String contactCandidateId;

    // FK
    @Column(name = "position_id")
    private int positionId;
}
