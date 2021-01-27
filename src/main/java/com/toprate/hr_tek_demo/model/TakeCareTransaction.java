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
    private int takecareTransacntion;

    @Column(name = "takecare_transaction_name")
    private String takecareTransacntionName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "candidate_id") // thông qua khóa ngoại candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "job_id") // thông qua khóa ngoại job_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements  jobRequirements;

    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<BlackListAction> blackListActionList;

    @OneToOne(mappedBy = "takeCareTransaction" )
    private NoteStatus noteStatus;


    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TodoTask> todoTaskList;

}
