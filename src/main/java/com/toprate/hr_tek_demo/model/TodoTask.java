package com.toprate.hr_tek_demo.model;

import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "takecare_transaction_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TakeCareTransaction takeCareTransaction;
}
