package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "note")
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;

    @Column(name = "time_create")
    private Date timeCreate;

    @Column(name = "description")
    private String description;

    //FK
    @ManyToOne
    @JoinColumn(name = "user_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user;


    @ManyToOne
    @JoinColumn(name = "takecare_transaction_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TakeCareTransaction takeCareTransaction;

}
