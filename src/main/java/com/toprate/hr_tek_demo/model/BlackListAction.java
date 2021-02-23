package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "blacklist_action")
public class BlackListAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blacklist_action_id")
    private int blackListId;

    @Column(name = "reason")
    private String reason;


    // FK
    @ManyToOne
    @JoinColumn(name = "takecare_transaction_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TakeCareTransaction takeCareTransaction;

}
