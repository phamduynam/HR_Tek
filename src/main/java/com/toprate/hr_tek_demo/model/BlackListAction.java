package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "takecare_transaction_id")
    private int takecareTransactionID;
}
