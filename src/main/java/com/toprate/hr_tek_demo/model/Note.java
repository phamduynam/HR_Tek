package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "note")
public class Note implements Serializable {
    //FK
    @Id
    @Column(name = "user_id")
    private String userId;
    //FK
    @Id
    @Column(name = "takecare_transaction_id")
    private int takecareTransactionId;


    @Column(name = "time_create")
    private Date timeCreate;

    @Column(name = "description")
    private String description;

}
