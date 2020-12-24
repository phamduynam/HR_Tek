package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "takecare_transaction")
public class TakeCareTransaction {

    @Id
    @Column(name = "takecare_transaction_id")
    private int takecareTransacntion;

    @Column(name = "takecare_transaction_name")
    private String takecareTransacntionName;

    @Column(name = "description")
    private String description;
    //FK
    @Column(name = "candidate_id")
    private String candidateId;

    //FK
    @Column(name = "job_id")
    private String jobId;
}
