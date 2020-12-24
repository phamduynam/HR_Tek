package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "note_status")
public class NoteStatus {
    //FK
    @Id
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "note_description")
    private String noteDescription;

}
