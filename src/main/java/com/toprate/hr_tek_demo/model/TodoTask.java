package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todo_task")
public class TodoTask {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "todo_task_id")
    private int todoTaskId;

    @Column(name = "description")
    private String description;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;
    //FK
    @Column(name = "takecare_transaction_id")
    private int takecareTransactionId;
}
