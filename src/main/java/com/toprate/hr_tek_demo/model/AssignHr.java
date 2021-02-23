package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "assign_hr")
public class AssignHr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_hr_id")
    private int assignHrId;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @OneToMany(mappedBy = "assignHr", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AssignHrJob> assignHrJobList;

    //FK
    @ManyToOne
    @JoinColumn(name = "contact_candidate_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;
    // FK
    @ManyToOne
    @JoinColumn(name = "manager_id") 
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user_manager;

    // FK
    @ManyToOne
    @JoinColumn(name = "hr_id") // thông qua khóa ngoại hr_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user_hr;
}
