package com.toprate.hr_tek.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "note_status")
public class NoteStatus {
    //FK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_status_id")
    private int noteStatusId;

    @Column(name = "note_description")
    private String noteDescription;

    @ManyToOne
    @JoinColumn(name = "status_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Status status;

}
