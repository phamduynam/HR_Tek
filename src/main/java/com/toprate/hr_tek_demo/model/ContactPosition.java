package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "contact_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "position_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Position position;


    public ContactPosition(Contact contact, Position position) {
        this.contact = contact;
        this.position = position;
    }

}
