package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "takecare_transaction")
public class TakeCareTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "takecare_transaction_id")
    private int takecareTransacntionId;

    @Column(name = "takecare_transaction_name")
    private String takecareTransacntionName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements  jobRequirements;

    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<BlackListAction> blackListActionList;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Status status;

    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TodoTask> todoTaskList;

    public TakeCareTransaction(Status status) {
        this.setStatus(status);
    }
}
